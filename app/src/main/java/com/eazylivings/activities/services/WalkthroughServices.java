package com.eazylivings.activities.services;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.activities.services.cooking.ChoiceOfCooking;
import com.eazylivings.constant.Constants;

public class WalkthroughServices extends Activity {

    int clickedService;
    int displayScreenHeight;
    int displayScreenWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough_services);

        try {

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            displayScreenHeight = size.x;
            displayScreenWidth = size.y;

            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                clickedService = bundle.getInt(Constants.SHARED_PREFERENCE_CLICKED_SERVICE);
            }
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setIcon(android.R.color.transparent);
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(Constants.BLUE_COLOR)));
            }

            setServiceBasedContent(clickedService);
        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), OfferedServices.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }

    public void onClickLetMeChoose(View view){
        Intent intent;

        switch (clickedService){

            case 0:
                break;

            case 1:
                intent=new Intent(getApplicationContext(), ChoiceOfCooking.class);
                startActivity(intent);
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

        try {


            RelativeLayout layout = (RelativeLayout) findViewById(R.id.walkthroughServices_relativelayout);
            TextView textView = (TextView) findViewById(R.id.walkthroughService_textView_steps);
            int numberOfLines=(displayScreenHeight*5)/150;
            textView.setMaxLines(numberOfLines);
            textView.setMovementMethod(new ScrollingMovementMethod());
            String textViewText;

            if (layout != null && textView != null) {


                switch (serviceSelected) {

                    case 0:
                        setTitle(Constants.WALK_THROUGH_SERVICES_FLAT_TITLE);
                        layout.setBackgroundResource(R.drawable.blur_background_services_description);
                        textViewText = "1. Choose all the amenities you want in your flat.\n\n" +
                                "2. Select other services if required and configure them.\n\n" +
                                "3. Review all the selected and subscribed items in final screen.\n\n" +
                                "4. Once reviewed submit the page. You will have ot pay 20% of the amount along with the order. Remaining amount once goods are delivered.\n\n" +
                                "5.  We will contact you once we receive the request and confirm your selections. After this we will deliver the product and receive rest of the payment";
                        textView.setText(textViewText);
                        setTitle("Flat Setup");
                        break;

                    case 1:
                        setTitle(Constants.WALK_THROUGH_SERVICES_COOKING_TITLE);
                        layout.setBackgroundResource(R.drawable.blur_background_services_description);
                        textViewText = "1. Select the preferred way of cooking which can be either cook or a mess.\n\n" +
                                "2. If it is cook or self cooking, configure your weekly and monthly grocery list in our planner. We will provide those items on weekly and monthly basis.\n\n" +
                                "3. Buying grocery will be prepaid service while hiring a cook will be a postpaid service.";
                        textView.setText(textViewText);
                        setTitle("Cooking");
                        break;

                    case 2:
                        setTitle(Constants.WALK_THROUGH_SERVICES_CLEANING_TITLE);
                        layout.setBackgroundResource(R.drawable.blur_background_services_description);
                        textViewText = "1. Select the preferred choice of cleaner you want.\n\n" +
                                "2.Configure the areas and type of cleaning you want on daily, weekly and monthly basis.\n\n" +
                                "3. Cleaning will be postpaid service. So after end of every month you will have to pay the amount.";
                        textView.setText(textViewText);
                        setTitle("Cleaning");
                        break;

                    case 3:
                        setTitle(Constants.WALK_THROUGH_SERVICES_WASHING_TITLE);
                        layout.setBackgroundResource(R.drawable.blur_background_services_description);
                        textViewText = "1. Select the type of washing you want.\n\n" +
                                "2. Configure the washing preferences along with numbers of clothes on average and whether it is weekly once or twice.\n\n" +
                                "3. This will be postpaid service. After end of every month you will be charged for the same.";
                        textView.setText(textViewText);
                        setTitle("Washing");
                        break;

                    default:
                        break;
                }
            }
        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
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
