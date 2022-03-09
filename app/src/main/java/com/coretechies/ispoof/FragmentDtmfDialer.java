package com.coretechies.ispoof;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.coretechies.ispoof.ui.DBHandlerDtmf;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDtmfDialer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDtmfDialer extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentDtmfDialer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDtmfDialer.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDtmfDialer newInstance(String param1, String param2) {
        FragmentDtmfDialer fragment = new FragmentDtmfDialer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextView text;
    ImageView copyImg;
    Button dtmfSaveButton,dtmfCancelButton;
    private DBHandlerDtmf dbHandler_dtmf;
    View view;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.dtmfdialer, container, false);

        text=view.findViewById(R.id.DTMF_number);
        dtmfSaveButton=view.findViewById(R.id.DtmfsaveButton);
        dtmfCancelButton=view.findViewById(R.id.cancelButton);
        copyImg=view.findViewById(R.id.copy_text);


        text.setCursorVisible(true);
        text.setFocusableInTouchMode(false);
        text.setFocusable(false);
        String DtmfNo =text.getText().toString();
        dbHandler_dtmf = new DBHandlerDtmf(getActivity());
        dtmfSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Long Ldate = System.currentTimeMillis();

                if (DtmfNo.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Number", Toast.LENGTH_LONG).show();
                } else {
                    dbHandler_dtmf.addNewDtmf(DtmfNo,Ldate);
                }
            }
        });
        dtmfCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                text.setText("");
            }
        });
        if (!DtmfNo.isEmpty()){
            copyImg.setColorFilter(Color.WHITE);
        }
        copyImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (copyImg.isPressed())
                {
                    copyImg.setColorFilter(Color.parseColor("#3673c5"));
                    ClipboardManager clipboard = (ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData myClip = ClipData.newPlainText("text", text.getText().toString());
                    clipboard.setPrimaryClip(myClip);
                    Log.e("clipbord ","clipbord copy");
                }
            }
        });

        return view;
    }
}