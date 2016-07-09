package com.eazylivings.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.eazylivings.R;
import com.eazylivings.commonfuntionality.CommonFunctionality;

public class CustomSwipeAdapter extends PagerAdapter {
    Context context;
    Activity activity;
    int[] imageId = { R.drawable.background, R.drawable.background,
            R.drawable.background, R.drawable.background, R.drawable.background };

    public CustomSwipeAdapter(Context context,Activity activity){
        this.context = context;
        this.activity=activity;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        CommonFunctionality commonFunctionality=new CommonFunctionality(context,activity);

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        View viewItem = inflater.inflate(R.layout.swipe_layout, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.welcomeScreen_imageView_background);
        imageView.setImageResource(imageId[position]);

        imageView.getLayoutParams().height = commonFunctionality.getDeviceHeight() / 4;
        (container).addView(viewItem);

        return viewItem;
    }

    @Override
    public int getCount() {

        return imageId.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        (container).removeView((View) object);
    }
}
