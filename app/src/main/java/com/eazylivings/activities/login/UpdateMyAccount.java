package com.eazylivings.activities.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.eazylivings.R;
import com.eazylivings.VO.UserDetails;
import com.eazylivings.activities.MyAccount;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.ServerDatabaseHandler;
import com.eazylivings.sharedpreference.SharedPreference;

public class UpdateMyAccount extends AppCompatActivity {

    String previousUserName="";
    String previousEmailAddress="";
    String previousContactNumber="";
    String previousResidentialAddress="";
    CommonFunctionality commonFunctionality;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_my_account);

        try {

            commonFunctionality=new CommonFunctionality(getApplicationContext(),this);
            commonFunctionality.setTitleBar(R.id.updateMyAccount_backButton,R.id.updateMyAccount_titleBar,R.id.updateMyAccount_homeButton,Constants.TITLE_UPDATE_ACCOUNT);
            commonFunctionality.onClickListenerForImage(R.id.updateMyAccount_backButton);
            commonFunctionality.onClickListenerForImage(R.id.updateMyAccount_homeButton);


            SharedPreference sharedPreference = new SharedPreference(getApplicationContext());


            EditText userName = (EditText) findViewById(R.id.updateMyAccount_editText_userName);
            EditText emailAddress = (EditText) findViewById(R.id.updateMyAccount_editText_emailAddress);
            EditText contactNumber = (EditText) findViewById(R.id.updateMyAccount_editText_contactNumber);
            EditText residentialAddress = (EditText) findViewById(R.id.updateMyAccount_editText_residentialAddress);

            if (userName != null && emailAddress != null && contactNumber != null && residentialAddress != null) {

                previousUserName = userName.getText().toString();
                previousEmailAddress = emailAddress.getText().toString();
                previousContactNumber = contactNumber.getText().toString();
                previousResidentialAddress = residentialAddress.getText().toString();

                userName.setText(sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_USERNAME));
                emailAddress.setText(sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_EMAIL_ADDRESS));
                contactNumber.setText(sharedPreference.getStringValueFromSharedPreference(Constants.SERVER_HANDLER_PHONE_NUMBER));
                residentialAddress.setText(sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_ADDRESS));
            }
        }catch(Exception e){
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed() {

        SharedPreference sharedPreference = new SharedPreference(getApplicationContext());

        previousUserName = sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_USERNAME);
        previousEmailAddress = sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_EMAIL_ADDRESS);
        previousContactNumber = sharedPreference.getStringValueFromSharedPreference(Constants.SERVER_HANDLER_PHONE_NUMBER);
        previousResidentialAddress = sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_ADDRESS);

        EditText userName=(EditText)findViewById(R.id.updateMyAccount_editText_userName);
        EditText emailAddress=(EditText)findViewById(R.id.updateMyAccount_editText_emailAddress);
        EditText contactNumber=(EditText)findViewById(R.id.updateMyAccount_editText_contactNumber);
        EditText residentialAddress=(EditText)findViewById(R.id.updateMyAccount_editText_residentialAddress);

        if(userName!=null && emailAddress!=null && contactNumber!=null && residentialAddress!=null){

            if(userName.getText().toString().equalsIgnoreCase(previousUserName) || emailAddress.getText().toString().equalsIgnoreCase(previousEmailAddress) || contactNumber.getText().toString().equalsIgnoreCase(previousContactNumber) || residentialAddress.getText().toString().equalsIgnoreCase(previousResidentialAddress)) {

                Intent intent=new Intent(getApplicationContext(), MyAccount.class);
                startActivity(intent);
                finish();
            }else{
                commonFunctionality.generatePopupMessage(Constants.UPDATE_ACCOUNT);
            }
        }
    }

    //Back button control on Title bar
    public boolean onClickBackButton(View view){

        SharedPreference sharedPreference = new SharedPreference(getApplicationContext());

        previousUserName = sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_USERNAME);
        previousEmailAddress = sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_EMAIL_ADDRESS);
        previousContactNumber = sharedPreference.getStringValueFromSharedPreference(Constants.SERVER_HANDLER_PHONE_NUMBER);
        previousResidentialAddress = sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_ADDRESS);


        EditText userName=(EditText)findViewById(R.id.updateMyAccount_editText_userName);
        EditText emailAddress=(EditText)findViewById(R.id.updateMyAccount_editText_emailAddress);
        EditText contactNumber=(EditText)findViewById(R.id.updateMyAccount_editText_contactNumber);
        EditText residentialAddress=(EditText)findViewById(R.id.updateMyAccount_editText_residentialAddress);

        if(userName!=null && emailAddress!=null && contactNumber!=null && residentialAddress!=null) {

            if (userName.getText().toString().equalsIgnoreCase(previousUserName) || emailAddress.getText().toString().equalsIgnoreCase(previousEmailAddress) || contactNumber.getText().toString().equalsIgnoreCase(previousContactNumber) || residentialAddress.getText().toString().equalsIgnoreCase(previousResidentialAddress)) {
                Intent myIntent = new Intent(getApplicationContext(), MyAccount.class);
                startActivityForResult(myIntent, 0);
                finish();
                return true;
            } else {
                commonFunctionality.generatePopupMessage(Constants.UPDATE_ACCOUNT);
                return true;
            }
        }else{
            commonFunctionality.generatePopupMessage(Constants.UPDATE_ACCOUNT);
            return true;
        }
    }

    public void onClickHomeButton(View view){

        SharedPreference sharedPreference = new SharedPreference(getApplicationContext());

        previousUserName = sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_USERNAME);
        previousEmailAddress = sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_EMAIL_ADDRESS);
        previousContactNumber = sharedPreference.getStringValueFromSharedPreference(Constants.SERVER_HANDLER_PHONE_NUMBER);
        previousResidentialAddress = sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_ADDRESS);


        EditText userName=(EditText)findViewById(R.id.updateMyAccount_editText_userName);
        EditText emailAddress=(EditText)findViewById(R.id.updateMyAccount_editText_emailAddress);
        EditText contactNumber=(EditText)findViewById(R.id.updateMyAccount_editText_contactNumber);
        EditText residentialAddress=(EditText)findViewById(R.id.updateMyAccount_editText_residentialAddress);

        if(userName!=null && emailAddress!=null && contactNumber!=null && residentialAddress!=null) {

            if (userName.getText().toString().equalsIgnoreCase(previousUserName) || emailAddress.getText().toString().equalsIgnoreCase(previousEmailAddress) || contactNumber.getText().toString().equalsIgnoreCase(previousContactNumber) || residentialAddress.getText().toString().equalsIgnoreCase(previousResidentialAddress)) {
                Intent myIntent = new Intent(getApplicationContext(), WelcomeScreen.class);
                startActivityForResult(myIntent, 0);
                finish();
            } else {
                commonFunctionality.generatePopupMessage(Constants.UPDATE_ACCOUNT);
            }
        }else{
            commonFunctionality.generatePopupMessage(Constants.UPDATE_ACCOUNT);
        }
    }

    public void onClickSaveInformation(View view) {

        try {

            SharedPreference preference = new SharedPreference(getApplicationContext());

            UserDetails details = new UserDetails();
            EditText userName = (EditText) findViewById(R.id.updateMyAccount_editText_userName);
            EditText emailAddress = (EditText) findViewById(R.id.updateMyAccount_editText_emailAddress);
            EditText contactNumber = (EditText) findViewById(R.id.updateMyAccount_editText_contactNumber);
            EditText residentialAddress = (EditText) findViewById(R.id.updateMyAccount_editText_residentialAddress);

            if (userName != null && emailAddress != null && contactNumber != null && residentialAddress != null) {

                details.setUserName(userName.getText().toString());
                details.setEmail_address(emailAddress.getText().toString());
                details.setContact_number(contactNumber.getText().toString());
                details.setResidential_address(residentialAddress.getText().toString());

                preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_USERNAME, userName.getText().toString());
                preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_EMAIL_ADDRESS, emailAddress.getText().toString());
                preference.setStringValueInSharedPreference(Constants.SERVER_HANDLER_PHONE_NUMBER, contactNumber.getText().toString());
                preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_ADDRESS, residentialAddress.getText().toString());

                ServerDatabaseHandler handler = new ServerDatabaseHandler(getApplicationContext(), this);
                handler.execute(Constants.SAVE_USER_UPDATE, userName.getText().toString(), emailAddress.getText().toString(), contactNumber.getText().toString(), residentialAddress.getText().toString());
            }
        }catch(Exception e){
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_UPDATE_INFORMATION);
        }
    }
}
