package com.coretechies.ispoof;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link call_log_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class call_log_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public call_log_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment call_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static call_log_Fragment newInstance(String param1, String param2) {
        call_log_Fragment fragment = new call_log_Fragment();
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
    RecyclerView recyclerView;
    DBHandler dbHandler;
    ArrayList<Call_log_Model> dataholder;
    Call_Log_Adapter adapter;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_call_log, container, false);

        changeFragmentOrientation();

        dbHandler = new DBHandler(getActivity());
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataholder=new ArrayList<>();
        Cursor cursor = dbHandler.readAllData();
        while(cursor.moveToNext()){
            Call_log_Model obj=new Call_log_Model(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            dataholder.add(obj);
        }
        adapter = new Call_Log_Adapter(dataholder);
        recyclerView.setAdapter(adapter);
        boolean reverseLayout = true; // Or false if your data is already reversed
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, reverseLayout);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        return view;
    }

    public void changeFragmentOrientation(){
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

}