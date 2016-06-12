package com.eazylivings.activities.services.cooking;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.eazylivings.R;
import com.eazylivings.constant.Constants;

public class CookForVegOrNonVeg extends Activity {

    int clickedSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_for_veg_or_non_veg);
        try {

            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setIcon(android.R.color.transparent);
                setTitle(Constants.TITLE_COOK_FOR_VEG_OR_NON_VEG);
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(Constants.BLUE_COLOR)));
            }
            setHeightAndWidth();

            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                clickedSelection = bundle.getInt(Constants.SHARED_PREFERENCE_CLICKED_SERVICE);
            }
        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(getApplicationContext(),PreferredWayOfCooking.class);
        startActivity(intent);
        finish();
    }

    //Back button control on Title bar
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), PreferredWayOfCooking.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
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
                    }
                    break;
                case R.id.cookForVegOrNonVeg_radioButton_nonVeg:
                    if (nonVegRadio != null && checked) {
                    }
                    break;

                case R.id.cookForVegOrNonVeg_radioButton_both:
                    if (bothRadio != null && checked) {
                    }
                    break;
            }
        } catch (Exception e) {
            generatePopupMessage(Constants.EXCEPTION_COOK_FOR_VEG_OR_NON_VEG);
        }
    }

        public void onClickImage(View view){

            RadioButton vegRadio = (RadioButton) findViewById(R.id.cookForVegOrNonVeg_radioButton_veg);
            RadioButton nonVegRadio = (RadioButton) findViewById(R.id.cookForVegOrNonVeg_radioButton_nonVeg);
            RadioButton bothRadio = (RadioButton) findViewById(R.id.cookForVegOrNonVeg_radioButton_both);

            switch(view.getId()){

                case R.id.cookForVegOrNonVeg_imageView_veg:
                    vegRadio.setChecked(true);
                    nonVegRadio.setChecked(false);
                    bothRadio.setChecked(false);
                    break;
                case R.id.cookForVegOrNonVeg_imageView_nonVeg:
                    nonVegRadio.setChecked(true);
                    vegRadio.setChecked(false);
                    bothRadio.setChecked(false);
                    break;

                case R.id.cookForVegOrNonVeg_imageView_both:
                    bothRadio.setChecked(true);
                    vegRadio.setChecked(false);
                    nonVegRadio.setChecked(false);
                    break;
            }

    }

    public void onClickSelectCook(View view){

        Intent intent=new Intent(getApplicationContext(),SelectCook.class);
        startActivity(intent);
        finish();

    }

    private void setHeightAndWidth(){

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        ImageView vegCook=(ImageView)findViewById(R.id.cookForVegOrNonVeg_imageView_veg);
        vegCook.getLayoutParams().width = width/4;
        ImageView nonVegCook=(ImageView)findViewById(R.id.cookForVegOrNonVeg_imageView_nonVeg);
        nonVegCook.getLayoutParams().width = width/4;
        ImageView bothCook=(ImageView)findViewById(R.id.cookForVegOrNonVeg_imageView_both);
        bothCook.getLayoutParams().width = width/4;

        vegCook.requestLayout();
        nonVegCook.requestLayout();
        bothCook.requestLayout();

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

