package com.eazylivings.activities.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.constant.Constants;

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
    public void onSignInClick(View view){

        finish();
        Intent intent=new Intent(getApplicationContext(),SignIn.class);
        startActivity(intent);
    }

    public void onClickRetrievePassword(View view){

        EditText emailAddress=(EditText)findViewById(R.id.forgotPassword_button_emailAddress);
        Button retrievePassword=(Button)findViewById(R.id.forgotPassword_button_retrievePassword);
        TextView defaultMessage=(TextView)findViewById(R.id.forgotPassword_button_defaultMessage) ;
        TextView newTo=(TextView)findViewById(R.id.forgotPassword_textView_newTo);
        TextView linkSignUp=(TextView)findViewById(R.id.forgotPassword_link_signUp);
        boolean isEmailSuccessfullySent=false;


        if(emailAddress!=null){
            isEmailSuccessfullySent=sendEmailForPasswordRetrieval(emailAddress);
        }else{
            generatePopupMessages("Please check email Address.");
        }
        if(emailAddress!=null && retrievePassword!=null && newTo!=null && linkSignUp!=null) {

            linkSignUp.setVisibility(View.GONE);
            newTo.setVisibility(View.GONE);
            emailAddress.setVisibility(View.GONE);
            retrievePassword.setVisibility(View.GONE);
        }
        if(isEmailSuccessfullySent && defaultMessage!=null){
            defaultMessage.setText(Constants.MESSAGE_FOR_SUCCESSFUL_RESET_PASSWORD);
            signInButton.setVisibility(View.VISIBLE);
        }else if(defaultMessage!=null){
            defaultMessage.setText(Constants.MESSAGE_FAIL_RESET_PASSWORD);
        }
    }

    private boolean sendEmailForPasswordRetrieval(EditText emailAddress){

        /*MailHandler mailHandler=new MailHandler(getApplicationContext());
        if(emailAddress!=null) {
            mailHandler.execute("send email", Constants.FORGOTPASSWORD, emailAddress.getText().toString());
        }*/
        String activityResult="Email successfully sent";

        return activityResult.equalsIgnoreCase("Email successfully sent");
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
