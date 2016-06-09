package com.eazylivings.activities.services.cooking;

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

public class ChoiceOfCooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_of_cooking);

        String services[] = {"0","1","2"};
        ListAdapter listAdapter = new CookingChoiceAdaptor(this, services);
        ListView listView = (ListView) findViewById(R.id.choiceOfCooking_listView);
        if (listView != null) {
            listView.setAdapter(listAdapter);

            listView.setOnItemClickListener(

                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            if(position==0){

                                Intent intent = new Intent(getApplicationContext(), CookSelection.class);
                                intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
                                intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE, position);
                                startActivity(intent);
                            }
                        }
                    }
            );
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), WalkthroughServices.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }

}
