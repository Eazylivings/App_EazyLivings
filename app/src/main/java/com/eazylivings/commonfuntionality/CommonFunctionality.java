package com.eazylivings.commonfuntionality;


import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
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

    public void onClickListenerForImage(int imageid){

        ImageView imageView = (ImageView) callingActivity.findViewById(imageid);

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


}
