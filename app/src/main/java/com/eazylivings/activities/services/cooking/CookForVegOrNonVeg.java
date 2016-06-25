package com.eazylivings.activities.services.cooking;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.eazylivings.R;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class CookForVegOrNonVeg extends AppCompatActivity {

    int clickedSelection;
    String selectedVegOrNonVeg = "";
    CommonFunctionality commonFunctionality;
    SharedPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_for_veg_or_non_veg);
        try {

            preference=new SharedPreference(getApplicationContext());

            commonFunctionality = new CommonFunctionality(getApplicationContext(),this);
            commonFunctionality.setTitleBar(R.id.cookForVegOrNonVeg_backButton, R.id.cookForVegOrNonVeg_titleBar, R.id.cookForVegOrNonVeg_homeButton, Constants.TITLE_COOK_FOR_VEG_OR_NON_VEG);
            commonFunctionality.onClickListenerForImage(R.id.cookForVegOrNonVeg_imageView_veg);
            commonFunctionality.onClickListenerForImage(R.id.cookForVegOrNonVeg_imageView_nonVeg);
            commonFunctionality.onClickListenerForImage(R.id.cookForVegOrNonVeg_imageView_both);
            commonFunctionality.onClickListenerForImage(R.id.cookForVegOrNonVeg_backButton);
            commonFunctionality.onClickListenerForImage(R.id.cookForVegOrNonVeg_homeButton);


            setHeightAndWidth();

            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                clickedSelection = bundle.getInt(Constants.SHARED_PREFERENCE_CLICKED_SERVICE);
            }
        } catch (Exception e) {
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed() {

        commonFunctionality.onBackPressed(Constants.ACTIVITY_PREFERRED_WAY_OF_COOKING);
    }

    public void onClickBackButton(View view) {

        commonFunctionality.onBackPressed(Constants.ACTIVITY_PREFERRED_WAY_OF_COOKING);
    }

    public void onClickHomeButton(View view) {

        commonFunctionality.onClickHomeButton();
    }

    public void onClickRadioButton(View view) {

        try {

            RadioButton vegRadio = (RadioButton) findViewById(R.id.cookForVegOrNonVeg_radioButton_veg);
            RadioButton nonVegRadio = (RadioButton) findViewById(R.id.cookForVegOrNonVeg_radioButton_nonVeg);
            RadioButton bothRadio = (RadioButton) findViewById(R.id.cookForVegOrNonVeg_radioButton_both);

            boolean checked = ((RadioButton) view).isChecked();
            switch (view.getId()) {
                case R.id.cookForVegOrNonVeg_radioButton_veg:
                    if (vegRadio != null && checked) {
                        selectedVegOrNonVeg = "veg";
                    }
                    break;
                case R.id.cookForVegOrNonVeg_radioButton_nonVeg:
                    if (nonVegRadio != null && checked) {
                        selectedVegOrNonVeg = "nonVeg";
                    }
                    break;

                case R.id.cookForVegOrNonVeg_radioButton_both:
                    if (bothRadio != null && checked) {
                        selectedVegOrNonVeg = "both";
                    }
                    break;
            }
        } catch (Exception e) {
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_COOK_FOR_VEG_OR_NON_VEG);
        }
    }

    public void onClickImage(View view) {

        RadioButton vegRadio = (RadioButton) findViewById(R.id.cookForVegOrNonVeg_radioButton_veg);
        RadioButton nonVegRadio = (RadioButton) findViewById(R.id.cookForVegOrNonVeg_radioButton_nonVeg);
        RadioButton bothRadio = (RadioButton) findViewById(R.id.cookForVegOrNonVeg_radioButton_both);

        switch (view.getId()) {

            case R.id.cookForVegOrNonVeg_imageView_veg:
                if(vegRadio!=null && nonVegRadio!=null && bothRadio!=null) {
                    vegRadio.setChecked(true);
                    nonVegRadio.setChecked(false);
                    bothRadio.setChecked(false);
                    selectedVegOrNonVeg = "veg";
                }

                break;
            case R.id.cookForVegOrNonVeg_imageView_nonVeg:
                if(vegRadio!=null && nonVegRadio!=null && bothRadio!=null) {
                    nonVegRadio.setChecked(true);
                    vegRadio.setChecked(false);
                    bothRadio.setChecked(false);
                    selectedVegOrNonVeg = "nonVeg";
                }

                break;

            case R.id.cookForVegOrNonVeg_imageView_both:
                if(vegRadio!=null && nonVegRadio!=null && bothRadio!=null) {
                    bothRadio.setChecked(true);
                    vegRadio.setChecked(false);
                    nonVegRadio.setChecked(false);
                    selectedVegOrNonVeg = "both";
                }
                break;
        }
    }

    public void onClickSelectCook(View view) {

        if (selectedVegOrNonVeg.equalsIgnoreCase("")) {
            commonFunctionality.generatePopupMessage(Constants.COOK_FOR_VEG_NON_VEG_SELECT_CHOICE);
        } else {

            preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_SELECTED_VEG_NON_VEG, selectedVegOrNonVeg);

            Intent intent = new Intent(getApplicationContext(), CookSelection.class);
            startActivity(intent);
            finish();
        }
    }

    private void setHeightAndWidth() {

        try {

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

            //Set Cook images Height and Width
            ImageView vegCook = (ImageView) findViewById(R.id.cookForVegOrNonVeg_imageView_veg);
            if(vegCook!=null) {
                vegCook.getLayoutParams().width = width / 4;
                vegCook.getLayoutParams().height = height / 2;
            }
            ImageView nonVegCook = (ImageView) findViewById(R.id.cookForVegOrNonVeg_imageView_nonVeg);
            if(nonVegCook!=null) {
                nonVegCook.getLayoutParams().width = width / 4;
                nonVegCook.getLayoutParams().height = height / 2;
            }
            ImageView bothCook = (ImageView) findViewById(R.id.cookForVegOrNonVeg_imageView_both);
            if(bothCook!=null) {
                bothCook.getLayoutParams().width = width / 4;
                bothCook.getLayoutParams().height = height / 2;
            }

            if(vegCook!=null && nonVegCook!=null && bothCook!=null) {

                vegCook.requestLayout();
                nonVegCook.requestLayout();
                bothCook.requestLayout();
            }
        } catch (Exception e) {
            commonFunctionality.generatePopupMessage(Constants.EXCEPTION_SETTING_WIDTH_HEIGHT);
        }
    }
}




