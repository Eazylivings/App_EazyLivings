package com.eazylivings.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.eazylivings.R;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;

public class AboutUs extends AppCompatActivity {

    CommonFunctionality commonFunctionality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        try {
            commonFunctionality=new CommonFunctionality(getApplicationContext(),this);
            commonFunctionality.setTitleBar(R.id.aboutUs_backButton,R.id.aboutUs_titleBar,R.id.aboutUs_homeButton,Constants.TITLE_ABOUT_US);
            commonFunctionality.onClickListenerForImage(R.id.aboutUs_backButton);
            commonFunctionality.onClickListenerForImage(R.id.aboutUs_homeButton);

        }catch(Exception e){
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_WELCOME_SCREEN);
    }

    public void onClickBackButton(View view){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_WELCOME_SCREEN);
    }

    public void onClickHomeButton(View view){

        commonFunctionality.onClickHomeButton();
    }
}
