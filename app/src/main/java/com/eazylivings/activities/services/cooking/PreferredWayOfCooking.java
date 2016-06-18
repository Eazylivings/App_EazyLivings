package com.eazylivings.activities.services.cooking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.activities.services.OfferedServices;
import com.eazylivings.adapter.PreferredWayOfCookingAdaptor;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class PreferredWayOfCooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred_way_of_cooking);
        try {
            CommonFunctionality commonFunctionality=new CommonFunctionality(this);
            commonFunctionality.setTitleBar(R.id.preferredWayOfCooking_backButton,R.id.preferredWayOfCooking_titleBar,R.id.preferredWayOfCooking_homeButton,Constants.TITLE_PREFERRED_WAY_OF_COOKING);


            String services[] = {"0", "1", "2"};
            ListAdapter listAdapter = new PreferredWayOfCookingAdaptor(this, services);
            ListView listView = (ListView) findViewById(R.id.preferredWayOfCooking_listView);
            if (listView != null) {
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener(

                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                SharedPreference preference=new SharedPreference(getApplicationContext());
                                preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREFERRED_WAY_OF_COOKING,String.valueOf(position));

                                if (position == 0) {

                                    Intent intent = new Intent(getApplicationContext(), CookForVegOrNonVeg.class);
                                    intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
                                    startActivity(intent);
                                    finish();
                                }else if (position == 1) {

                                    Intent intent = new Intent(getApplicationContext(), CookForVegOrNonVeg.class);
                                    intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent = new Intent(getApplicationContext(), SelectGrocery.class);
                                    intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                );
            }
        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    public void onClickHomeButton(View view){

        Intent intent=new Intent(getApplicationContext(),WelcomeScreen.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(getApplicationContext(),OfferedServices.class);
        startActivity(intent);
        finish();
    }

    public void onClickBackButton(View view){

        Intent intent=new Intent(getApplicationContext(),OfferedServices.class);
        startActivity(intent);
        finish();
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
