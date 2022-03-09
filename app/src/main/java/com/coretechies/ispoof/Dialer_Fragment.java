package com.coretechies.ispoof;

import static com.google.gson.internal.bind.util.ISO8601Utils.format;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.sip.SipAudioCall;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dialer_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Dialer_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Dial_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Dialer_Fragment newInstance(String param1, String param2) {
        Dialer_Fragment fragment = new Dialer_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Dialer_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextView dialText1, dialText2, dialText3, dialText4, dialText5, dialText6, dialText7, dialText8, dialText9, dialText0, dialHash, dialStar;
    EditText display_no;
    ImageView callImg, clearDisplay;
    private DBHandler dbHandler;
    View view;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    public SipProfile sipAddress = null;
    public SipManager manager = null;
    public SipProfile sipProfile = null;
    public SipAudioCall call = null;
    public IncomingCallReceiver callReceiver;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dialer, container, false);


        dialText1 = view.findViewById(R.id.call_button_1);
        dialText2 = view.findViewById(R.id.call_button_2);
        dialText3 = view.findViewById(R.id.call_button_3);
        dialText4 = view.findViewById(R.id.call_button_4);
        dialText5 = view.findViewById(R.id.call_button_5);
        dialText6 = view.findViewById(R.id.call_button_6);
        dialText7 = view.findViewById(R.id.call_button_7);
        dialText8 = view.findViewById(R.id.call_button_8);
        dialText9 = view.findViewById(R.id.call_button_9);
        dialText0 = view.findViewById(R.id.call_button_0);
        dialHash = view.findViewById(R.id.call_button_hash);
        dialStar = view.findViewById(R.id.call_button_star);
        clearDisplay = view.findViewById(R.id.clear_text);
        display_no = view.findViewById(R.id.display_number);
        callImg = view.findViewById(R.id.call);

        // display_no.setShowSoftInputOnFocus(false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        display_no.setCursorVisible(false);
        display_no.setFocusableInTouchMode(false);
        display_no.setFocusable(false);

        dbHandler = new DBHandler(getActivity());

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.RECORD_AUDIO)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        1);
            }
        }
        /*if (manager == null) {
            manager = SipManager.newInstance(getActivity());
        }*/

        dialText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_no.setText(display_no.getText().toString() + "1");
            }
        });
        dialText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_no.setText(display_no.getText().toString() + "2");
            }
        });
        dialText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_no.setText(display_no.getText().toString() + "3");
            }
        });
        dialText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_no.setText(display_no.getText().toString() + "4");
            }
        });
        dialText5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_no.setText(display_no.getText().toString() + "5");
            }
        });
        dialText6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_no.setText(display_no.getText().toString() + "6");
            }
        });
        dialText7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_no.setText(display_no.getText().toString() + "7");
            }
        });
        dialText8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_no.setText(display_no.getText().toString() + "8");
            }
        });
        dialText9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_no.setText(display_no.getText().toString() + "9");
            }
        });
        dialText0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_no.setText(display_no.getText().toString() + "0");
            }
        });
        dialStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_no.setText(display_no.getText().toString() + "*");
            }
        });
        dialHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_no.setText(display_no.getText().toString() + "#");
            }
        });
        clearDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = display_no.getText().toString();
                if (str != null && str.length() > 0) {
                    str = str.substring(0, str.length() - 1);
                }
                display_no.setText(str);
            }
        });
        clearDisplay.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                display_no.setText("");
                return true;
            }
        });
        dialText0.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                display_no.setText(display_no.getText().toString() + "+");
                return true;
            }
        });
        callImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phonenumber = display_no.getText().toString().trim();
                Long Ldate = System.currentTimeMillis();
                display_no.setText("");
                SharedPreferences preferences = getActivity().getSharedPreferences("iSpoof", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString("setNumber", phonenumber);
                edit.apply();
                if (phonenumber.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Number", Toast.LENGTH_LONG).show();
                } else {
                    dbHandler.addNewCourse(phonenumber, phonenumber, Ldate);
                    Intent i = new Intent(getActivity(), endCallDisplay.class);
                    getActivity().startActivity(i);
                }

            }
        });
        return view;
    }



}