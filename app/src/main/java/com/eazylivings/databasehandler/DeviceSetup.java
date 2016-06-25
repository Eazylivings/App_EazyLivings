package com.eazylivings.databasehandler;

import android.content.Context;

import com.eazylivings.VO.UserDetails;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class DeviceSetup  {

    Context applicationContext;




    public DeviceSetup(Context context) {
        this.applicationContext=context;
    }

    public void saveUserDetailsUsingSharedPreference(UserDetails userDetails){

        SharedPreference preference=new SharedPreference(applicationContext);
        preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_USERNAME,userDetails.getUserName());
        preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_EMAIL_ADDRESS,userDetails.getEmail_address());
        preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_CONTACT_NUMBER,userDetails.getContact_number());
        preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_ADDRESS,userDetails.getResidential_address());
        preference.setBooleanValueInSharedPreference(Constants.SHARED_PREFERENCE_PROFILE_ALREADY_LOADED,true);
    }

    public UserDetails getUserDetailsUsingSharedPreferences(){

        SharedPreference sharedPreference=new SharedPreference(applicationContext);
        UserDetails userDetails=new UserDetails();

        userDetails.setUserName(sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_USERNAME));
        userDetails.setEmail_address(sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_EMAIL_ADDRESS));
        userDetails.setContact_number(sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_CONTACT_NUMBER));
        userDetails.setResidential_address(sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_ADDRESS));

        return userDetails;
    }
}
