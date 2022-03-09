package com.coretechies.ispoof;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.sip.SipAudioCall;
import android.net.sip.SipErrorCode;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;




import android.net.sip.SipSession;

import android.widget.TextView;

import java.text.ParseException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class set_id extends AppCompatActivity {

    public SipManager manager = null;
    public SipProfile sipProfile = null;
    public SipAudioCall call = null;
    public IncomingCallReceiver callReceiver;
    EditText Username, Password, Domain;
    Button btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_set_id);

        Username = findViewById(R.id.sipUsername);
        Password = findViewById(R.id.sipPassword);
        Domain = findViewById(R.id.sipDomain);
        btnSignin = findViewById(R.id.signButton);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.USE_SIP,Manifest.permission.READ_EXTERNAL_STORAGE}, 2);

        if (manager == null) {
            manager = SipManager.newInstance(this);
        }
            closeLocalProfile();
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public void onClick(View view) {
                String username = Username.getText().toString();
                String password = Password.getText().toString();
                String domain = Domain.getText().toString();

                //getSuperHeroes();

                if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter UserName", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    } else {
                        if (password.length() < 0) {
                            Toast.makeText(getApplicationContext(), " Enter password  ", Toast.LENGTH_SHORT).show();
                        } else {
                            if (domain.isEmpty()) {
                                Toast.makeText(getApplicationContext(), " Enter Domain ", Toast.LENGTH_SHORT).show();
                            } else {

                                SharedPreferences preferences = getSharedPreferences("iSpoof", MODE_PRIVATE);
                                SharedPreferences.Editor edit = preferences.edit();
                                edit.putString("setUsername", username);
                                edit.putString("setPassword", password);
                                edit.putString("setDomain", domain);
                                edit.apply();


                                String  setUsername = preferences.getString("setUsername", null);
                                String setPassword = preferences.getString("setPassword", null);
                                String setDomain = preferences.getString("setDomain", null);

                                Log.i("name", setUsername);
                                Log.i("password", setPassword);
                                Log.i("Domain", setDomain);

                                Intent obj = new Intent(set_id.this, bottom_nav.class);
                                startActivity(obj);

                                Username.setText(setUsername);
                                Password.setText(setPassword);
                                Domain.setText(setDomain);

                                if (setUsername.length() == 0 || setDomain.length() == 0 || setPassword.length() == 0) {
                                    return;
                                }
                                try {
                                    SipProfile.Builder builder = new SipProfile.Builder(setUsername, setDomain);
                                    builder.setPassword(setPassword);
                                    builder.setPort(5620);
                                    builder.setProtocol("UDP");
                                    // builder.setPort(5080);
                                    builder.setAuthUserName(setUsername);
                                    sipProfile = builder.build();
                                } catch (ParseException parseException) {
                                    parseException.printStackTrace();
                                }
                                IntentFilter filter = new IntentFilter();
                                filter.addAction("android.SipDemo.INCOMING_CALL");
                                callReceiver = new IncomingCallReceiver();
                                registerReceiver(callReceiver, filter);

                                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                                Intent intent = new Intent();
                                intent.setAction("android.SipDemo.INCOMING_CALL");
                                PendingIntent pendingIntent = PendingIntent.getBroadcast(set_id.this, 0, intent, Intent.FILL_IN_DATA);
                                    try {
                                        manager.open(sipProfile, pendingIntent, new SipRegistrationListener() {
                                            @Override
                                            public void onRegistering(String s) {
                                                Log.e("SipTest", "Registering with SIP Server...");
                                                Toast.makeText(set_id.this," On Registering",Toast.LENGTH_SHORT).show();
                                            }
                                            @Override
                                            public void onRegistrationDone(String s, long l) {
                                                Log.e("SipTest", "Ready");
                                                Toast.makeText(set_id.this," On Registration Done",Toast.LENGTH_SHORT).show();
                                            }
                                            @Override
                                            public void onRegistrationFailed(String s, int i, String s1) {
                                                Log.e("SipTest", "Registration failed.  Please check settings.");
                                                Toast.makeText(set_id.this," On Registration failed",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        manager.setRegistrationListener(sipProfile.getUriString(),
                                                new SipRegistrationListener() {
                                                    public void onRegistering(String localProfileUri) {
                                                        Log.e("SipTest", "Registering with SIP Server...");
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                    Toast.makeText(set_id.this," On Registering",Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                    }
                                                    public void onRegistrationDone(String localProfileUri, long expiryTime) {
                                                        Log.e("SipTest", "Ready");
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                Toast.makeText(set_id.this," On Registration Done",Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                    }

                                                    public void onRegistrationFailed(String localProfileUri, int errorCode, String errorMessage) {
                                                        try {
                                                            manager.close(sipProfile.getUriString());
                                                        } catch (SipException e) {
                                                            e.printStackTrace();
                                                        }
                                                        Log.e("SipTest", "Registration failed.  Please check settings.");
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                    Toast.makeText(set_id.this," On Registration failed",Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                    }
                                                });
                                    }
                                    catch (SipException e) {
                                        e.printStackTrace();
                                    }
                                }
                           }
                        }
                    }
                }
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.SipDemo.INCOMING_CALL");
        callReceiver = new IncomingCallReceiver();
        this.registerReceiver(callReceiver, filter);
    }
        public void closeLocalProfile() {
        if (manager == null) {
            return;
        }
        try {
            if (sipProfile != null) {
                manager.close(sipProfile.getUriString());
            }
        } catch (Exception ee) {
            Log.d("set_id/onDestroy", "Failed to close local profile.", ee);
        }
    }
    public void updateStatus(SipAudioCall incomingCall) {

    }
   /*private void getSuperHeroes() {
        SharedPreferences preferences = getSharedPreferences("iSpoof", MODE_PRIVATE);
        String setUsername = preferences.getString("setUsername", null);
        String setPassword = preferences.getString("setPassword", null);
        Call<ResponseBody> call1 = RetrofitClient.getInstance().getMyApi().savePost("W2UmZ2AidghAwQqD7KLu24GaLQ8vnyZ54uce3r8KSSkmMzua6Pq3C7ZPPavtwbV12","283494","103","32","oHepJrl");
        call1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody show = response.body();
                Log.e("Response message", show.toString());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Response message1",t.getMessage());
            }
        });
    }*/
}