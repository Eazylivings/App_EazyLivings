package com.eazylivings.activities.services;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.adapter.ServiceImageDescriptionAdaptor;

import com.eazylivings.R;
import com.eazylivings.constant.Constants;

public class OfferedServices extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_services);


        ActionBar actionBar = getActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setIcon(android.R.color.transparent);
            setTitle(Constants.OFFERED_SERVICES_TITLE);
        }


        String services[] = {"Flat Setup","Cooking","Cleaning", "Washing"};
        ListAdapter listAdapter = new ServiceImageDescriptionAdaptor(this, services);
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
                    }
                }
        );
    }

    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), WelcomeScreen.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }
}
