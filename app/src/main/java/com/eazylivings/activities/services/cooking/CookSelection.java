package com.eazylivings.activities.services.cooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.eazylivings.R;
import com.eazylivings.activities.login.SignIn;
import com.eazylivings.constant.Constants;

public class CookSelection extends AppCompatActivity {

    int clickedSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_selection);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            clickedSelection = bundle.getInt(Constants.SHARED_PREFERENCE_CLICKED_SERVICE);
        }
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(getApplicationContext(),ChoiceOfCooking.class);
        startActivity(intent);
        finish();
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
            generatePopupMessage("Exception Occurred");
        }


    }
    public void onClickSelectCook(View view){

        Intent intent=new Intent(getApplicationContext(),SelectCook.class);
        startActivity(intent);
        finish();

    }

    private void generatePopupMessage(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(CookSelection.this).create();
        alertDialog.setTitle("Alert");
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

