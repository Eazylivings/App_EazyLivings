package com.eazylivings.activities.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.eazylivings.R;
import com.eazylivings.VO.UserDetails;
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
        Intent intent = new Intent(getApplicationContext(),SignIn.class);
        startActivity(intent);
        finish();
    }

    public void onClickSignUpButton(View view) {

        final EditText userName = (EditText) findViewById(R.id.SignUpPage_editText_Name);
        final EditText password = (EditText) findViewById(R.id.SignUpPage_editText_password);
        final EditText emailAddress = (EditText) findViewById(R.id.SignUpPage_editText_email);
        final EditText contactNumber = (EditText) findViewById(R.id.SignUpPage_editText_PhoneNo);

        if(userName==null || userName.getText().toString().equalsIgnoreCase("")){
            generatePopupMessages(Constants.ENTER_USERNAME);

        }else if(contactNumber==null || contactNumber.getText().toString().equalsIgnoreCase("")){
            generatePopupMessages(Constants.ENTER_CONTACT_NUMBER);

        }else if(emailAddress==null ||emailAddress.getText().toString().equalsIgnoreCase("")){
            generatePopupMessages(Constants.ENTER_EMAIL_ADDRESS);

        }else if(password==null || password.getText().toString().equalsIgnoreCase("")){
            generatePopupMessages(Constants.ENTER_PASSWORD);

        }else {

            boolean isEmailFormatCorrect = Validator.checkEmailFormat(emailAddress);
            boolean isUserAlreadyPresent=false;
            //boolean isUserAlreadyPresent = Validator.checkExistingUser(userName);
            boolean isUserNameFormatCorrect = Validator.checkUsernameFormat(userName);
            boolean isPasswordFormatCorrect = Validator.checkPasswordFormat(password);

            if (isUserNameFormatCorrect && isEmailFormatCorrect && !isUserAlreadyPresent && isPasswordFormatCorrect) {


                UserDetails userDetails = new UserDetails();
                userDetails.setUserName(userName.getText().toString());
                userDetails.setPassword(password.getText().toString());
                userDetails.setEmail_address(emailAddress.getText().toString());
                userDetails.setContact_number(contactNumber.getText().toString());

                ServerDatabaseHandler serverDatabaseHandler = new ServerDatabaseHandler(getApplicationContext(), this);
                serverDatabaseHandler.execute(Constants.REGISTER, userName.getText().toString(), password.getText().toString(), emailAddress.getText().toString(), contactNumber.getText().toString());


            } else if (isUserAlreadyPresent) {
                generatePopupMessages(Constants.USER_ALREADY_PRESENT);
            } else if (!isUserNameFormatCorrect) {
                generatePopupMessages(Constants.ENTER_CORRECT_USERNAME);
            } else if (!isEmailFormatCorrect) {
                generatePopupMessages(Constants.ENTER_CORRECT_EMAIL_ADDRESS);
            } else if (!isPasswordFormatCorrect) {
                generatePopupMessages(Constants.ENTER_CORRECT_PASSWORD);
            } else {
                generatePopupMessages(Constants.ERROR_MESSAGE);
            }
        }
    }

    public void onClickSignIn(View view){

        Intent intent = new Intent(getApplicationContext(),SignIn.class);
        startActivity(intent);
        finish();
    }

    private void generatePopupMessages(String message){
        {
            AlertDialog alertDialog = new AlertDialog.Builder(SignUp.this).create();
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
