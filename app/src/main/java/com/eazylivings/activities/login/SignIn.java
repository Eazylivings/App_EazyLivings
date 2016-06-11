package com.eazylivings.activities.login;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.databasehandler.ServerDatabaseHandler;
import com.eazylivings.constant.Constants;
import com.eazylivings.validator.Validator;

public class SignIn extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        try {
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.loginPage_progressBar_progress);
            if (progressBar != null) {
                progressBar.setVisibility(View.INVISIBLE);
            }
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setIcon(android.R.color.transparent);
                setTitle(Constants.SIGN_IN_TITLE);
            }
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

    //Back button control on Title bar
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), WelcomeScreen.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }

    public void onClickLoginButton(View view) {
        try {
            EditText editText_userName = (EditText) findViewById(R.id.loginPage_editText_userName);
            EditText editText_password = (EditText) findViewById(R.id.loginPage_editText_password);

            if (editText_userName != null && editText_password != null) {
                if (editText_userName.getText().toString().equalsIgnoreCase("")) {
                    generatePopupMessage(Constants.ENTER_USERNAME);
                } else if (editText_password.getText().toString().equalsIgnoreCase("")) {
                    generatePopupMessage(Constants.ENTER_PASSWORD);
                } else {

                    boolean isUserOnline = Validator.isInternetAvailable(getApplicationContext());
                    if (isUserOnline) {
                        ServerDatabaseHandler serverDatabaseHandler = new ServerDatabaseHandler(getApplicationContext(), this);
                        serverDatabaseHandler.execute(Constants.LOGIN, editText_userName.getText().toString(), editText_password.getText().toString());
                    } else {
                        generatePopupMessage(Constants.NOT_ONLINE);
                    }
                }
            }
        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_SIGN_IN);
        }
    }

    public void onClickRegisterButton(View view) {

        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
    }

    public void onClickForgotPassword(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
        finish();
    }

    private void generatePopupMessage(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(SignIn.this).create();
        alertDialog.setTitle(Constants.ALERT_TITLE);
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
