package com.eazylivings.activities.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.eazylivings.adapter.ServicesOfferedAdaptor;
import com.eazylivings.R;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class OfferedServices extends AppCompatActivity {

    SharedPreference preference;
    CommonFunctionality commonFunctionality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_services);

        preference=new SharedPreference(getApplicationContext());

        try {

            commonFunctionality=new CommonFunctionality(getApplicationContext(),this);
            commonFunctionality.setTitleBar(R.id.offeredServices_backButton,R.id.offeredServices_titleBar,R.id.offeredServices_homeButton,Constants.TITLE_OFFERED_SERVICES);
            commonFunctionality.onClickListenerForImage(R.id.offeredServices_backButton);
            commonFunctionality.onClickListenerForImage(R.id.offeredServices_homeButton);

            String services[] = {"0", "1", "2", "3"};
            ListAdapter listAdapter = new ServicesOfferedAdaptor(this, services);
            ListView listView = (ListView) findViewById(R.id.offeredServices_listView_servicesList);
            if (listView != null) {
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener(

                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Intent intent = new Intent(getApplicationContext(), WalkthroughServices.class);
                                intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
                                preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY,Constants.ACTIVITY_OFFERED_SERVICE);
                                startActivity(intent);
                                finish();
                            }
                        }
                );
            }
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
