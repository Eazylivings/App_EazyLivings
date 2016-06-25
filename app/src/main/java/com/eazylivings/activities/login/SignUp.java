package com.eazylivings.activities.login;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.eazylivings.R;
import com.eazylivings.VO.UserDetails;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.databasehandler.ServerDatabaseHandler;
import com.eazylivings.validator.Validator;

public class SignUp extends AppCompatActivity {

    CommonFunctionality commonFunctionality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        commonFunctionality=new CommonFunctionality(getApplicationContext(),this);
        commonFunctionality.setTitleBar(R.id.signUp_backButton,R.id.signUp_titleBar,R.id.signUp_homeButton,Constants.TITLE_SIGN_UP);
        commonFunctionality.onClickListenerForImage(R.id.signUp_backButton);
        commonFunctionality.onClickListenerForImage(R.id.signUp_homeButton);
    }


    @Override
    public void onBackPressed(){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_SIGH_IN);
    }

    public void onClickBackButton(View view){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_SIGH_IN);
    }

    public void onClickHomeButton(View view){

        commonFunctionality.onClickHomeButton();
    }

    public void onClickSignUpButton(View view) {
        try {

            if (Validator.isInternetAvailable(getApplicationContext())) {

                final EditText userName = (EditText) findViewById(R.id.SignUpPage_editText_Name);
                final EditText password = (EditText) findViewById(R.id.SignUpPage_editText_password);
                final EditText emailAddress = (EditText) findViewById(R.id.SignUpPage_editText_email);
                final EditText contactNumber = (EditText) findViewById(R.id.SignUpPage_editText_PhoneNo);

                if (userName == null || userName.getText().toString().equalsIgnoreCase("")) {
                    commonFunctionality.generatePopupMessage(Constants.ENTER_USERNAME);

                } else if (contactNumber == null || contactNumber.getText().toString().equalsIgnoreCase("")) {
                    commonFunctionality.generatePopupMessage(Constants.ENTER_CONTACT_NUMBER);

                } else if (emailAddress == null || emailAddress.getText().toString().equalsIgnoreCase("")) {
                    commonFunctionality.generatePopupMessage(Constants.ENTER_EMAIL_ADDRESS);

                } else if (password == null || password.getText().toString().equalsIgnoreCase("")) {
                    commonFunctionality.generatePopupMessage(Constants.ENTER_PASSWORD);

                } else {

                    boolean isEmailFormatCorrect = Validator.checkEmailFormat(emailAddress);
                    boolean isUserAlreadyPresent = Validator.checkExistingUser(userName,getApplicationContext(),this);
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
                        commonFunctionality.generatePopupMessage(Constants.USER_ALREADY_PRESENT);
                    } else if (!isUserNameFormatCorrect) {
                        commonFunctionality.generatePopupMessage(Constants.ENTER_CORRECT_USERNAME);
                    } else if (!isEmailFormatCorrect) {
                        commonFunctionality.generatePopupMessage(Constants.ENTER_CORRECT_EMAIL_ADDRESS);
                    } else if (!isPasswordFormatCorrect) {
                        commonFunctionality.generatePopupMessage(Constants.ENTER_CORRECT_PASSWORD);
                    } else {
                        commonFunctionality.generatePopupMessage(Constants.ERROR_MESSAGE);
                    }
                }
            } else {
                commonFunctionality.generatePopupMessage(Constants.NOT_ONLINE);
            }
        }catch(Exception e){
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    public void onClickSignIn(View view){

        Intent intent = new Intent(getApplicationContext(),SignIn.class);
        startActivity(intent);
        finish();
    }
}
