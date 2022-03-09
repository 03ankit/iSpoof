package com.coretechies.ispoof;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;

public class bottom_nav extends AppCompatActivity {
    BottomNavigationView navigationView;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Toolbar toolbar;
        TextView textView;
        ImageView imageView,imageView1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);


        toolbar =findViewById(R.id.toolbar);
        textView=findViewById(R.id.textView12);
        imageView=findViewById(R.id.imageView5);
        imageView1 =findViewById(R.id.imageView7);
       // toolbar1 =findViewById(R.id.contactSearch);



        navigationView = findViewById(R.id.bottom_nav);
        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Dialer_Fragment()).commit();
            toolbar.setVisibility(View.GONE);
            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    confirmDialog();
                }
            });
        }
        else {
            String id=savedInstanceState.getString("id");
            if (id.equals(call_log_Fragment.class.getName())){
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new call_log_Fragment()).commit();
            }
            if (id.equals(contact_Fragment.class.getName())){
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new FragmentDtmfDialer()).commit();
            }
            if (id.equals(Dialer_Fragment.class.getName())){
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Dialer_Fragment()).commit();
            }
            if (id.equals(set_caller_id_Fragment.class.getName())){
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new set_caller_id_Fragment()).commit();
            }
            if (id.equals(setting_Fragment.class.getName())){
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new setting_Fragment()).commit();
            }

        }



        navigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {

                    case R.id.nav_call:
                        fragment = new call_log_Fragment();
                        toolbar.setVisibility(View.VISIBLE);
                        textView.setText("Call Log");
                        imageView.setImageResource(R.drawable.logo_2);
                        imageView1.setImageResource(R.drawable.ic_clear_all);
                        imageView1.setVisibility(View.VISIBLE);
                        imageView1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    confirmDialog();
                                }
                            });
                        break;
                    case R.id.nav_accountbox:
                        fragment = new FragmentDtmfDialer();
                        toolbar.setVisibility(View.VISIBLE);
                        textView.setText("DTMF");
                        imageView.setImageResource(R.drawable.logo_2);
                        imageView1.setImageResource(R.drawable.list);
                        imageView1.setVisibility(View.VISIBLE);
                        imageView1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(bottom_nav.this,dtmf_list.class);
                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.nav_dial:
                        fragment = new Dialer_Fragment();
                        toolbar.setVisibility(View.GONE);
                        item.setIcon(R.drawable.dialpad_1);
                        break;
                    case R.id.nav_contactphone:
                        fragment = new set_caller_id_Fragment();
                        toolbar.setVisibility(View.VISIBLE);
                        textView.setText("Set Caller ID");
                        imageView1.setVisibility(View.GONE);
                        imageView.setImageResource(R.drawable.ic_arrow_back);
                        break;
                    case R.id.nav_setting:
                        fragment = new setting_Fragment();
                        toolbar.setVisibility(View.VISIBLE);
                        textView.setText("Change Caller ID");
                        imageView.setImageResource(R.drawable.ic_arrow_back);
                        imageView1.setVisibility(View.GONE);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();
                return true;
            }
        });
        navigationView.setSelectedItemId(R.id.nav_dial);



    }

   /* @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "on Pause", Toast.LENGTH_SHORT).show();
        *//*try {
            manager.unregister(sipProfile,null);
            manager.close(sipProfile.getUriString());
        } catch (SipException e) {
            e.printStackTrace();
        }*//*

    }*/

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("id",getSupportFragmentManager().findFragmentById(R.id.framelayout).getClass().getName());
        super.onSaveInstanceState(outState);
    }

   void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHandler myDB = new DBHandler(bottom_nav.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(bottom_nav.this, bottom_nav.class);
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
}