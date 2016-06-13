package com.eazylivings.activities.services;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.adapter.ServicesOfferedAdaptor;

import com.eazylivings.R;
import com.eazylivings.constant.Constants;

public class OfferedServices extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_services);

        try {


            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setIcon(android.R.color.transparent);
                setTitle(Constants.OFFERED_SERVICES_TITLE);
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(Constants.BLUE_COLOR)));
            }


            String services[] = {"0", "1", "2", "3"};
            ListAdapter listAdapter = new ServicesOfferedAdaptor(this, services);
            ListView listView = (ListView) findViewById(R.id.offeredServices_listView_servicesList);
            if (listView != null) {
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener(

                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Intent intent = new Intent(getApplicationContext(), WalkthroughServices.class);
                                intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
                                startActivity(intent);
                                finish();
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

        Intent intent = new Intent(getApplicationContext(),WelcomeScreen.class);
        startActivity(intent);
        finish();
    }
    //Back button control on Title bar
    public boolean onOptionsItemSelected(MenuItem item){

        Intent myIntent = new Intent(getApplicationContext(), WelcomeScreen.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;

    }

    private void generatePopupMessage(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create(); //Use context
        alertDialog.setTitle(Constants.ALERT_WARNING);
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
