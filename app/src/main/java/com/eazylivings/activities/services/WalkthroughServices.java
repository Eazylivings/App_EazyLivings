package com.eazylivings.activities.services;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.activities.login.SignIn;
import com.eazylivings.activities.services.cooking.PreferredWayOfCooking;
import com.eazylivings.activities.services.flatsetup.FlatSubServices;
import com.eazylivings.activities.services.flatsetup.SelectItemsForFlat;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class WalkthroughServices extends AppCompatActivity {

    int clickedService;
    String clickedFlatSubService;
    String previousActivity="";
    SharedPreference preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough_services);

        preference=new SharedPreference(getApplicationContext());
        previousActivity=preference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY);

        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                clickedService = bundle.getInt(Constants.SHARED_PREFERENCE_CLICKED_SERVICE);
                clickedFlatSubService=bundle.getString(Constants.SHARED_PREFERENCE_CLICKED_FLAT_SUB_SERVICE);
                if(clickedFlatSubService==null){
                    clickedFlatSubService="";
                }
            }
            setServiceBasedContent(clickedService);
        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public void onBackPressed(){

        if(previousActivity.equalsIgnoreCase(Constants.ACTIVITY_WELCOME_SCREEN)){
            Intent intent = new Intent(getApplicationContext(),WelcomeScreen.class);
            startActivity(intent);
            finish();
        }else if(previousActivity.equalsIgnoreCase(Constants.ACTIVITY_OFFERED_SERVICE)){
            Intent intent = new Intent(getApplicationContext(),OfferedServices.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClickBackButton(View view){

        if(previousActivity.equalsIgnoreCase(Constants.ACTIVITY_WELCOME_SCREEN)){
            Intent intent = new Intent(getApplicationContext(),WelcomeScreen.class);
            startActivity(intent);
            finish();
        }else if(previousActivity.equalsIgnoreCase(Constants.ACTIVITY_OFFERED_SERVICE)){
            Intent intent = new Intent(getApplicationContext(),OfferedServices.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClickHomeButton(View view){

        Intent intent=new Intent(getApplicationContext(),WelcomeScreen.class);
        startActivity(intent);
        finish();
    }

    public void onClickLetMeChoose(View view){
        Intent intent;
        preference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY,Constants.ACTIVITY_OFFERED_SERVICE);
        switch (clickedService){

            case 0:
                if(clickedFlatSubService.equalsIgnoreCase(Constants.FLAT_SUB_SERVICE_ENTRANCE)){
                    intent=new Intent(getApplicationContext(), SelectItemsForFlat.class);
                    startActivity(intent);
                    finish();

                }else if(clickedFlatSubService.equalsIgnoreCase(Constants.FLAT_SUB_SERVICE_COMMON_AREA)){
                    intent=new Intent(getApplicationContext(), SelectItemsForFlat.class);
                    startActivity(intent);
                    finish();

                }else if(clickedFlatSubService.equalsIgnoreCase(Constants.FLAT_SUB_SERVICE_KITCHEN)){
                    intent=new Intent(getApplicationContext(), SelectItemsForFlat.class);
                    startActivity(intent);
                    finish();

                }else if(clickedFlatSubService.equalsIgnoreCase(Constants.FLAT_SUB_SERVICE_BEDROOM)){
                    intent=new Intent(getApplicationContext(), SelectItemsForFlat.class);
                    startActivity(intent);
                    finish();

                }else if(clickedFlatSubService.equalsIgnoreCase(Constants.FLAT_SUB_SERVICE_WASHROOM)){
                    intent=new Intent(getApplicationContext(), SelectItemsForFlat.class);
                    startActivity(intent);
                    finish();

                }else{
                    intent=new Intent(getApplicationContext(), FlatSubServices.class);
                    startActivity(intent);
                    finish();
                }

                break;

            case 1:
                intent=new Intent(getApplicationContext(), PreferredWayOfCooking.class);
                intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE,1);
                startActivity(intent);
                finish();
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
            CommonFunctionality commonFunctionality=new CommonFunctionality(this);

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int displayScreenHeight = size.x;

            RelativeLayout layout = (RelativeLayout) findViewById(R.id.walkthroughServices_relativelayout);
            TextView textView = (TextView) findViewById(R.id.walkthroughService_textView_steps);
            int numberOfLines=(displayScreenHeight*5)/150;
            textView.setMaxLines(numberOfLines);
            textView.setMovementMethod(new ScrollingMovementMethod());
            String textViewText;

            if (layout != null && textView != null) {


                switch (serviceSelected) {

                    case 0:
                        commonFunctionality.setTitleBar(R.id.walkThroughServices_backButton,R.id.walkThroughServices_titleBar,R.id.walkThroughServices_homeButton,Constants.TITLE_WALK_THROUGH_SERVICES_FLAT);
                        commonFunctionality.onClickListenerForImage(R.id.walkThroughServices_backButton);
                        commonFunctionality.onClickListenerForImage(R.id.walkThroughServices_homeButton);
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
                        commonFunctionality.setTitleBar(R.id.walkThroughServices_backButton,R.id.walkThroughServices_titleBar,R.id.walkThroughServices_homeButton,Constants.TITLE_WALK_THROUGH_SERVICES_COOKING);
                        commonFunctionality.onClickListenerForImage(R.id.walkThroughServices_backButton);
                        commonFunctionality.onClickListenerForImage(R.id.walkThroughServices_homeButton);
                        layout.setBackgroundResource(R.drawable.blur_background_services_description);
                        textViewText = "1. Select the preferred way of cooking which can be either cook or a mess.\n\n" +
                                "2. If it is cook or self cooking, configure your weekly and monthly grocery list in our planner. We will provide those items on weekly and monthly basis.\n\n" +
                                "3. Buying grocery will be prepaid service while hiring a cook will be a postpaid service.";
                        textView.setText(textViewText);
                        setTitle("Cooking");
                        break;

                    case 2:

                        commonFunctionality.setTitleBar(R.id.walkThroughServices_backButton,R.id.walkThroughServices_titleBar,R.id.walkThroughServices_homeButton,Constants.TITLE_WALK_THROUGH_SERVICES_CLEANING);
                        commonFunctionality.onClickListenerForImage(R.id.walkThroughServices_backButton);
                        commonFunctionality.onClickListenerForImage(R.id.walkThroughServices_homeButton);
                        layout.setBackgroundResource(R.drawable.blur_background_services_description);
                        textViewText = "1. Select the preferred choice of cleaner you want.\n\n" +
                                "2.Configure the areas and type of cleaning you want on daily, weekly and monthly basis.\n\n" +
                                "3. Cleaning will be postpaid service. So after end of every month you will have to pay the amount.";
                        textView.setText(textViewText);
                        setTitle("Cleaning");
                        break;

                    case 3:
                        commonFunctionality.setTitleBar(R.id.walkThroughServices_backButton,R.id.walkThroughServices_titleBar,R.id.walkThroughServices_homeButton,Constants.TITLE_WALK_THROUGH_SERVICES_WASHING);
                        commonFunctionality.onClickListenerForImage(R.id.walkThroughServices_backButton);
                        commonFunctionality.onClickListenerForImage(R.id.walkThroughServices_homeButton);
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

    private void generateLoginPopupMessage(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(Constants.ALERT_CONFIRM);
        builder.setMessage(message);

        builder.setPositiveButton("Login Now", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                SharedPreference sharedPreference=new SharedPreference(getApplicationContext());
                if(clickedService==0) {
                    sharedPreference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY, Constants.ACTIVITY_FLAT_SETUP);
                }else if(clickedService==1){
                    sharedPreference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY, Constants.ACTIVITY_PREFERRED_WAY_OF_COOKING);
                }else if(clickedService==2){

                }else if(clickedService==3){

                }
                Intent intent=new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
