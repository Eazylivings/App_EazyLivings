package com.eazylivings.activities.services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eazylivings.R;

public class WalkthroughServices extends AppCompatActivity {

    int clickedService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough_services);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            clickedService=bundle.getInt("clickedService");
        }

        setServiceBasedContent(clickedService);
    }

    public void onClickLetMeChoose(View view){

        switch (clickedService){

            case 0:
                break;

            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            default:
                break;
        }

    }

    private void setServiceBasedContent(int serviceSelected){

        TextView textView=(TextView)findViewById(R.id.walkthroughServices_testView_test);

        switch (serviceSelected){

            case 0:
                textView.setText("Flat Setup");
                break;

            case 1:
                textView.setText("Cleaning");
                break;

            case 2:
                textView.setText("Washing");
                break;

            case 3:
                textView.setText("Cooking");
                break;

            default:
                textView.setText("Default");
                break;
        }



    }
}
