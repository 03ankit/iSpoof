package com.coretechies.ispoof;

import android.app.Application;
import android.content.SharedPreferences;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.text.ParseException;

public class BaseApplication extends Application implements AppLifeCycleHandler.AppLifeCycleCallback {

    public SipManager manager = null;
    public SipProfile sipProfile = null;

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onCreate() {
        super.onCreate();
        AppLifeCycleHandler appLifeCycleHandler = new AppLifeCycleHandler(this);
        registerActivityLifecycleCallbacks(appLifeCycleHandler);
        registerComponentCallbacks(appLifeCycleHandler);


        if (manager == null) {
            manager = SipManager.newInstance(this);
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public void onAppBackground() {
        Log.d("LifecycleEvent", "onAppBackground");
        Toast.makeText(this, "on Pause", Toast.LENGTH_SHORT).show();
        sipProfile =getSipProfile();
        if (sipProfile!= null) {
            try {
                if (manager.isRegistered(sipProfile.getUriString()))
                    manager.unregister(sipProfile, null);
                if (manager.isOpened(sipProfile.getUriString()))
                    manager.close(sipProfile.getUriString());

            } catch (SipException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAppForeground() {
        Log.d("LifecycleEvent", "onAppForeground");
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    SipProfile getSipProfile() {
        SharedPreferences preferences = getSharedPreferences("iSpoof", MODE_PRIVATE);
        String setUsername = preferences.getString("setUsername", null);
        String setPassword = preferences.getString("setPassword", null);
        String setDomain = preferences.getString("setDomain", null);
        if (setUsername != null) {
            try {
                SipProfile.Builder builder = new SipProfile.Builder(setUsername, setDomain);
                builder.setPassword(setPassword);
                builder.setPort(5620);
                builder.setProtocol("UDP");
                // builder.setPort(5080);
                builder.setAuthUserName(setUsername);
                return builder.build();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
                return null;
            }
        }
        else {return null;}
    }

}
