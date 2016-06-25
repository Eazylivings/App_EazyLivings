package com.eazylivings.activities.services.cooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class ConfigureGroceryList extends AppCompatActivity {

    String previousActivity;
    CommonFunctionality commonFunctionality;
    SharedPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_grocery_list);

        try {

            preference=new SharedPreference(getApplicationContext());
            previousActivity=preference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY);
            if(previousActivity.equalsIgnoreCase(Constants.ACTIVITY_PREFERRED_WAY_OF_COOKING)){
                TextView skipView=(TextView)findViewById(R.id.configureGroceryList_skip);
                if(skipView!=null){
                    skipView.setVisibility(View.INVISIBLE);
                }
            }

            commonFunctionality=new CommonFunctionality(getApplicationContext(),this);
            commonFunctionality.setTitleBar(R.id.configureGroceryList_backButton,R.id.configureGroceryList_titleBar,R.id.configureGroceryList_homeButton,Constants.TITLE_CONFIGURE_GROCERY_LIST);
            commonFunctionality.onClickListenerForImage(R.id.configureGroceryList_backButton);
            commonFunctionality.onClickListenerForImage(R.id.configureGroceryList_homeButton);

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

    public void onClickMainCategory(View view){

        int selectedCategory=view.getId();
        Intent intent=new Intent(getApplicationContext(),SelectGroceryItems.class);
        preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY,Constants.ACTIVITY_CONFIGURE_GROCERY_LIST);

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

        preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY,Constants.ACTIVITY_CONFIGURE_GROCERY_LIST);

        Intent intent=new Intent(getApplicationContext(),CookingFinalScreen.class);
        startActivity(intent);
        finish();
    }
}