package com.eazylivings.activities.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.databasehandler.ServerDatabaseHandler;
import com.eazylivings.constant.Constants;
import com.eazylivings.validator.Validator;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ProgressBar progressBar=(ProgressBar)findViewById(R.id.loginPage_progressBar_progress);
        if(progressBar!=null) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),WelcomeScreen.class);
        startActivity(intent);
        finish();
    }

    public void onClickLoginButton(View view) {
        EditText editText_userName = (EditText) findViewById(R.id.loginPage_editText_userName);
        EditText editText_password = (EditText) findViewById(R.id.loginPage_editText_password);

        if(editText_userName!=null && editText_password!=null) {
            if (editText_userName.getText().toString().equalsIgnoreCase("")) {
                generatePopupMessage(Constants.ENTER_USERNAME);
            } else if (editText_password.getText().toString().equalsIgnoreCase("")) {
                generatePopupMessage(Constants.ENTER_PASSWORD);
            } else {

                boolean isUserOnline = Validator.isInternetAvailable(getApplicationContext());
                if (isUserOnline) {
                    ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(getApplicationContext(),this);
                    serverDatabaseHandler.execute(Constants.LOGIN,editText_userName.getText().toString(),editText_password.getText().toString());
                }else{
                    generatePopupMessage(Constants.NOT_ONLINE);
                }
            }
        }
    }

    public void onClickRegisterButton(View view) {

        if(Validator.isInternetAvailable(getApplicationContext())) {

            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        }else{
            generatePopupMessage(Constants.NOT_ONLINE);
        }
    }

    public void onClickForgotPassword(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
        finish();
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
