package com.eazylivings.activities.services.cooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.adapter.SelectGroceryItemsAdaptor;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class SelectGroceryItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_grocery_items);

        try {

            String[] testArray={"w","e","r"};

            CommonFunctionality commonFunctionality = new CommonFunctionality(this);
            commonFunctionality.setTitleBar(R.id.selectGroceryItems_backButton, R.id.selectGroceryItems_titleBar, R.id.selectGroceryItems_homeButton, Constants.TITLE_SELECT_GROCERY_ITEMS);
            commonFunctionality.onClickListenerForImage(R.id.selectGroceryItems_backButton);
            commonFunctionality.onClickListenerForImage(R.id.selectGroceryItems_homeButton);

            ListAdapter listAdapter = new SelectGroceryItemsAdaptor(this, testArray,null);
            ListView listView = (ListView) findViewById(R.id.selectGroceryItems_listView);
            if (listView != null) {
                listView.setAdapter(listAdapter);
                listView.setOnItemClickListener(

                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                SharedPreference preference=new SharedPreference(getApplicationContext());
                                preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREFERRED_WAY_OF_COOKING,String.valueOf(position));


                            }
                        }
                );
            }


        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }

    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(getApplicationContext(),ConfigureGroceryList.class);
        startActivity(intent);
        finish();
    }

    public void onClickBackButton(View view){

        Intent intent=new Intent(getApplicationContext(),ConfigureGroceryList.class);
        startActivity(intent);
        finish();
    }

    public void onClickHomeButton(View view){

        Intent intent=new Intent(getApplicationContext(),WelcomeScreen.class);
        startActivity(intent);
        finish();
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
