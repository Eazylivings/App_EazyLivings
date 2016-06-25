package com.eazylivings.activities.services.cooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.eazylivings.R;
import com.eazylivings.adapter.SelectGroceryItemsAdaptor;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class SelectGroceryItems extends AppCompatActivity {

    CommonFunctionality commonFunctionality;
    SharedPreference preference;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_grocery_items);

        try {

            preference=new SharedPreference(getApplicationContext());

            commonFunctionality = new CommonFunctionality(getApplicationContext(),this);
            commonFunctionality.setTitleBar(R.id.selectGroceryItems_backButton, R.id.selectGroceryItems_titleBar, R.id.selectGroceryItems_homeButton, Constants.TITLE_SELECT_GROCERY_ITEMS);
            commonFunctionality.onClickListenerForImage(R.id.selectGroceryItems_backButton);
            commonFunctionality.onClickListenerForImage(R.id.selectGroceryItems_homeButton);

            ListAdapter listAdapter = new SelectGroceryItemsAdaptor(this, null);
            ListView listView = (ListView) findViewById(R.id.selectGroceryItems_listView);
            if (listView != null) {
                listView.setAdapter(listAdapter);
                listView.setOnItemClickListener(

                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREFERRED_WAY_OF_COOKING,String.valueOf(position));
                                preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY,Constants.ACTIVITY_SELECT_GROCERY_LIST);
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

        commonFunctionality.onBackPressed(Constants.ACTIVITY_CONFIGURE_GROCERY_LIST);
    }

    public void onClickBackButton(View view){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_CONFIGURE_GROCERY_LIST);
    }

    public void onClickHomeButton(View view){

        commonFunctionality.onClickHomeButton();
    }

}
