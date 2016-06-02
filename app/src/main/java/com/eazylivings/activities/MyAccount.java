package com.eazylivings.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.VO.UserDetails;
import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.LocalDatabaseHandler;
import com.eazylivings.profile.UserProfileHandler;

public class MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        String emailAddress="sharma.vibek80@gmail.com";
        UserProfileHandler userProfileHandler=new UserProfileHandler(getApplicationContext(),this);
        //userProfileHandler.execute(Constants.USER_PROFILE_ACTION,Constants.USER_PROFILE,emailAddress);

    }

    /*private void setUserProfile(String userName){

        LocalDatabaseHandler localDatabaseHandler=new LocalDatabaseHandler(getApplicationContext(), Constants.DATABASE_NAME,null, Constants.DATABASE_VERSION);
        UserDetails userDetails=localDatabaseHandler.fetchDetailsFromTable(userName);
        TextView profile_userName=(TextView)findViewById(R.id.userProfile_text_userName);
        TextView profile_emailAddress=(TextView)findViewById(R.id.userProfile_text_emailAddress);
        TextView profile_contactNumber=(TextView)findViewById(R.id.userProfile_text_contactNumber);
        TextView profile_address=(TextView)findViewById(R.id.userProfile_text_address);
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


        }*/


}
