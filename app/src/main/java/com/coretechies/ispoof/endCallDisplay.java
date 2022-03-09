package com.coretechies.ispoof;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.sip.SipAudioCall;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class endCallDisplay extends AppCompatActivity {


    TextView name,time;
    ImageView endbutton,speaker,mute;
    boolean isPressed=false;
    private long startTime = 0L;
    private Handler myHandler = new Handler();
    long timeInMillies = 0L;
    long timeSwap = 0L;
    long finalTime = 0L;

    public SipProfile sipAddress = null;
    public SipManager manager = null;
    public SipProfile sipProfile = null;
    public SipAudioCall call = null;
    public IncomingCallReceiver callReceiver;



    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_call_display);

        name =findViewById(R.id.callerName);
        time=findViewById(R.id.callDuration);
        endbutton = findViewById(R.id.endCall);
        speaker=findViewById(R.id.callSpeaker);
        mute=findViewById(R.id.callMute);

        //closeLocalProfile();
        SharedPreferences preferences = getSharedPreferences("iSpoof", Context.MODE_PRIVATE);
        String setUsername = preferences.getString("setUsername", null);
        String setPassword = preferences.getString("setPassword", null);
        String setDomain = preferences.getString("setDomain", null);
        String number = preferences.getString("setNumber", null);
        name.setText(number);

        if (manager == null) {
            manager = SipManager.newInstance(this);
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
        IntentFilter filter =new IntentFilter();
        filter.addAction("android.SipDemo.INCOMING_CALL");
        callReceiver = new IncomingCallReceiver();
        registerReceiver(callReceiver,filter);
        // Screen on off can cause problems on pushto talk
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Intent intent = new Intent();
        intent.setAction("android.SipDemo.INCOMING_CALL");
        PendingIntent pendingIntent = PendingIntent.getActivity(endCallDisplay.this, 0, intent, Intent.FILL_IN_DATA);
        try {
            manager.open(sipProfile, pendingIntent, null);
        } catch (SipException e) {
            e.printStackTrace();
        }
        try {
            manager.setRegistrationListener(sipProfile.getUriString(),
                    new SipRegistrationListener() {
                        public void onRegistering(String localProfileUri) {
                            Log.e("endcallSipTest", "Registering with SIP Server...");
                        }

                        public void onRegistrationDone(String localProfileUri, long expiryTime) {
                            Log.e("endcallSipTest", "Ready");
                        }
                        public void onRegistrationFailed(String localProfileUri, int errorCode, String errorMessage) {
                            try {
                                manager.close(sipProfile.getUriString());
                            } catch (SipException e) {
                                e.printStackTrace();
                            }
                            Log.e("endcallSipTest", "Registration failed.  Please check settings.");
                        }
                    });
        } catch (SipException e) {
            e.printStackTrace();
        }
        try {
            SipProfile.Builder builder = new SipProfile.Builder(number, setDomain);
            builder.setPort(5620);
            builder.setProtocol("UDP");
            // builder.setPort(5080);
            builder.setAuthUserName(setUsername);
            sipAddress = builder.build();
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        initiateCall();

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
            Log.e("set_id/onDestroy", "Failed to close local profile.", ee);
        }
    }
    public void initiateCall()  {
        Log.e("endcallSipTest", "initiatecall");
        try {
            SipAudioCall.Listener listener = new SipAudioCall.Listener()
            {
                @Override
                public void onCallEstablished(SipAudioCall call) {
                    Log.e("endcallsip", "onCALL");
                    call.startAudio();
                    startTime = SystemClock.uptimeMillis();
                    myHandler.postDelayed(updateTimerMethod, 0);
                    //getSuperHeroes();
                }
                @Override
                public void onCallEnded(SipAudioCall call) {
                    Log.e("endcallsip", "onCALLEnd");
                    finish(); }
            };
            call= manager.makeAudioCall(sipProfile.getUriString(), sipAddress.getUriString(), listener, 4);
        } catch (SipException e) {
            e.printStackTrace();
        }
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPressed){
                    speaker.setImageResource(R.drawable.speaker);
                    call.setSpeakerMode(true);
                }else{
                    speaker.setImageResource(R.drawable.ic_baseline_volume_off_24);
                    call.setSpeakerMode(false);
                }
                isPressed=!isPressed;
            }
        });
        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPressed){
                    mute.setImageResource(R.drawable.ic_baseline_mic_off_24);
                        call.isMuted();
                }else{
                    mute.setImageResource(R.drawable.ic_baseline_mic_24);
                    call.isMuted();
                }
                isPressed=!isPressed;
            }
        });
        endbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    call.endCall();
                    finish();
                } catch (SipException e) {
                    e.printStackTrace();
                }
            }
        });
       // Log.e("call", "" + call.getState());
    }
    private Runnable updateTimerMethod = new Runnable() {
        public void run() {
            timeInMillies = SystemClock.uptimeMillis() - startTime;
            finalTime = timeSwap + timeInMillies;
            int seconds = (int) (finalTime / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            time.setText("" + minutes + ":" + String.format("%02d", seconds));
            myHandler.postDelayed(this, 0);
        }
    };
    private void getSuperHeroes() {
        SharedPreferences preferences = getSharedPreferences("iSpoof", MODE_PRIVATE);
        String setUsername = preferences.getString("setUsername", null);
        String setPassword = preferences.getString("setPassword", null);
        String number = preferences.getString("setNumber", null);
        Call<ResponseBody> call1 = RetrofitClient.getInstance().getMyApi().savePost("W2UmZ2AidghAwQqD7KLu24GaLQ8vnyZ54uce3r8KSSkmMzua6Pq3C7ZPPavtwbV12",setUsername,number,"32",setPassword);
        call1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody show = response.body();
                Log.e("Response message", response.errorBody().toString());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Response message1",t.getMessage());
            }
        });
    }

}
