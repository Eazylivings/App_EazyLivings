package com.eazylivings.activities.services.cooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.eazylivings.R;
import com.eazylivings.adapter.PreferredWayOfCookingAdaptor;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class PreferredWayOfCooking extends AppCompatActivity {

    CommonFunctionality commonFunctionality;
    SharedPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred_way_of_cooking);
        try {
            preference=new SharedPreference(getApplicationContext());
            preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY,Constants.ACTIVITY_PREFERRED_WAY_OF_COOKING);

            commonFunctionality=new CommonFunctionality(getApplicationContext(),this);
            commonFunctionality.setTitleBar(R.id.preferredWayOfCooking_backButton,R.id.preferredWayOfCooking_titleBar,R.id.preferredWayOfCooking_homeButton,Constants.TITLE_PREFERRED_WAY_OF_COOKING);
            commonFunctionality.onClickListenerForImage(R.id.preferredWayOfCooking_backButton);
            commonFunctionality.onClickListenerForImage(R.id.preferredWayOfCooking_homeButton);


            String services[] = {"0", "1", "2"};
            ListAdapter listAdapter = new PreferredWayOfCookingAdaptor(this, services);
            ListView listView = (ListView) findViewById(R.id.preferredWayOfCooking_listView);
            if (listView != null) {
                listView.setAdapter(listAdapter);
                listView.setOnItemClickListener(

                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREFERRED_WAY_OF_COOKING,String.valueOf(position));

                                if (position == 0) {

                                    Intent intent = new Intent(getApplicationContext(), CookForVegOrNonVeg.class);
                                    intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
                                    startActivity(intent);
                                    finish();
                                }else if (position == 1) {

                                    Intent intent = new Intent(getApplicationContext(), ConfigureGroceryList.class);
                                    intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY,Constants.ACTIVITY_PREFERRED_WAY_OF_COOKING);
                                    Intent intent = new Intent(getApplicationContext(), ConfigureGroceryList.class);
                                    intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                );
            }
        }catch(Exception e){
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    public void onClickHomeButton(View view){

        commonFunctionality.onClickHomeButton();
    }

    @Override
    public void onBackPressed(){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_OFFERED_SERVICE);
    }

    public void onClickBackButton(View view){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_OFFERED_SERVICE);
    }
}
