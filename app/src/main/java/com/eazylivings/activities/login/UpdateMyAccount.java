package com.eazylivings.activities.login;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.eazylivings.R;
import com.eazylivings.VO.UserDetails;
import com.eazylivings.activities.MyAccount;
import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.ServerDatabaseHandler;
import com.eazylivings.sharedpreference.SharedPreference;

public class UpdateMyAccount extends Activity {

    String previousUserName="";
    String previousEmailAddress="";
    String previousContactNumber="";
    String previousResidentialAddress="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_my_account);

        try {

            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setIcon(android.R.color.transparent);
                setTitle(Constants.TITLE_UPDATE_ACCOUNT);
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(Constants.BLUE_COLOR)));
            }

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
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
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
                generatePopupMessage(Constants.UPDATE_ACCOUNT);
            }
        }
    }

    //Back button control on Title bar
    public boolean onOptionsItemSelected(MenuItem item){

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
                generatePopupMessage(Constants.UPDATE_ACCOUNT);
                return true;
            }
        }else{
            generatePopupMessage(Constants.UPDATE_ACCOUNT);
            return true;
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
            generatePopupMessage(Constants.EXCEPTION_UPDATE_INFORMATION);
        }
    }

    private void generatePopupMessage(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(Constants.ALERT_CONFIRM);
        builder.setMessage(message);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(getApplicationContext(), MyAccount.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
