package com.eazylivings.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.eazylivings.constant.Constants;


public class SharedPreference {

    Context context;

    public SharedPreference(Context applicationContext){
        this.context=applicationContext;
    }

    public void setStringValueInSharedPreference(String key,String value){

        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
        if(preferences!=null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public String getStringValueFromSharedPreference(String key){
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (preferences != null && key.equalsIgnoreCase(Constants.SHARED_PREFERENCE_EMAIL_ADDRESS)) {
                return preferences.getString(key, Constants.SHARED_PREFERENCE_DEFAULT_EMAIL);
            } else if (preferences != null &&  key.equalsIgnoreCase(Constants.SHARED_PREFERENCE_USERNAME)) {
                return preferences.getString(key, Constants.SHARED_PREFERENCE_DEFAULT_USERNAME);
            }else if (preferences != null) {
                return preferences.getString(key, Constants.SHARED_PREFERENCE_DEFAULT_STRING);
            } else {
                return "";
            }
        }catch(Exception e){
            return "";
        }
    }

    public void setBooleanValueInSharedPreference(String key,boolean value){

        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
        if(preferences!=null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    public boolean getBooleanValueFromSharedPreference(String key){
        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, false);

    }



    public void removeValueFromSharedPreference(String valueToBeDeleted){
        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(valueToBeDeleted);
        editor.apply();


    }
    public void clearSharedPreference(){
        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

    }
}
