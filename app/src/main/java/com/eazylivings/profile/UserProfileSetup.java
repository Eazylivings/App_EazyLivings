package com.eazylivings.profile;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.DeviceSetup;
import com.eazylivings.databasehandler.ServerDatabaseHandler;
import com.eazylivings.sharedpreference.SharedPreference;

public class UserProfileSetup {

    Context context;
    Activity baseActivity;

    public UserProfileSetup(Context applicationContext, Activity activity){
        this.context=applicationContext;
        this.baseActivity=activity;
    }

    public void setupUserProfile(String userName) {

        SharedPreference preference=new SharedPreference();

        boolean isProfileAlreadyLoaded=preference.getBooleanValueFromSharedPreference(context,"isProfileAlreadyLoaded");

        if(!isProfileAlreadyLoaded){
            ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(context,baseActivity);
            serverDatabaseHandler.execute(Constants.USER_PROFILE_ACTION,userName);
        }
    }


    }


