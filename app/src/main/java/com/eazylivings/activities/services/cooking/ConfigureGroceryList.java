package com.eazylivings.activities.services.cooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;

import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class ConfigureGroceryList extends AppCompatActivity {

    String previousActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_grocery_list);

        try {

            SharedPreference preference=new SharedPreference(getApplicationContext());
            previousActivity=preference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY);
            if(previousActivity.equalsIgnoreCase(Constants.ACTIVITY_PREFERRED_WAY_OF_COOKING)){
                TextView skipView=(TextView)findViewById(R.id.configureGroceryList_skip);
                if(skipView!=null){
                    skipView.setVisibility(View.INVISIBLE);
                }
            }

            CommonFunctionality commonFunctionality=new CommonFunctionality(this);
            commonFunctionality.setTitleBar(R.id.configureGroceryList_backButton,R.id.configureGroceryList_titleBar,R.id.configureGroceryList_homeButton,Constants.TITLE_CONFIGURE_GROCERY_LIST);



        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        if(previousActivity.equalsIgnoreCase(Constants.ACTIVITY_COOK_SELECTION)){
            Intent intent = new Intent(getApplicationContext(),CookSelection.class);
            startActivity(intent);
            finish();

        }else if(previousActivity.equalsIgnoreCase(Constants.ACTIVITY_PREFERRED_WAY_OF_COOKING)){
            Intent intent = new Intent(getApplicationContext(),PreferredWayOfCooking.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClickBackButton(View view){

        if(previousActivity.equalsIgnoreCase(Constants.ACTIVITY_COOK_SELECTION)){
            Intent intent = new Intent(getApplicationContext(),CookSelection.class);
            startActivity(intent);
            finish();

        }else if(previousActivity.equalsIgnoreCase(Constants.ACTIVITY_PREFERRED_WAY_OF_COOKING)){
            Intent intent = new Intent(getApplicationContext(),PreferredWayOfCooking.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClickHomeButton(View view){

        Intent intent=new Intent(getApplicationContext(),WelcomeScreen.class);
        startActivity(intent);
        finish();
    }

    public void onClickMainCategory(View view){

        int selectedCategory=view.getId();
        Intent intent=new Intent(getApplicationContext(),SelectGroceryItems.class);

        if(selectedCategory==R.id.configureGroceryList_imageView_vegetables || selectedCategory==R.id.configureGroceryList_textView_vegetables){
            intent.putExtra(Constants.GROCERY_LIST_MAIN_CATEGORY,Constants.GROCERY_LIST_VEGETABLES);
            startActivity(intent);

        }else if(selectedCategory==R.id.configureGroceryList_imageView_fruits || selectedCategory==R.id.configureGroceryList_textView_fruits){
            intent.putExtra(Constants.GROCERY_LIST_MAIN_CATEGORY,Constants.GROCERY_LIST_MAIN_FRUITS);
            startActivity(intent);

        }else if(selectedCategory==R.id.configureGroceryList_imageView_wheatAndFlour || selectedCategory==R.id.configureGroceryList_textView_wheatAndFlour){
            intent.putExtra(Constants.GROCERY_LIST_MAIN_CATEGORY,Constants.GROCERY_LIST_MAIN_WHEAT_AND_FLOUR);
            startActivity(intent);

        }else if(selectedCategory==R.id.configureGroceryList_imageView_masaale || selectedCategory==R.id.configureGroceryList_textView_masaale){
            intent.putExtra(Constants.GROCERY_LIST_MAIN_CATEGORY,Constants.GROCERY_LIST_MAIN_MASALAE);
            startActivity(intent);

        }

    }

    public void onClickSkip(View view){

        SharedPreference preference=new SharedPreference(getApplicationContext());
        preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY,Constants.ACTIVITY_CONFIGURE_GROCERY_LIST);

        Intent intent=new Intent(getApplicationContext(),CookingFinalScreen.class);
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
