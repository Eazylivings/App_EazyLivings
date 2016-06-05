package com.eazylivings.validator;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.EditText;

import com.eazylivings.constant.Constants;

public class Validator {

    public static boolean checkPasswordFormat(EditText password){

        if(password!=null){
            String userPassword=password.getText().toString();
            return userPassword.matches("^[a-zA-Z0-9@$_]{3,15}$");
        }else{
            return false;
        }
    }

    public static boolean checkEmailFormat(EditText emailAddress){

        if(emailAddress!=null){
            String userEmailAddress=emailAddress.getText().toString();
            return userEmailAddress.matches("^[a-zA-Z0-9@$_]+[@][a-zA-Z]{2,8}[.][a-zA-Z]{2,3}$");
        }else{
            return false;
        }
    }

    public static boolean checkUsernameFormat(EditText userName){

        if(userName!=null ){
            String name=userName.getText().toString();

           return name.matches("^[a-zA-Z]{3,15}$");

        }else{
            return false;
        }
    }

    public static boolean checkExistingUser(EditText userName){

        ServerSideValidationHandler serverSideValidationHandler=new ServerSideValidationHandler();

        if(userName!=null && userName.getText()!=null){
            String result= serverSideValidationHandler.checkUserExists(Constants.CHECK_EXISTING_USER,userName.getText().toString());
            return result.equalsIgnoreCase("true");
        }else
        {
            return false;
        }

    }

    public static boolean checkContactNumber(EditText contactNumber){



        if(contactNumber!=null){
            String number= contactNumber.getText().toString();
            return number.matches("^[789][0-9]{9}+$");
        }else{
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
