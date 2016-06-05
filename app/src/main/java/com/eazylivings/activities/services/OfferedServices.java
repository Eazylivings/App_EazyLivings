package com.eazylivings.activities.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.eazylivings.ServiceImageDescriptionAdaptor;

import com.eazylivings.R;
import com.eazylivings.constant.Constants;

public class OfferedServices extends AppCompatActivity {


    String clickedService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_services);

        setTitle(Constants.OFFERED_SERVICES_TITLE);

        String services[] = {"Flat Setup","Cooking","Cleaning", "Washing"};
        ListAdapter listAdapter = new ServiceImageDescriptionAdaptor(this, services);
        ListView listView = (ListView) findViewById(R.id.offeredServices_listView_servicesList);
        if (listView != null) {
            listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        clickedService = String.valueOf(parent.getItemAtPosition(position));
                        Intent intent = new Intent(getApplicationContext(), WalkthroughServices.class);
                        intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
                        startActivity(intent);

                    }
                }

        );
    }

    }
}
