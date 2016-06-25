package com.eazylivings.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.eazylivings.activities.MyAccount;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.activities.login.UpdateMyAccount;
import com.eazylivings.activities.services.OfferedServices;
import com.eazylivings.activities.services.cooking.ConfigureGroceryList;
import com.eazylivings.activities.services.cooking.CookForVegOrNonVeg;
import com.eazylivings.activities.services.cooking.CookSelection;
import com.eazylivings.activities.services.cooking.CookingFinalScreen;
import com.eazylivings.activities.services.cooking.PreferredWayOfCooking;
import com.eazylivings.activities.services.cooking.SelectGroceryItems;
import com.eazylivings.activities.services.flatsetup.FlatSubServices;
import com.eazylivings.activities.services.flatsetup.SelectItemsForFlat;
import com.eazylivings.constant.Constants;

import java.util.ArrayList;
import java.util.HashMap;


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

    public Class getPreviousActivity(String activity){

        if(activity.equalsIgnoreCase(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY)){
            activity=getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY);
        }

       if(activity.equalsIgnoreCase(Constants.ACTIVITY_WELCOME_SCREEN)){
           return WelcomeScreen.class;
       }else if(activity.equalsIgnoreCase(Constants.ACTIVITY_FLAT_SUB_SERVICES)){
           return FlatSubServices.class;
       }else if(activity.equalsIgnoreCase(Constants.ACTIVITY_PREFERRED_WAY_OF_COOKING)){
           return PreferredWayOfCooking.class;
       }else if(activity.equalsIgnoreCase(Constants.ACTIVITY_COOK_FOR_VEG_NON_VEG)){
           return CookForVegOrNonVeg.class;
       }else if(activity.equalsIgnoreCase(Constants.ACTIVITY_COOK_SELECTION)){
           return CookSelection.class;
       }else if(activity.equalsIgnoreCase(Constants.ACTIVITY_CONFIGURE_GROCERY_LIST)){
           return ConfigureGroceryList.class;
       }else if(activity.equalsIgnoreCase(Constants.ACTIVITY_COOKING_FINAL_SCREEN)){
           return CookingFinalScreen.class;
       }else if(activity.equalsIgnoreCase(Constants.ACTIVITY_SELECT_GROCERY_ITEM)){
           return SelectGroceryItems.class;
       }else if(activity.equalsIgnoreCase(Constants.ACTIVITY_SELECT_ITEMS_FOR_FLAT)){
           return SelectItemsForFlat.class;
       }else if(activity.equalsIgnoreCase(Constants.ACTIVITY_OFFERED_SERVICE)){
           return OfferedServices.class;
       }else if(activity.equalsIgnoreCase(Constants.ACTIVITY_MY_ACCOUNT)){
           return MyAccount.class;
       }else if(activity.equalsIgnoreCase(Constants.ACTIVITY_UPDATE_ACCOUNT)){
           return UpdateMyAccount.class;
       }else{
           return WelcomeScreen.class;
       }

    }
}
