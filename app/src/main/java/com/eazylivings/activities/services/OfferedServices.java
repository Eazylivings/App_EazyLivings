package com.eazylivings.activities.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eazylivings.R;

public class OfferedServices extends AppCompatActivity {


    String clickedService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_services);

        setTitle("EazyLivings's Offerings");
    }

    public void onClickService(View view){
        Intent intent=new Intent(getApplicationContext(),WalkthroughServices.class);
        intent.putExtra("clickedService",clickedService);
        startActivity(intent);
    }
}
