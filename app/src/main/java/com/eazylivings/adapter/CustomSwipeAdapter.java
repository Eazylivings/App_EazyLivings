package com.eazylivings.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.eazylivings.R;

public class CustomSwipeAdapter extends PagerAdapter {
    Context context;
    int[] imageId = { R.drawable.background, R.drawable.background,
            R.drawable.background, R.drawable.background, R.drawable.background };

    public CustomSwipeAdapter(Context context){
        this.context = context;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        View viewItem = inflater.inflate(R.layout.swipe_layout, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.welcomeScreen_textView_background);
        imageView.setImageResource(imageId[position]);
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
