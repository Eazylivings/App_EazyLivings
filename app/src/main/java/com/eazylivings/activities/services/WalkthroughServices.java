package com.eazylivings.activities.services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.constant.Constants;

public class WalkthroughServices extends AppCompatActivity {

    int clickedService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough_services);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            clickedService=bundle.getInt(Constants.SHARED_PREFERENCE_CLICKED_SERVICE);
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

        RelativeLayout layout=(RelativeLayout)findViewById(R.id.walkthroughServices_relativelayout);
        TextView textView=(TextView)findViewById(R.id.walkthroughService_textView_steps);
        String textViewText;

        if(layout!=null && textView!=null) {


            switch (serviceSelected) {

                case 0:
                    setTitle(Constants.WALK_THROUGH_SERVICES_FLAT_TITLE);
                    layout.setBackgroundResource(R.drawable.imageone);
                    textViewText="1. Provide us the location of the flat.\n\n"+
                            "2. Select the items which you want us to deliver at your place.\n\n"+
                            "3. Book an time slot. You will have to pay 20% of the product value after placing the order and outstanding amount after receipt.";
                    textView.setText(textViewText);
                    break;

                case 1:
                    setTitle(Constants.WALK_THROUGH_SERVICES_COOKING_TITLE);
                    layout.setBackgroundResource(R.drawable.imagetwo);
                    textViewText="1. Provide us the location of the flat.\n\n"+
                            "2. Select the items which you e.\n\n"+
                            "3. Book an time slot. You will hahve to pay 20% of the produtc value after placing the order and outstanding amount after receipt.";
                    textView.setText(textViewText);
                    break;

                case 2:
                    setTitle(Constants.WALK_THROUGH_SERVICES_CLEANING_TITLE);
                    layout.setBackgroundResource(R.drawable.imagethree);
                    textViewText="1. Provide us the location of the flat.\n\n"+
                            "2.which you want us to deliver at your place.\n\n"+
                            "3. Book an time slot. You will hahve to pay 20% of the produtc value after placing the order and outstanding amount after receipt.";
                    textView.setText(textViewText);
                    break;

                case 3:
                    setTitle(Constants.WALK_THROUGH_SERVICES_WASHING_TITLE);
                    layout.setBackgroundResource(R.drawable.imagefour);
                    textViewText="1. Provide us the location of the flat.\n\n"+
                            "2. Select the items which you want us to deliver at your place.";
                    textView.setText(textViewText);
                    break;

                default:
                    break;
            }
        }
    }
}
