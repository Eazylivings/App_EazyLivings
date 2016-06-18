package com.eazylivings.activities.services.cooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;

public class CookingFinalScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_final_screen);

        try {

            CommonFunctionality commonFunctionality = new CommonFunctionality(this);
            commonFunctionality.setTitleBar(R.id.cookSelection_backButton, R.id.cookSelection_titleBar, R.id.cookSelection_homeButton, Constants.TITLE_COOK_SELECTION);
        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(getApplicationContext(),CookSelection.class);
        startActivity(intent);
        finish();
    }

    public void onClickBackButton(View view){

        Intent intent=new Intent(getApplicationContext(),CookSelection.class);
        startActivity(intent);
        finish();
    }

    public void onClickHomeButton(View view){

        Intent intent=new Intent(getApplicationContext(),WelcomeScreen.class);
        startActivity(intent);
        finish();
    }

    private void generatePopupMessage(String message){
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
