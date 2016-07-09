package com.eazylivings.activities.services.cooking;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.TestClass;
import com.eazylivings.adapter.SelectGroceryItemsAdaptor;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SelectGroceryItems extends AppCompatActivity {

    CommonFunctionality commonFunctionality;
    SharedPreference preference;
    Set<String> finalGroceryList=new HashSet<>();
    String clickedMainGroceryCategory="";

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

            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                clickedMainGroceryCategory = bundle.getString(Constants.GROCERY_LIST_MAIN_CATEGORY);
            }

            ListAdapter listAdapter = new SelectGroceryItemsAdaptor(this, TestClass.inputsForGroceryItems());
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


    public void onClickAddToCart(View view){

        String finalString="";
        String selectedRadio="";

        TextView itemName=(TextView)view.findViewById(R.id.selectGroceryItems_textView_itemName);
        TextView itemWeight=(TextView)view.findViewById(R.id.selectGroceryItems_textView_itemWeight);
        TextView itemQuantity=(TextView)view.findViewById(R.id.selectGroceryItems_textView_itemQuantity);
        TextView itemPrice=(TextView)view.findViewById(R.id.selectGroceryItems_textView_itemPrice);
        RadioButton radioButtonWeekly=(RadioButton)findViewById(R.id.selectGroceryItems_radioButton_weekly);
        RadioButton radioButtonMonthly=(RadioButton)findViewById(R.id.selectGroceryItems_radioButton_monthly);
        if(radioButtonWeekly.isChecked()){
            selectedRadio="weekly";
        }else if(radioButtonWeekly.isChecked()){
            selectedRadio="monthly";
        }else{
            commonFunctionality.generatePopupMessage("Please select one.");
        }

        if(itemName!=null && itemWeight!=null && itemQuantity!=null && itemPrice!=null ){

            finalString=finalString.concat(itemName.getText().toString()).concat("_").concat(itemWeight.getText().toString()).concat("_").concat(itemQuantity.getText().toString()).concat("_").concat(selectedRadio);
        }
        finalGroceryList.add(finalString);
    }

    public void onClickContinue(View view){

        preference.setItemList(clickedMainGroceryCategory,finalGroceryList);

    }

    public void onClickViewCart(View view){

        preference.setItemList(clickedMainGroceryCategory,finalGroceryList);

    }
}
