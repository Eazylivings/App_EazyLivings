package com.eazylivings.activities.services.flatsetup;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.activities.services.OfferedServices;
import com.eazylivings.activities.services.WalkthroughServices;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class SelectItemsForFlat extends AppCompatActivity {

    String clickedFlatSubService;
    SharedPreference preference;
    String previousActivity="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_items_for_flat);

        try {

            preference=new SharedPreference(getApplicationContext());
            previousActivity=preference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY);
            CommonFunctionality commonFunctionality=new CommonFunctionality(this);
            commonFunctionality.setTitleBar(R.id.selectItemsForFlat_backButton,R.id.selectItemsForFlat_titleBar,R.id.selectItemsForFlat_homeButton,Constants.TITLE_SELECT_ITEMS_FOR_FLAT);
            commonFunctionality.onClickListenerForImage(R.id.selectItemsForFlat_backButton);
            commonFunctionality.onClickListenerForImage(R.id.selectItemsForFlat_homeButton);


            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                clickedFlatSubService = bundle.getString(Constants.SHARED_PREFERENCE_CLICKED_FLAT_SUB_SERVICE);
            }

        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        if(previousActivity.equalsIgnoreCase(Constants.ACTIVITY_OFFERED_SERVICE)){
            Intent intent = new Intent(getApplicationContext(),OfferedServices.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(getApplicationContext(),FlatSubServices.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClickBackButton(View view){

        if(previousActivity.equalsIgnoreCase(Constants.ACTIVITY_OFFERED_SERVICE)){
            Intent intent = new Intent(getApplicationContext(),OfferedServices.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(getApplicationContext(),FlatSubServices.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClickHomeButton(View view){

        Intent intent=new Intent(getApplicationContext(),WelcomeScreen.class);
        startActivity(intent);
        finish();
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
