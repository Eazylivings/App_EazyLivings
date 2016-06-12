package com.eazylivings.activities.services.flatsetup;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.eazylivings.R;
import com.eazylivings.activities.services.OfferedServices;
import com.eazylivings.activities.services.WalkthroughServices;
import com.eazylivings.adapter.OptionFlatSubServiceAdaptor;
import com.eazylivings.adapter.ServicesOfferedAdaptor;
import com.eazylivings.constant.Constants;

public class FlatSubServices extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_sub_services);

        try {
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setIcon(android.R.color.transparent);
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(Constants.BLUE_COLOR)));
                setTitle(Constants.TITLE_FLAT_SUB_SERVICE);
            }

            String services[] = {"0", "1", "2", "3","4"};
            ListAdapter listAdapter = new OptionFlatSubServiceAdaptor(this, services);
            ListView listView = (ListView) findViewById(R.id.flatSubServices_listView);
            if (listView != null) {
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener(

                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Intent intent = new Intent(getApplicationContext(), SelectItemsForFlat.class);
                                intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
                                startActivity(intent);
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

        Intent intent = new Intent(getApplicationContext(),OfferedServices.class);
        startActivity(intent);
        finish();
    }

    //Back button control on Title bar
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), OfferedServices.class);
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
