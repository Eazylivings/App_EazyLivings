package com.eazylivings;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.constant.Constants;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(!prefs.getBoolean(Constants.SHARED_PREFERENCE_LOGIN_STATUS, false)){
            prefs.edit().remove(Constants.SHARED_PREFERENCE_USERNAME).apply();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent;
                mainIntent = new Intent(MainActivity.this,WelcomeScreen.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 2000);
    }
}
