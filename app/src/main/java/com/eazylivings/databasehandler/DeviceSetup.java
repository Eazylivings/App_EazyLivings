package com.eazylivings.databasehandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eazylivings.VO.UserDetails;
import com.eazylivings.sharedpreference.SharedPreference;

public class DeviceSetup  {

    Context applicationContext;




    public DeviceSetup(Context context) {
        this.applicationContext=context;
    }

    public void saveUserDetailsUsingSharedPreference(UserDetails userDetails,Context context){

        SharedPreference preference=new SharedPreference();
        preference.setStringValueInSharedPreference(context,"user_name",userDetails.getUserName());
        preference.setStringValueInSharedPreference(context,"email_address",userDetails.getEmail_address());
        preference.setStringValueInSharedPreference(context,"contact_number",userDetails.getContact_number());
        preference.setStringValueInSharedPreference(context,"address",userDetails.getResidential_address());
        preference.setBooleanValueInSharedPreference(context,"isProfileAlreadyLoaded",true);
    }

    public UserDetails getUserDetailsUsingSharedPreferences(Context context){

        SharedPreference sharedPreference=new SharedPreference();
        UserDetails userDetails=new UserDetails();

        userDetails.setUserName(sharedPreference.getStringValueFromSharedPreference(context,"user_name"));
        userDetails.setEmail_address(sharedPreference.getStringValueFromSharedPreference(context,"email_address"));
        userDetails.setContact_number(sharedPreference.getStringValueFromSharedPreference(context,"contact_number"));
        userDetails.setResidential_address(sharedPreference.getStringValueFromSharedPreference(context,"address"));

        return userDetails;
    }
}
