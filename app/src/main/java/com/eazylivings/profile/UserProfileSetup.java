package com.eazylivings.profile;

import android.app.Activity;
import android.content.Context;

import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.ServerDatabaseHandler;
import com.eazylivings.sharedpreference.SharedPreference;

public class UserProfileSetup {

    Context applicationContext;
    Activity baseActivity;

    public UserProfileSetup(Context context, Activity activity){
        this.applicationContext=context;
        this.baseActivity=activity;
    }

    public void setupUserProfile(String userName) {

        SharedPreference preference=new SharedPreference(applicationContext);

        boolean isProfileAlreadyLoaded=preference.getBooleanValueFromSharedPreference(Constants.SHARED_PREFERENCE_PROFILE_ALREADY_LOADED);

        if(!isProfileAlreadyLoaded){
            ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(applicationContext,baseActivity);
            serverDatabaseHandler.execute(Constants.USER_PROFILE_ACTION,userName);
        }
    }


    }


