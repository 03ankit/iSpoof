package com.coretechies.ispoof;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link set_caller_id_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class set_caller_id_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public set_caller_id_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment contact_phone_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static set_caller_id_Fragment newInstance(String param1, String param2) {
        set_caller_id_Fragment fragment = new set_caller_id_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    TextView setCallerText1, setCallerText2, setCallerText3, setCallerText4, setCallerText5, setCallerText6, setCallerText7, setCallerText8, setCallerText9, setCallerText0, setCallerHash, setCallerStar;
    EditText displayNo1, displayNo2;
    ImageView clearDisplay1 , clearDisplay2;
    Button saveChangebtn;
    View view;
    private boolean clicked =false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_set_caller_id, container, false);

        setCallerText1 = view.findViewById(R.id.setCallerButton_1);
        setCallerText2 = view.findViewById(R.id.setCallerButton_2);
        setCallerText3 = view.findViewById(R.id.setCallerButton_3);
        setCallerText4 = view.findViewById(R.id.setCallerButton_4);
        setCallerText5 = view.findViewById(R.id.setCallerButton_5);
        setCallerText6 = view.findViewById(R.id.setCallerButton_6);
        setCallerText7 = view.findViewById(R.id.setCallerButton_7);
        setCallerText8 = view.findViewById(R.id.setCallerButton_8);
        setCallerText9 = view.findViewById(R.id.setCallerButton_9);
        setCallerText0 = view.findViewById(R.id.setCallerButton_0);
        setCallerHash = view.findViewById(R.id.setCallerHash);
        setCallerStar = view.findViewById(R.id.setCallerStar);
        clearDisplay1 = view.findViewById(R.id.clear_text1);
        clearDisplay2 = view.findViewById(R.id.clear_text2);
        displayNo1 = view.findViewById(R.id.displayCurrentNumber);
        displayNo2 = view.findViewById(R.id.displayNumber);
        saveChangebtn =view.findViewById(R.id.saveButton);


        displayNo1.setCursorVisible(false);
        displayNo1.setFocusableInTouchMode(false);
        displayNo1.setFocusable(false);
        displayNo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setCallerText1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo1.setText(displayNo1.getText().toString() + "1");
                    }
                });
                setCallerText2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo1.setText(displayNo1.getText().toString() + "2");
                    }
                });
                setCallerText3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo1.setText(displayNo1.getText().toString() + "3");
                    }
                });
                setCallerText4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo1.setText(displayNo1.getText().toString() + "4");
                    }
                });
                setCallerText5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo1.setText(displayNo1.getText().toString() + "5");
                    }
                });
                setCallerText6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo1.setText(displayNo1.getText().toString() + "6");
                    }
                });
                setCallerText7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo1.setText(displayNo1.getText().toString() + "7");
                    }
                });
                setCallerText8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo1.setText(displayNo1.getText().toString() + "8");
                    }
                });
                setCallerText9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo1.setText(displayNo1.getText().toString() + "9");
                    }
                });
                setCallerText0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo1.setText(displayNo1.getText().toString() + "0");
                    }
                });
                setCallerStar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo1.setText(displayNo1.getText().toString() + "*");
                    }
                });
                setCallerHash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo1.setText(displayNo1.getText().toString() + "#");
                    }
                });
                clearDisplay1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String str = displayNo1.getText().toString();
                        if (str != null && str.length() > 0) {
                            str = str.substring(0, str.length() - 1);
                        }
                        displayNo1.setText(str);
                    }
                });
                clearDisplay1.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        displayNo1.setText("");
                        return true;
                    }
                });
            }
        });

        displayNo2.setCursorVisible(false);
        displayNo2.setFocusableInTouchMode(false);
        displayNo2.setFocusable(false);
        displayNo2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setCallerText1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo2.setText(displayNo2.getText().toString() + "1");
                    }
                });
                setCallerText2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo2.setText(displayNo2.getText().toString() + "2");
                    }
                });
                setCallerText3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo2.setText(displayNo2.getText().toString() + "3");
                    }
                });
                setCallerText4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo2.setText(displayNo2.getText().toString() + "4");
                    }
                });
                setCallerText5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo2.setText(displayNo2.getText().toString() + "5");
                    }
                });
                setCallerText6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo2.setText(displayNo2.getText().toString() + "6");
                    }
                });
                setCallerText7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo2.setText(displayNo2.getText().toString() + "7");
                    }
                });
                setCallerText8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo2.setText(displayNo2.getText().toString() + "8");
                    }
                });
                setCallerText9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo2.setText(displayNo2.getText().toString() + "9");
                    }
                });
                setCallerText0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo2.setText(displayNo2.getText().toString() + "0");
                    }
                });
                setCallerStar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo2.setText(displayNo2.getText().toString() + "*");
                    }
                });
                setCallerHash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayNo2.setText(displayNo2.getText().toString() + "#");
                    }
                });
                clearDisplay2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String str = displayNo2.getText().toString();
                        if (str != null && str.length() > 0) {
                            str = str.substring(0, str.length() - 1);
                        }
                        displayNo2.setText(str);
                    }
                });
                clearDisplay2.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        displayNo2.setText("");
                        return true;
                    }
                });

                saveChangebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String setCallerIdNo= displayNo2.getText().toString().trim();
                        SharedPreferences displayNo1 = getActivity().getSharedPreferences("setid", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = displayNo1.edit();
                        edit.putString("setNumber", setCallerIdNo);
                        edit.apply();
                    }
                });
            }
        });
                SharedPreferences displayNo1 = getActivity().getSharedPreferences("setid", Context.MODE_PRIVATE);
                String setUsername = displayNo1.getString("setNumber", null);
                displayNo2.setText(setUsername);

        return view;
    }


}