package com.eazylivings.activities.services.flatsetup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.eazylivings.R;
import com.eazylivings.adapter.OptionFlatSubServiceAdaptor;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class FlatSubServices extends AppCompatActivity {

    CommonFunctionality commonFunctionality;
    SharedPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_sub_services);

        try {
            preference=new SharedPreference(getApplicationContext());

            commonFunctionality=new CommonFunctionality(getApplicationContext(),this);
            commonFunctionality.setTitleBar(R.id.flatSubServices_backButton,R.id.flatSubServices_titleBar,R.id.flatSubServices_homeButton,Constants.TITLE_FLAT_SUB_SERVICE);
            commonFunctionality.onClickListenerForImage(R.id.flatSubServices_backButton);
            commonFunctionality.onClickListenerForImage(R.id.flatSubServices_homeButton);


            String services[] = {Constants.FLAT_SUB_SERVICE_ENTRANCE, Constants.FLAT_SUB_SERVICE_COMMON_AREA, Constants.FLAT_SUB_SERVICE_KITCHEN, Constants.FLAT_SUB_SERVICE_BEDROOM,Constants.FLAT_SUB_SERVICE_WASHROOM};
            ListAdapter listAdapter = new OptionFlatSubServiceAdaptor(this, services);
            ListView listView = (ListView) findViewById(R.id.flatSubServices_listView);
            if (listView != null) {
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener(

                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Intent intent = new Intent(getApplicationContext(), SelectItemsForFlat.class);
                                intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
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

        commonFunctionality.onBackPressed(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY);
    }

    public void onClickBackButton(View view){

        commonFunctionality.onBackPressed(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY);
    }

    public void onClickHomeButton(View view){

        commonFunctionality.onClickHomeButton();
    }
}
