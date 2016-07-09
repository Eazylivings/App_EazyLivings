package com.eazylivings.commonfuntionality;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.activities.login.SignIn;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

public class CommonFunctionality {

    Context context;
    Activity activity;

    public CommonFunctionality(Context context,Activity activity){

        this.activity=activity;
        this.context=context;
    }

    public void setTitleBar(int backButtonId,int titleBarId, int homeButtonId, String titleBarText){

        try{

            Display display = activity.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;

            //Title Bar
            TextView titleBar = (TextView) activity.findViewById(titleBarId);
            if(titleBar!=null) {
                int textSize=(width*6)/100;
                titleBar.getLayoutParams().width = (width * 7) / 10;
                titleBar.setText(titleBarText);
                titleBar.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
            }
            ImageView backButton = (ImageView) activity.findViewById(backButtonId);
            if(backButton!=null) {
                backButton.getLayoutParams().width = (width) / 10;
            }
            ImageView homeButton = (ImageView) activity.findViewById(homeButtonId);
            if(homeButton!=null) {
                homeButton.getLayoutParams().width = (width) / 10;
            }

        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }

    }

    public void onClickListenerForImage(int imageid){

        ImageView imageView = (ImageView) activity.findViewById(imageid);

        if (imageView != null) {

            imageView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            ImageView view = (ImageView) v;
                            //overlay is black with transparency of 0x77 (119)
                            view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                            view.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            ImageView view = (ImageView) v;
                            //clear the overlay
                            view.getDrawable().clearColorFilter();
                            view.invalidate();
                            break;
                        }
                    }

                    return false;
                }
            });
        }

    }

    public void onBackPressed(String calledActivity){

        SharedPreference preference=new SharedPreference(context);

        Intent intent = new Intent(context,preference.getPreviousActivity(calledActivity));
        activity.startActivity(intent);
        activity.finish();
    }

    public void onClickHomeButton(){

        Intent intent = new Intent(context, WelcomeScreen.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public void generateLoginPopupMessage(String message){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);

        builder.setTitle(Constants.ALERT_CONFIRM);
        builder.setMessage(message);

        builder.setPositiveButton("Login Now", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                SharedPreference sharedPreference=new SharedPreference(context);
                sharedPreference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY, Constants.ACTIVITY_COOK_SELECTION);

                Intent intent=new Intent(context, SignIn.class);
                activity.startActivity(intent);
                activity.finish();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

    public void generatePopupMessage(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
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

    public int getDeviceWidth(){

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.x;

    }

    public int getDeviceHeight(){

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.y;
    }

    public void setTextSize(int textViewId){

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        TextView textView = (TextView) activity.findViewById(textViewId);
        if(textView!=null) {
            int textSize=(width*4)/100;
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        }

    }

}
