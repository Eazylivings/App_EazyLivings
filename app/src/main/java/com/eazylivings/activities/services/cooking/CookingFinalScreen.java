package com.eazylivings.activities.services.cooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eazylivings.R;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;

public class CookingFinalScreen extends AppCompatActivity {

    CommonFunctionality commonFunctionality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_final_screen);

        try {

            commonFunctionality = new CommonFunctionality(getApplicationContext(),this);
            commonFunctionality.setTitleBar(R.id.cookingFinalScreen_backButton, R.id.cookingFinalScreen_titleBar, R.id.cookingFinalScreen_homeButton, Constants.TITLE_COOK_FINAL_SCREEN);
            commonFunctionality.onClickListenerForImage(R.id.cookingFinalScreen_backButton);
            commonFunctionality.onClickListenerForImage(R.id.cookingFinalScreen_homeButton);
        }catch(Exception e){
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        commonFunctionality.onBackPressed(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY);
    }

    public void onClickBackButton(View view){

        commonFunctionality.onBackPressed(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY);
    }

    public void onClickHomeButton(View view){

        commonFunctionality.onClickHomeButton();
    }
}
