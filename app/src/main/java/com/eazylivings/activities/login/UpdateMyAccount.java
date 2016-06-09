package com.eazylivings.activities.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.eazylivings.R;
import com.eazylivings.VO.UserDetails;
import com.eazylivings.activities.MyAccount;
import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.ServerDatabaseHandler;
import com.eazylivings.sharedpreference.SharedPreference;

public class UpdateMyAccount extends AppCompatActivity {

    String previousUserName="";
    String previousEmailAddress="";
    String previousContactNumber="";
    String previousResidentialAddress="";

    @Override
    public void onBackPressed() {

        EditText userName=(EditText)findViewById(R.id.updateMyAccount_editText_userName);
        EditText emailAddress=(EditText)findViewById(R.id.updateMyAccount_editText_emailAddress);
        EditText contactNumber=(EditText)findViewById(R.id.updateMyAccount_editText_contactNumber);
        EditText residentialAddress=(EditText)findViewById(R.id.updateMyAccount_editText_residentialAddress);

        if(userName!=null && emailAddress!=null && contactNumber!=null && residentialAddress!=null){

            if(!userName.getText().toString().equalsIgnoreCase(previousUserName) || !emailAddress.getText().toString().equalsIgnoreCase(previousEmailAddress) || !contactNumber.getText().toString().equalsIgnoreCase(previousContactNumber) || !residentialAddress.getText().toString().equalsIgnoreCase(previousResidentialAddress)) {

                generatePopupMessage("You made changes in your profile. Are you sure you want to leave without updating changes");
            }else{
                Intent intent=new Intent(getApplicationContext(), MyAccount.class);
                startActivity(intent);
                finish();
            }
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_my_account);

        SharedPreference sharedPreference=new SharedPreference();


        EditText userName=(EditText)findViewById(R.id.updateMyAccount_editText_userName);
        EditText emailAddress=(EditText)findViewById(R.id.updateMyAccount_editText_emailAddress);
        EditText contactNumber=(EditText)findViewById(R.id.updateMyAccount_editText_contactNumber);
        EditText residentialAddress=(EditText)findViewById(R.id.updateMyAccount_editText_residentialAddress);

        if(userName!=null && emailAddress!=null && contactNumber!=null && residentialAddress!=null){

            previousUserName=userName.getText().toString();
            previousEmailAddress=emailAddress.getText().toString();
            previousContactNumber=contactNumber.getText().toString();
            previousResidentialAddress=residentialAddress.getText().toString();

            userName.setText(sharedPreference.getStringValueFromSharedPreference(getApplicationContext(),"userName"));
            emailAddress.setText(sharedPreference.getStringValueFromSharedPreference(getApplicationContext(),"emailAddress"));
            contactNumber.setText(sharedPreference.getStringValueFromSharedPreference(getApplicationContext(),"contactNumber"));
            residentialAddress.setText(sharedPreference.getStringValueFromSharedPreference(getApplicationContext(),"residentialAddress"));
        }
    }

    public void onClickSaveInformation(View view) {

        SharedPreference preference=new SharedPreference();

        UserDetails details=new UserDetails();
        EditText userName = (EditText) findViewById(R.id.updateMyAccount_editText_userName);
        EditText emailAddress = (EditText) findViewById(R.id.updateMyAccount_editText_emailAddress);
        EditText contactNumber = (EditText) findViewById(R.id.updateMyAccount_editText_contactNumber);
        EditText residentialAddress = (EditText) findViewById(R.id.updateMyAccount_editText_residentialAddress);

        if (userName != null && emailAddress != null && contactNumber != null && residentialAddress != null) {

            details.setUserName(userName.getText().toString());
            details.setEmail_address(emailAddress.getText().toString());
            details.setContact_number(contactNumber.getText().toString());
            details.setResidential_address(residentialAddress.getText().toString());

            preference.setStringValueInSharedPreference(getApplicationContext(),"userName",userName.getText().toString());
            preference.setStringValueInSharedPreference(getApplicationContext(),"emailAddress",emailAddress.getText().toString());
            preference.setStringValueInSharedPreference(getApplicationContext(),"contactNumber",contactNumber.getText().toString());
            preference.setStringValueInSharedPreference(getApplicationContext(),"residentialAddress",residentialAddress.getText().toString());

            ServerDatabaseHandler handler=new ServerDatabaseHandler(getApplicationContext(),this);
            handler.execute(Constants.SAVE_USER_UPDATE,userName.getText().toString(),emailAddress.getText().toString(),contactNumber.getText().toString(),residentialAddress.getText().toString());
        }
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
