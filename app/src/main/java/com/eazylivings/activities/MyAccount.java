package com.eazylivings.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.VO.UserDetails;
import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.LocalDatabaseHandler;
import com.eazylivings.sharedpreference.SharedPreference;

public class MyAccount extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Button updateChangesButton=(Button)findViewById(R.id.myAccount_button_updateChanges);
        if(updateChangesButton!=null){
            updateChangesButton.setVisibility(View.INVISIBLE);
        }

        UserDetails userDetails;
        SharedPreference preference=new SharedPreference();
        String userName=preference.getStringValueFromSharedPreference(getApplicationContext(),"userName");

        LocalDatabaseHandler localDatabaseHandler=new LocalDatabaseHandler(getApplicationContext(),Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);

        userDetails=localDatabaseHandler.getUserProfileDetails(userName);
        setUserProfile(userDetails);
    }

    private void setUserProfile(UserDetails userDetails){

        TextView profile_userName=(TextView)findViewById(R.id.myAccount_editText_userName);
        TextView profile_emailAddress=(TextView)findViewById(R.id.myAccount_editText_userEmailAddress);
        TextView profile_contactNumber=(TextView)findViewById(R.id.myAccount_editText_userContactNumber);
        TextView profile_address=(TextView)findViewById(R.id.myAccount_editText_userAddress);
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

    public void onClickUpdate(View view){
        TextView profile_userName=(TextView)findViewById(R.id.myAccount_editText_userName);
        TextView profile_emailAddress=(TextView)findViewById(R.id.myAccount_editText_userEmailAddress);
        TextView profile_contactNumber=(TextView)findViewById(R.id.myAccount_editText_userContactNumber);
        TextView profile_address=(TextView)findViewById(R.id.myAccount_editText_userAddress);
        Button updateButton=(Button)findViewById(R.id.myAccount_button_update);
        Button updateChangesButton=(Button)findViewById(R.id.myAccount_button_updateChanges);

        if(profile_userName!=null && profile_emailAddress!=null && profile_contactNumber!=null && profile_address!=null && updateButton!=null && updateChangesButton!=null){
            updateButton.setVisibility(View.INVISIBLE);
            updateChangesButton.setVisibility(View.VISIBLE);


        }


    }

    public void onClickUpdateChanges(View view){

    }


}
