package com.eazylivings.activities.services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eazylivings.R;

public class WalkthroughServices extends AppCompatActivity {

    String clickedService="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough_services);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            clickedService=bundle.getString("clickedService");
        }
        TextView textView=(TextView)findViewById(R.id.walkthroughServices_testView_test);
        textView.setText(clickedService);

    }

    public void onClickLetMeChoose(View view){

    }
}
