package com.eazylivings.activities;

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

    CommonFunctionality commonFunctionality;
    SharedPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        try {
            commonFunctionality=new CommonFunctionality(getApplicationContext(),this);
            commonFunctionality.setTitleBar(R.id.myAccount_backButton,R.id.myAccount_titleBar,R.id.myAccount_homeButton,Constants.TITLE_MY_ACCOUNT);
            commonFunctionality.onClickListenerForImage(R.id.myAccount_backButton);
            commonFunctionality.onClickListenerForImage(R.id.myAccount_homeButton);

            preference = new SharedPreference(getApplicationContext());
            String emailAddress = preference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_USERNAME);

            UserProfileSetup userProfileSetup = new UserProfileSetup(getApplicationContext(), this);
            userProfileSetup.setupUserProfile(emailAddress);

            DeviceSetup deviceSetup = new DeviceSetup(getApplicationContext());

            UserDetails userDetails = deviceSetup.getUserDetailsUsingSharedPreferences();
            setUserProfile(userDetails);
        }catch(Exception e){
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }
    @Override
    public void onBackPressed(){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_WELCOME_SCREEN);
    }

    public void onClickBackButton(View view){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_WELCOME_SCREEN);
    }

    public void onClickHomeButton(View view){

        commonFunctionality.onClickHomeButton();
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
            commonFunctionality.generatePopupMessage("Exception Occurred");
        }
        }

    public void onClickUpdateInformation(View view){

        preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY,Constants.ACTIVITY_MY_ACCOUNT);

        Intent intent=new Intent(getApplicationContext(), UpdateMyAccount.class);
        startActivity(intent);
        finish();
    }
}
