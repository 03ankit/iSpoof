package com.coretechies.ispoof;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coretechies.ispoof.ui.DBHandlerDtmf;

import java.util.ArrayList;
import java.util.List;

public class dtmf_list extends AppCompatActivity {

    ImageView backimg;
    TextView deletedata, data;
    RecyclerView recyclerView;
    dtmfListAdapter adapter1;
    DBHandlerDtmf dbHandler;
    ArrayList<DtmfModel> dataholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        changeFragmentOrientation();
        setContentView(R.layout.activity_dtmf_list);
        deletedata =findViewById(R.id.deleteAll);
        recyclerView = findViewById(R.id.DTMFRecycler);
        backimg=findViewById(R.id.DtmfbackButton);
        data=findViewById(R.id.Dtmfview1);
        dbHandler = new DBHandlerDtmf(dtmf_list.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(dtmf_list.this));

        dataholder=new ArrayList<>();
        Cursor cursor = dbHandler.DtmfreadAllData();
        while(cursor.moveToNext()){
            DtmfModel obj1=new DtmfModel(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2));
            dataholder.add(obj1);
        }
        adapter1 = new dtmfListAdapter(dataholder);
        recyclerView.setAdapter(adapter1);
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        deletedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }


    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHandlerDtmf myDB = new DBHandlerDtmf(dtmf_list.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(dtmf_list.this, dtmf_list.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
    public void changeFragmentOrientation(){
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}