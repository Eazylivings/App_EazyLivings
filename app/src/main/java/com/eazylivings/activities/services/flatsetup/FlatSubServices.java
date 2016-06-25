package com.eazylivings.activities.services.flatsetup;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.activities.services.OfferedServices;
import com.eazylivings.adapter.OptionFlatSubServiceAdaptor;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;

public class FlatSubServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_sub_services);

        try {
            CommonFunctionality commonFunctionality=new CommonFunctionality(this);
            commonFunctionality.setTitleBar(R.id.flatSubServices_backButton,R.id.flatSubServices_titleBar,R.id.flatSubServices_homeButton,Constants.TITLE_FLAT_SUB_SERVICE);
            commonFunctionality.onClickListenerForImage(R.id.flatSubServices_backButton);
            commonFunctionality.onClickListenerForImage(R.id.flatSubServices_homeButton);


            String services[] = {Constants.FLAT_SUB_SERVICE_ENTRANCE, Constants.FLAT_SUB_SERVICE_COMMON_AREA, Constants.FLAT_SUB_SERVICE_KITCHEN, Constants.FLAT_SUB_SERVICE_BEDROOM,Constants.FLAT_SUB_SERVICE_WASHROOM};
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

        Intent intent = new Intent(getApplicationContext(),OfferedServices.class);
        startActivity(intent);
        finish();
    }

    public void onClickBackButton(View view){

        Intent intent=new Intent(getApplicationContext(),OfferedServices.class);
        startActivity(intent);
        finish();
    }

    public void onClickHomeButton(View view){

        Intent intent=new Intent(getApplicationContext(),WelcomeScreen.class);
        startActivity(intent);
        finish();
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
