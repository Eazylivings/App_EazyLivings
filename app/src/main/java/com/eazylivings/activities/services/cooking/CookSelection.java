package com.eazylivings.activities.services.cooking;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_selection);

        try {

            CommonFunctionality commonFunctionality=new CommonFunctionality(this);
            commonFunctionality.setTitleBar(R.id.cookSelection_backButton,R.id.cookSelection_titleBar,R.id.cookSelection_homeButton,Constants.TITLE_COOK_SELECTION);

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
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(getApplicationContext(),CookForVegOrNonVeg.class);
        startActivity(intent);
        finish();
    }

    public void onClickBackButton(View view){

        Intent intent=new Intent(getApplicationContext(),CookForVegOrNonVeg.class);
        startActivity(intent);
        finish();
    }

    public void onClickHomeButton(View view){

        Intent intent=new Intent(getApplicationContext(),WelcomeScreen.class);
        startActivity(intent);
        finish();
    }

   public void onClickContinue(View view){

       CheckBox breakfast=(CheckBox)findViewById(R.id.cookSelection_checkBox_breakfast);
       CheckBox lunch=(CheckBox)findViewById(R.id.cookSelection_checkBox_lunch);
       CheckBox dinner=(CheckBox)findViewById(R.id.cookSelection_checkBox_dinner);
       if(breakfast!=null && lunch!=null && dinner!=null) {

           if (breakfast.isChecked() && lunch.isChecked()) {

               SharedPreference preference = new SharedPreference(getApplicationContext());
               preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_COOK_CHOICE, String.valueOf(selectedCook));
               preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_MEALS_CHOICE,Constants.COOK_SELECTION_BREAKFAST_LUNCH);
               Intent intent = new Intent(getApplicationContext(), CookingFinalScreen.class);
               startActivity(intent);
               finish();
           } else if (breakfast.isChecked() && dinner.isChecked()) {
               SharedPreference preference = new SharedPreference(getApplicationContext());
               preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_COOK_CHOICE, String.valueOf(selectedCook));
               preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_MEALS_CHOICE,Constants.COOK_SELECTION_BREAKFAST_DINNER);
               Intent intent = new Intent(getApplicationContext(), CookingFinalScreen.class);
               startActivity(intent);
               finish();
           } else if (lunch.isChecked() && dinner.isChecked()) {
               SharedPreference preference = new SharedPreference(getApplicationContext());
               preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_COOK_CHOICE, String.valueOf(selectedCook));
               preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_MEALS_CHOICE,Constants.COOK_SELECTION_LUNCH_DINNER);
               Intent intent = new Intent(getApplicationContext(), CookingFinalScreen.class);
               startActivity(intent);
               finish();

           }else if (breakfast.isChecked()&& lunch.isChecked() && dinner.isChecked()) {
               SharedPreference preference = new SharedPreference(getApplicationContext());
               preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_COOK_CHOICE, String.valueOf(selectedCook));
               preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_MEALS_CHOICE,Constants.COOK_SELECTION_BREAKFAST_LUNCH_DINNER);
               Intent intent = new Intent(getApplicationContext(), CookingFinalScreen.class);
               startActivity(intent);
               finish();

           } else {
               generatePopupMessage(Constants.CHECK_BOX_SELECTION);
           }
       }else{
           generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
       }

    }






    private void generatePopupMessage(String message){
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
