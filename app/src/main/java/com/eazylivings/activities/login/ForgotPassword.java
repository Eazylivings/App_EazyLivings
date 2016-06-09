package com.eazylivings.activities.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.ServerDatabaseHandler;
import com.eazylivings.validator.Validator;

public class ForgotPassword extends AppCompatActivity {

    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setTitle("Forgot Password");
        signInButton=(Button)findViewById(R.id.forgotPassword_button_signIn);
        if(signInButton!=null){
            signInButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(getApplicationContext(),SignIn.class);
        startActivity(intent);
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

        if(Validator.isInternetAvailable(getApplicationContext())) {

            EditText emailAddress = (EditText) findViewById(R.id.forgotPassword_button_emailAddress);

            if (emailAddress == null || emailAddress.getText().toString().equalsIgnoreCase("") || !Validator.checkEmailFormat(emailAddress)) {
                generatePopupMessages(Constants.CHECK_EMAIL_ADDRESS);
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
                ServerDatabaseHandler handler=new ServerDatabaseHandler(getApplicationContext(), this);
                handler.execute(Constants.FORGOT_PASSWORD, emailAddress.getText().toString());
            }
        }else{
            generatePopupMessages("Oops!!! You are not online!!!");
        }
    }

    private void generatePopupMessages(String message){

        {
            AlertDialog alertDialog = new AlertDialog.Builder(ForgotPassword.this).create();
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
}
