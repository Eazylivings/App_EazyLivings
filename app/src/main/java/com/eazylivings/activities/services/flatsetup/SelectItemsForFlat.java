package com.eazylivings.activities.services.flatsetup;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.eazylivings.R;
import com.eazylivings.activities.login.SignIn;
import com.eazylivings.constant.Constants;

public class SelectItemsForFlat extends Activity {

    int clickedSubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_items_for_flat);

        try {


            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setIcon(android.R.color.transparent);
                setTitle(Constants.TITLE_SELECT_ITEMS_FOR_FLAT);
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(Constants.BLUE_COLOR)));
            }

            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                clickedSubService = bundle.getInt(Constants.SHARED_PREFERENCE_CLICKED_SERVICE);
            }

        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(getApplicationContext(),FlatSubServices.class);
        startActivity(intent);
        finish();
    }

    //Back button control on Title bar
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), FlatSubServices.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
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
