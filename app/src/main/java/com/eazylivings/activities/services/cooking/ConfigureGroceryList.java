package com.eazylivings.activities.services.cooking;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;

import com.eazylivings.R;
import com.eazylivings.constant.Constants;

public class ConfigureGroceryList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_grocery_list);

        try {
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setIcon(android.R.color.transparent);
                setTitle(Constants.CONFIGURE_GROCERY_LIST_TITLE);
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(Constants.BLUE_COLOR)));
            }
        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(getApplicationContext(),SelectCook.class);
        startActivity(intent);
        finish();
    }

    //Back button control on Title bar
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), SelectCook.class);
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
