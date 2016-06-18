package com.eazylivings.commonfuntionality;


import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

public class CommonFunctionality {

    Context applicationContext;
    Activity callingActivity;

    public CommonFunctionality(Activity activity){

        this.callingActivity=activity;

    }

    public void setTitleBar(int backButtonId,int titleBarId, int homeButtonId, String titleBarText){

        try{

            Display display = callingActivity.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

            //Title Bar
            TextView titleBar = (TextView) callingActivity.findViewById(titleBarId);
            if(titleBar!=null) {
                int textSize=(width*6)/100;
                titleBar.getLayoutParams().width = (width * 7) / 10;
                titleBar.setText(titleBarText);
                titleBar.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
            }
            ImageView backButton = (ImageView) callingActivity.findViewById(backButtonId);
            backButton.getLayoutParams().width = (width) / 10;
            ImageView homeButton = (ImageView) callingActivity.findViewById(homeButtonId);
            homeButton.getLayoutParams().width = (width) / 10;

        }catch(Exception e){

        }

    }


}
