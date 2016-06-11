package com.eazylivings.validator;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.EditText;

import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.ServerDatabaseHandler;

public class Validator {

    public static boolean checkPasswordFormat(EditText password){

        if(password!=null){
            String userPassword=password.getText().toString();
            return userPassword.matches(Constants.REGEX_PASSWORD);
        }else{
            return false;
        }
    }

    public static boolean checkEmailFormat(EditText emailAddress){

        if(emailAddress!=null){
            String userEmailAddress=emailAddress.getText().toString();
            return userEmailAddress.matches(Constants.REGEX_EMAIL);
        }else{
            return false;
        }
    }

    public static boolean checkUsernameFormat(EditText userName){

        if(userName!=null ){
            String name=userName.getText().toString();

           return name.matches(Constants.REGEX_USERNAME);

        }else{
            return false;
        }
    }

    public static boolean checkExistingUser(EditText userName, Context context, Activity activity){

        ServerDatabaseHandler serverSideValidationHandler=new ServerDatabaseHandler(context,activity);

        if(userName!=null && userName.getText()!=null){
            serverSideValidationHandler.execute(Constants.CHECK_EXISTING_USER,userName.getText().toString());
            return false;
        }else
        {
            return false;
        }

    }

    public static boolean isInternetAvailable(Context context) {

        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    /*public boolean isConnectedToWiFi(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return  activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
    }*/
}
