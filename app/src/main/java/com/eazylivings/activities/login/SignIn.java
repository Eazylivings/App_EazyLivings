package com.eazylivings.activities.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.databasehandler.DeviceSetup;
import com.eazylivings.databasehandler.ServerDatabaseHandler;
import com.eazylivings.constant.Constants;
import com.eazylivings.validator.Validator;

public class SignIn extends AppCompatActivity {

    private String userName;
    DeviceSetup deviceSetup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ProgressBar progressBar=(ProgressBar)findViewById(R.id.loginPage_progressBar_progress);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(getApplicationContext(),WelcomeScreen.class);
        startActivity(intent);
    }

    public void onClickLoginButton(View view) {
        EditText editText_userName = (EditText) findViewById(R.id.loginPage_editText_userName);
        EditText editText_password = (EditText) findViewById(R.id.loginPage_editText_password);

        if(editText_userName!=null && editText_password!=null) {
            if (editText_userName.getText().toString().equalsIgnoreCase("")) {
                generatePopupMessage("Please enter username");
            } else if (editText_password.getText().toString().equalsIgnoreCase("")) {
                generatePopupMessage("Please enter password");
            } else {

                boolean isUserOnline = Validator.isInternetAvailable(getApplicationContext());
                if (isUserOnline) {
                    finish();
                    ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(getApplicationContext(),this);
                    serverDatabaseHandler.execute(Constants.LOGIN,editText_userName.getText().toString(),editText_password.getText().toString());
                }else{
                    generatePopupMessage("Oops!!! You are not online!!!");
                }
            }
        }
    }

    public void onClickRegisterButton(View view) {

        if(Validator.isInternetAvailable(getApplicationContext())) {

            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        }else{
            generatePopupMessage("Please check internet connection.");
        }
    }

    public void onClickForgotPassword(View view) {

        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);

    }

    private void setupUserProfile(){

        boolean isUserSpecificTablesPresent=deviceSetup.checkIfUserSpecificTableExists(userName);

        if(isUserSpecificTablesPresent){
            setSession();

        }else{
            deviceSetup.createUserSpecificTables(userName);
            deviceSetup.populateUerSpecificTables(userName);
            setSession();
        }
    }

    private void setSession(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.edit().putBoolean("loginStatus", true).apply();
        prefs.edit().putString("userName", userName).apply();
    }

    private void generatePopupMessage(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(SignIn.this).create();
        alertDialog.setTitle("Alert");
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
