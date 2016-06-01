package com.eazylivings.activities.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.eazylivings.R;
import com.eazylivings.VO.UserDetails;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.ServerDatabaseHandler;
import com.eazylivings.validator.Validator;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }


    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(getApplicationContext(),SignIn.class);
        startActivity(intent);
    }

    public void onClickSignUpButton(View view) {

        final EditText userName = (EditText) findViewById(R.id.SignUpPage_editText_Name);
        final EditText password = (EditText) findViewById(R.id.SignUpPage_editText_password);
        final EditText emailAddress = (EditText) findViewById(R.id.SignUpPage_editText_email);
        final EditText contactNumber = (EditText) findViewById(R.id.SignUpPage_editText_PhoneNo);

        boolean isEmailFormatCorrect=Validator.checkEmailFormat(emailAddress);
        boolean isUserAlreadyPresent=Validator.checkExistingUser(userName);
        boolean isUserNameFormatCorrect=Validator.checkUsernameFormat(userName);
        boolean isPasswordFormatCorrect=Validator.checkPasswordFormat(password);
        boolean isContactNumberCorrect= Validator.checkContactNumber(contactNumber);

        if(true/*isUserNameFormatCorrect && isEmailFormatCorrect && !isUserAlreadyPresent && isPasswordFormatCorrect && isContactNumberCorrect*/){


            UserDetails userDetails=new UserDetails();
            userDetails.setUserName(userName.getText().toString());
            userDetails.setPassword(password.getText().toString());
            userDetails.setEmail_address(emailAddress.getText().toString());
            userDetails.setContact_number(contactNumber.getText().toString());

            ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(getApplicationContext(),this);
            serverDatabaseHandler.execute(Constants.REGISTER,userName.getText().toString(),password.getText().toString(),emailAddress.getText().toString(),contactNumber.getText().toString());


        }/*else if(isUserAlreadyPresent){
            generatePopupMessages("This user is already present. Please sign in.");
        }else if(!isUserNameFormatCorrect){
            generatePopupMessages("Please check username");
        }else if(!isEmailFormatCorrect){
            generatePopupMessages("Please provide correct email address");
        }else if(!isPasswordFormatCorrect){
            generatePopupMessages("Please choose correct password");
        }else if(!isContactNumberCorrect){
            generatePopupMessages("Please provide correct contact number.");
        }*/
    }

    public void onClickSignIn(View view){

        finish();
        Intent intent = new Intent(getApplicationContext(),SignIn.class);
        startActivity(intent);

    }

    private void generatePopupMessages(String message){
        {
            AlertDialog alertDialog = new AlertDialog.Builder(SignUp.this).create();
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
    private void backButtonListener(Button backButton){

        if (backButton != null) {
            backButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    }
}
