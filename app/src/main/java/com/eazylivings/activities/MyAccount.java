package com.eazylivings.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.activities.login.UpdateMyAccount;
import com.eazylivings.VO.UserDetails;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.DeviceSetup;
import com.eazylivings.profile.UserProfileSetup;
import com.eazylivings.sharedpreference.SharedPreference;

public class MyAccount extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        try {
            CommonFunctionality commonFunctionality=new CommonFunctionality(this);
            commonFunctionality.setTitleBar(R.id.myAccount_backButton,R.id.myAccount_titleBar,R.id.myAccount_homeButton,Constants.TITLE_MY_ACCOUNT);


            SharedPreference preference = new SharedPreference(getApplicationContext());
            String userName = preference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_USERNAME);

            UserProfileSetup userProfileSetup = new UserProfileSetup(getApplicationContext(), this);
            userProfileSetup.setupUserProfile(userName);

            DeviceSetup deviceSetup = new DeviceSetup(getApplicationContext());

            UserDetails userDetails = deviceSetup.getUserDetailsUsingSharedPreferences(getApplicationContext());
            setUserProfile(userDetails);
        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }
    @Override
    public void onBackPressed(){

        Intent intent = new Intent(getApplicationContext(),WelcomeScreen.class);
        startActivity(intent);
        finish();
    }

    public void onClickBackButton(View view){

        Intent intent=new Intent(getApplicationContext(),WelcomeScreen.class);
        startActivity(intent);
        finish();
    }

    public void onClickHomeButton(View view){

        Intent intent=new Intent(getApplicationContext(),WelcomeScreen.class);
        startActivity(intent);
        finish();
    }


    private void setUserProfile(UserDetails userDetails){

        try {

            TextView profile_userName = (TextView) findViewById(R.id.myAccount_mediumText_userName);
            TextView profile_emailAddress = (TextView) findViewById(R.id.myAccount_mediumText_emailAddress);
            TextView profile_contactNumber = (TextView) findViewById(R.id.myAccount_mediumText_contactNumber);
            TextView profile_address = (TextView) findViewById(R.id.myAccount_mediumText_residentialAddress);


            if (userDetails != null) {
                if (profile_userName != null) {
                    profile_userName.setText(userDetails.getUserName());
                }
                if (profile_emailAddress != null) {
                    profile_emailAddress.setText(userDetails.getEmail_address());
                }
                if (profile_contactNumber != null) {
                    profile_contactNumber.setText(userDetails.getContact_number());
                }
                if (profile_address != null) {
                    profile_address.setText(userDetails.getResidential_address());
                }
            }
        }catch(Exception e){
            generatePopupMessage("Exception Occurred");
        }
        }

    public void onClickUpdateInformation(View view){

        Intent intent=new Intent(getApplicationContext(), UpdateMyAccount.class);
        startActivity(intent);
        finish();
    }

    private void generatePopupMessage(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create(); //Use context
        alertDialog.setTitle("Warning");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }



}
