package com.eazylivings.activities.login;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.ServerDatabaseHandler;
import com.eazylivings.validator.Validator;

public class ForgotPassword extends Activity {

    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        try {
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setIcon(android.R.color.transparent);
                setTitle(Constants.FORGOT_PASSWORD_TITLE);
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(Constants.BLUE_COLOR)));
            }
            signInButton = (Button) findViewById(R.id.forgotPassword_button_signIn);
            if (signInButton != null) {
                signInButton.setVisibility(View.INVISIBLE);
            }
        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(getApplicationContext(),SignIn.class);
        startActivity(intent);
        finish();
    }

    //Back button control on Title bar
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), SignIn.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }
    public void onClickSignUp(View view){

        Intent intent=new Intent(getApplicationContext(),SignUp.class);
        startActivity(intent);
        finish();

    }
    public void onSignInClick(View view){

        Intent intent=new Intent(getApplicationContext(),SignIn.class);
        startActivity(intent);
        finish();
    }

    public void onClickRetrievePassword(View view) {

        try {

            if (Validator.isInternetAvailable(getApplicationContext())) {

                EditText emailAddress = (EditText) findViewById(R.id.forgotPassword_button_emailAddress);

                if (emailAddress == null || emailAddress.getText().toString().equalsIgnoreCase("") || !Validator.checkEmailFormat(emailAddress)) {
                    generatePopupMessage(Constants.CHECK_EMAIL_ADDRESS);
                } else {

                    Button retrievePassword = (Button) findViewById(R.id.forgotPassword_button_retrievePassword);
                    TextView newTo = (TextView) findViewById(R.id.forgotPassword_textView_newTo);
                    TextView linkSignUp = (TextView) findViewById(R.id.forgotPassword_link_signUp);

                    if (retrievePassword != null && newTo != null && linkSignUp != null) {

                        linkSignUp.setVisibility(View.GONE);
                        newTo.setVisibility(View.GONE);
                        emailAddress.setVisibility(View.GONE);
                        retrievePassword.setVisibility(View.GONE);

                    }
                    ServerDatabaseHandler handler = new ServerDatabaseHandler(getApplicationContext(), this);
                    handler.execute(Constants.FORGOT_PASSWORD, emailAddress.getText().toString());
                }
            } else {
                generatePopupMessage(Constants.NOT_ONLINE);
            }
        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_FORGOT_PASSWORD_RETRIEVE);
        }
    }

    private void generatePopupMessage(String message){

        {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
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
}
