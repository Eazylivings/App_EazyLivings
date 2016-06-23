package com.eazylivings.activities.services.cooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class CookForVegOrNonVeg extends AppCompatActivity {

    int clickedSelection;
    String selectedVegOrNonVeg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_for_veg_or_non_veg);
        try {
            onClickListenerForImage(R.id.cookForVegOrNonVeg_imageView_veg);
            onClickListenerForImage(R.id.cookForVegOrNonVeg_imageView_nonVeg);
            onClickListenerForImage(R.id.cookForVegOrNonVeg_imageView_both);

            CommonFunctionality commonFunctionality = new CommonFunctionality(this);
            commonFunctionality.setTitleBar(R.id.cookForVegOrNonVeg_backButton, R.id.cookForVegOrNonVeg_titleBar, R.id.cookForVegOrNonVeg_homeButton, Constants.TITLE_COOK_FOR_VEG_OR_NON_VEG);

            setHeightAndWidth();

            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                clickedSelection = bundle.getInt(Constants.SHARED_PREFERENCE_CLICKED_SERVICE);
            }
        } catch (Exception e) {
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), PreferredWayOfCooking.class);
        startActivity(intent);
        finish();
    }

    public void onClickBackButton(View view) {

        Intent intent = new Intent(getApplicationContext(), PreferredWayOfCooking.class);
        startActivity(intent);
        finish();
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
            generatePopupMessage(Constants.EXCEPTION_COOK_FOR_VEG_OR_NON_VEG);
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

    public void onClickHomeButton(View view) {

        Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
        startActivity(intent);
        finish();
    }

    public void onClickSelectCook(View view) {

        if (selectedVegOrNonVeg.equalsIgnoreCase("")) {
            generatePopupMessage(Constants.COOK_FOR_VEG_NON_VEG_SELECT_CHOICE);
        } else {
            SharedPreference preference = new SharedPreference(getApplicationContext());
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

            vegCook.requestLayout();
            nonVegCook.requestLayout();
            bothCook.requestLayout();
        } catch (Exception e) {
            generatePopupMessage(Constants.EXCEPTION_SETTING_WIDTH_HEIGHT);
        }

    }

    private void generatePopupMessage(String message) {
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

    private void onClickListenerForImage(int imageid){

        ImageView imageView = (ImageView) findViewById(imageid);

        if (imageView != null) {

            imageView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            ImageView view = (ImageView) v;
                            //overlay is black with transparency of 0x77 (119)
                            view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                            view.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            ImageView view = (ImageView) v;
                            //clear the overlay
                            view.getDrawable().clearColorFilter();
                            view.invalidate();
                            break;
                        }
                    }

                    return false;
                }
            });
        }

    }
}




