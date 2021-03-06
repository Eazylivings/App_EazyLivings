package com.eazylivings.activities.services.flatsetup;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.eazylivings.R;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class SelectItemsForFlat extends AppCompatActivity {

    String clickedFlatSubService;
    SharedPreference preference;
    CommonFunctionality commonFunctionality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_items_for_flat);

        try {

            preference=new SharedPreference(getApplicationContext());

            commonFunctionality=new CommonFunctionality(getApplicationContext(),this);
            commonFunctionality.setTitleBar(R.id.selectItemsForFlat_backButton,R.id.selectItemsForFlat_titleBar,R.id.selectItemsForFlat_homeButton,Constants.TITLE_SELECT_ITEMS_FOR_FLAT);
            commonFunctionality.onClickListenerForImage(R.id.selectItemsForFlat_backButton);
            commonFunctionality.onClickListenerForImage(R.id.selectItemsForFlat_homeButton);


            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                clickedFlatSubService = bundle.getString(Constants.SHARED_PREFERENCE_CLICKED_FLAT_SUB_SERVICE);
            }

        }catch(Exception e){
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_FLAT_SUB_SERVICES);
    }

    public void onClickBackButton(View view){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_FLAT_SUB_SERVICES);
    }

    public void onClickHomeButton(View view){

        commonFunctionality.onClickHomeButton();
    }
}
