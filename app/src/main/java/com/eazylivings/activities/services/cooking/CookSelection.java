package com.eazylivings.activities.services.cooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.eazylivings.R;
import com.eazylivings.adapter.CooksInformationAdaptor;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CookSelection extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<HashMap<String,String>> listDataHeader= new ArrayList<>();
    HashMap<String, String> map=new HashMap<>();
    int selectedCook;
    CommonFunctionality commonFunctionality;
    SharedPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_selection);

        try {
            preference=new SharedPreference(getApplicationContext());

            commonFunctionality=new CommonFunctionality(getApplicationContext(),this);
            commonFunctionality.setTitleBar(R.id.cookSelection_backButton,R.id.cookSelection_titleBar,R.id.cookSelection_homeButton,Constants.TITLE_COOK_SELECTION);
            commonFunctionality.onClickListenerForImage(R.id.cookSelection_backButton);
            commonFunctionality.onClickListenerForImage(R.id.cookSelection_homeButton);

            map.put("name","Shwetang");
            map.put("gender","Male");
            map.put("age","24");
            map.put("homeTown","New Delhi");
            map.put("description","Working as a Application Developer in Oracle");
            map.put("specialization","Eating maggie");

            listDataHeader.add(map);

            map=new HashMap<>();

            map.put("name","Kavita");
            map.put("gender","Female");
            map.put("age","24");
            map.put("homeTown","Rourkela");
            map.put("description","Working as a Software Engineer in TCS");
            map.put("specialization","Eating pasta");

            listDataHeader.add(map);

            map=new HashMap<>();

            map.put("name","Prudhvi");
            map.put("gender","Male");
            map.put("age","24");
            map.put("homeTown","Satupalli");
            map.put("description","Working as a Software Engineer in TCS");
            map.put("specialization","Eating maggie");
            listDataHeader.add(map);


            listAdapter = new CooksInformationAdaptor(this, listDataHeader);
            expListView= (ExpandableListView) findViewById(R.id.cookSelection_listView);
            if (expListView != null) {
                expListView.setAdapter(listAdapter);

                expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v,
                                                int groupPosition, int childPosition, long id) {

                        selectedCook=groupPosition;
                        return true;
                    }
                });
            }

        }catch(Exception e){
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_COOK_FOR_VEG_NON_VEG);
    }

    public void onClickBackButton(View view){

        commonFunctionality.onBackPressed(Constants.ACTIVITY_COOK_FOR_VEG_NON_VEG);
    }

    public void onClickHomeButton(View view){

        commonFunctionality.onClickHomeButton();
    }

   public void onClickContinue(View view){

       boolean isUserLoggedIn=preference.getBooleanValueFromSharedPreference(Constants.SHARED_PREFERENCE_LOGIN_STATUS);
       preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY,Constants.ACTIVITY_COOK_SELECTION);
       if(isUserLoggedIn){

           CheckBox breakfast=(CheckBox)findViewById(R.id.cookSelection_checkBox_breakfast);
           CheckBox lunch=(CheckBox)findViewById(R.id.cookSelection_checkBox_lunch);
           CheckBox dinner=(CheckBox)findViewById(R.id.cookSelection_checkBox_dinner);
           if(breakfast!=null && lunch!=null && dinner!=null) {

               if (breakfast.isChecked() && lunch.isChecked()) {

                   preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_COOK_CHOICE, String.valueOf(selectedCook));
                   preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_MEALS_CHOICE,Constants.COOK_SELECTION_BREAKFAST_LUNCH);

                   Intent intent = new Intent(getApplicationContext(), ConfigureGroceryList.class);
                   startActivity(intent);
                   finish();
               } else if (breakfast.isChecked() && dinner.isChecked()) {

                   preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_COOK_CHOICE, String.valueOf(selectedCook));
                   preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_MEALS_CHOICE,Constants.COOK_SELECTION_BREAKFAST_DINNER);

                   Intent intent = new Intent(getApplicationContext(), ConfigureGroceryList.class);
                   startActivity(intent);
                   finish();
               } else if (lunch.isChecked() && dinner.isChecked()) {

                   preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_COOK_CHOICE, String.valueOf(selectedCook));
                   preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_MEALS_CHOICE,Constants.COOK_SELECTION_LUNCH_DINNER);

                   Intent intent = new Intent(getApplicationContext(), ConfigureGroceryList.class);
                   startActivity(intent);
                   finish();

               }else if (breakfast.isChecked()&& lunch.isChecked() && dinner.isChecked()) {

                   preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_COOK_CHOICE, String.valueOf(selectedCook));
                   preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_MEALS_CHOICE,Constants.COOK_SELECTION_BREAKFAST_LUNCH_DINNER);

                   Intent intent = new Intent(getApplicationContext(), ConfigureGroceryList.class);
                   startActivity(intent);
                   finish();

               } else {
                   commonFunctionality.generatePopupMessage(Constants.CHECK_BOX_SELECTION);
               }
           }else{
               commonFunctionality.generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
           }

       }else{
           commonFunctionality.generateLoginPopupMessage(Constants.LOGIN_TO_PROCEED);
       }
    }
}
