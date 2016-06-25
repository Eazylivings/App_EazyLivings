package com.eazylivings.activities.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.databasehandler.ServerDatabaseHandler;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;
import com.eazylivings.validator.Validator;

public class SignIn extends AppCompatActivity {


    SharedPreference preference;
    CommonFunctionality commonFunctionality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        try {
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.loginPage_progressBar_progress);
            if (progressBar != null) {
                progressBar.setVisibility(View.INVISIBLE);
            }

            preference=new SharedPreference(getApplicationContext());

            commonFunctionality=new CommonFunctionality(getApplicationContext(),this);
            commonFunctionality.setTitleBar(R.id.signIn_backButton,R.id.signIn_titleBar,R.id.signIn_homeButton,Constants.TITLE_SIGN_IN);
            commonFunctionality.onClickListenerForImage(R.id.signIn_backButton);
            commonFunctionality.onClickListenerForImage(R.id.signIn_homeButton);

        }catch(Exception e){
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_WELCOME_SCREEN);
    }

    public void onClickHomeButton(View view){

        commonFunctionality.onClickHomeButton();
    }

    public void onClickBackButton(View view){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_WELCOME_SCREEN);
    }


    public void onClickLoginButton(View view) {
        try {
            EditText editText_userName = (EditText) findViewById(R.id.loginPage_editText_userName);
            EditText editText_password = (EditText) findViewById(R.id.loginPage_editText_password);

            if (editText_userName != null && editText_password != null) {
                if (editText_userName.getText().toString().equalsIgnoreCase("")) {
                    commonFunctionality.generatePopupMessage(Constants.ENTER_USERNAME);
                } else if (editText_password.getText().toString().equalsIgnoreCase("")) {
                    commonFunctionality.generatePopupMessage(Constants.ENTER_PASSWORD);
                } else {

                    boolean isUserOnline = Validator.isInternetAvailable(getApplicationContext());
                    if (isUserOnline) {
                        ServerDatabaseHandler serverDatabaseHandler = new ServerDatabaseHandler(getApplicationContext(), this);
                        serverDatabaseHandler.execute(Constants.LOGIN, editText_userName.getText().toString(), editText_password.getText().toString());
                    } else {
                        commonFunctionality.generatePopupMessage(Constants.NOT_ONLINE);
                    }
                }
            }
        }catch(Exception e){
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_SIGN_IN);
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
}
