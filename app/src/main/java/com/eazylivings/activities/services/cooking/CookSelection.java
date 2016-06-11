package com.eazylivings.activities.services.cooking;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.eazylivings.R;
import com.eazylivings.activities.login.SignIn;
import com.eazylivings.constant.Constants;

public class CookSelection extends Activity {

    int clickedSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_selection);
        try {

            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setIcon(android.R.color.transparent);
                setTitle(Constants.COOK_SELECTION_TITLE);
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

        Intent intent = new Intent(getApplicationContext(),ChoiceOfCooking.class);
        startActivity(intent);
        finish();
    }

    //Back button control on Title bar
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ChoiceOfCooking.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }

    public void onClickRadioButton(View view){

        try {

            RadioButton vegRadio = (RadioButton) findViewById(R.id.cookSelection_radioButton_veg);
            RadioButton nonVegRadio = (RadioButton) findViewById(R.id.cookSelection_radioButton_nonVeg);
            RadioButton bothRadio = (RadioButton) findViewById(R.id.cookSelection_radioButton_both);

            boolean checked = ((RadioButton) view).isChecked();
            switch (view.getId()) {
                case R.id.cookSelection_radioButton_veg:
                    if (vegRadio != null && checked) {
                    }
                    break;
                case R.id.cookSelection_radioButton_nonVeg:
                    if (nonVegRadio != null && checked) {
                    }
                    break;

                case R.id.cookSelection_radioButton_both:
                    if (bothRadio != null && checked) {
                    }
                    break;
            }
        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_COOK_SELECTION);
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

        ImageView vegCook=(ImageView)findViewById(R.id.cookSelection_imageView_veg);
        vegCook.getLayoutParams().width = width/4;
        ImageView nonVegCook=(ImageView)findViewById(R.id.cookSelection_imageView_nonVeg);
        nonVegCook.getLayoutParams().width = width/4;
        ImageView bothCook=(ImageView)findViewById(R.id.cookSelection_imageView_both);
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

