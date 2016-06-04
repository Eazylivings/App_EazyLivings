package com.eazylivings.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.UpdateMyAccount;
import com.eazylivings.VO.UserDetails;
import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.DeviceSetup;
import com.eazylivings.profile.UserProfileSetup;
import com.eazylivings.sharedpreference.SharedPreference;

public class MyAccount extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        SharedPreference preference=new SharedPreference();
        String userName=preference.getStringValueFromSharedPreference(getApplicationContext(),"userName");

        UserProfileSetup userProfileSetup=new UserProfileSetup(getApplicationContext(),this);
        userProfileSetup.setupUserProfile(userName);

        DeviceSetup deviceSetup=new DeviceSetup(getApplicationContext(),Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);

        UserDetails userDetails=deviceSetup.getUserDetailsUsingSharedPreferences(getApplicationContext());
        setUserProfile(userDetails);
    }

    private void setUserProfile(UserDetails userDetails){

        TextView profile_userName=(TextView)findViewById(R.id.myAccount_mediumText_userName);
        TextView profile_emailAddress=(TextView)findViewById(R.id.myAccount_mediumText_emailAddress);
        TextView profile_contactNumber=(TextView)findViewById(R.id.myAccount_mediumText_contactNumber);
        TextView profile_address=(TextView)findViewById(R.id.myAccount_mediumText_residentialAddress);


        if(userDetails!=null) {
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
        }

    public void onClickUpdateInformation(View view){

        Intent intent=new Intent(getApplicationContext(), UpdateMyAccount.class);
        startActivity(intent);
    }



}
