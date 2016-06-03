package com.eazylivings.profile;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.DeviceSetup;
import com.eazylivings.databasehandler.ServerDatabaseHandler;

public class UserProfileSetup {

    Context context;
    Activity baseActivity;

    public UserProfileSetup(Context applicationContext, Activity activity){
        this.context=applicationContext;
        this.baseActivity=activity;
    }

    DeviceSetup deviceSetup;

    public void setupUserProfile(String userName) {

        ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(context,baseActivity);

        deviceSetup=new DeviceSetup(context, Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);
        boolean isUserSpecificTablesPresent = deviceSetup.checkIfUserSpecificTableExists(userName);

        if (isUserSpecificTablesPresent) {
            setSession(userName);
            serverDatabaseHandler.execute(Constants.USER_PROFILE_ACTION,userName);

        } else {
            deviceSetup.createUserSpecificTables(userName);
            deviceSetup.populateUerSpecificTables(userName);
            setSession(userName);
            serverDatabaseHandler.execute(Constants.USER_PROFILE_ACTION,userName);
        }
    }

    private void setSession(String userName){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean("loginStatus", true).apply();
        prefs.edit().putString("userName", userName).apply();
    }

    }


