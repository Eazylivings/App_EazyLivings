package com.eazylivings.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.eazylivings.R;

/**
 * Created by Vibek on 6/2/2016.
 */
public class CustomSwipeAdapter extends PagerAdapter {
    private int[] imageResources={R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public CustomSwipeAdapter(Context ctx){
        this.ctx=ctx;
    }

    @Override
    public int getCount() {
        return imageResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object ob) {
        return(view==(LinearLayout)ob);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView=(ImageView)itemView.findViewById(R.id.welcomeScreen_textView_background);
        imageView.setImageResource(imageResources[position]);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}