package com.eazylivings.activities.login;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.ServerDatabaseHandler;
import com.eazylivings.validator.Validator;

public class ForgotPassword extends AppCompatActivity {

    Button signInButton;
    CommonFunctionality commonFunctionality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        try {

            commonFunctionality=new CommonFunctionality(getApplicationContext(),this);
            commonFunctionality.setTitleBar(R.id.forgotPassword_backButton,R.id.forgotPassword_titleBar,R.id.forgotPassword_homeButton,Constants.TITLE_FORGOT_PASSWORD);
            commonFunctionality.onClickListenerForImage(R.id.forgotPassword_backButton);
            commonFunctionality.onClickListenerForImage(R.id.forgotPassword_homeButton);


            signInButton = (Button) findViewById(R.id.forgotPassword_button_signIn);
            if (signInButton != null) {
                signInButton.setVisibility(View.INVISIBLE);
            }
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
                    commonFunctionality.generatePopupMessage(Constants.CHECK_EMAIL_ADDRESS);
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
                commonFunctionality.generatePopupMessage(Constants.NOT_ONLINE);
            }
        }catch(Exception e){
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_FORGOT_PASSWORD_RETRIEVE);
        }
    }


}
