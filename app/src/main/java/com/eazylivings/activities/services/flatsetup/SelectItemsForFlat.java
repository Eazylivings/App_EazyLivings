package com.eazylivings.activities.services.flatsetup;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;

public class SelectItemsForFlat extends AppCompatActivity {

    int clickedSubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_items_for_flat);

        try {

            CommonFunctionality commonFunctionality=new CommonFunctionality(this);
            commonFunctionality.setTitleBar(R.id.selectItemsForFlat_backButton,R.id.selectItemsForFlat_titleBar,R.id.selectItemsForFlat_homeButton,Constants.TITLE_SELECT_ITEMS_FOR_FLAT);


            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                clickedSubService = bundle.getInt(Constants.SHARED_PREFERENCE_CLICKED_SERVICE);
            }

        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(getApplicationContext(),FlatSubServices.class);
        startActivity(intent);
        finish();
    }

    public void onClickBackButton(View view){

        Intent intent=new Intent(getApplicationContext(),FlatSubServices.class);
        startActivity(intent);
        finish();
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
