package com.eazylivings.activities.services.cooking;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.activities.services.WalkthroughServices;
import com.eazylivings.adapter.CookingChoiceAdaptor;
import com.eazylivings.constant.Constants;

public class ChoiceOfCooking extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_of_cooking);
        try {
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setIcon(android.R.color.transparent);
                setTitle(Constants.CHOICE_OF_COOKING_TITLE);
            }

            String services[] = {"0", "1", "2"};
            ListAdapter listAdapter = new CookingChoiceAdaptor(this, services);
            ListView listView = (ListView) findViewById(R.id.choiceOfCooking_listView);
            if (listView != null) {
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener(

                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                if (position == 0) {

                                    Intent intent = new Intent(getApplicationContext(), CookSelection.class);
                                    intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
                                    intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
                                    startActivity(intent);
                                }
                            }
                        }
                );
            }
        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), WalkthroughServices.class);
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
