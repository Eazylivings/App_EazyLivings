package com.eazylivings.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.constant.Constants;

public class PreferredWayOfCookingAdaptor extends ArrayAdapter<String>{

    public PreferredWayOfCookingAdaptor(Context context, String[] services) {
        super(context, R.layout.list_layout_preferred_way_of_cooking, services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.list_layout_preferred_way_of_cooking,parent,false);

        String clickedService=getItem(position);
        TextView cookingHeader=(TextView)view.findViewById(R.id.choiceOfCookingOptions_testView_header);
        ImageView cookingPoster=(ImageView)view.findViewById(R.id.choiceOfCookingOptions_imageButton_poster);

        switch (position){

            case 0:
                cookingHeader.setText(Constants.INTRODUCTION_PREFERRED_WAY_OF_COOKING_COOK);
                cookingPoster.setImageResource(R.drawable.cookingprocessone);
                break;

            case 1:
                cookingHeader.setText(Constants.INTRODUCTION_PREFERRED_WAY_OF_COOKING_MESS);
                cookingPoster.setImageResource(R.drawable.cookingprocesstwo);
                break;

            case 2:
                cookingHeader.setText(Constants.INTRODUCTION_PREFERRED_WAY_OF_COOKING_GROCERY);
                cookingPoster.setImageResource(R.drawable.cookingprocessthree);
                break;

            default:
                cookingHeader.setText(clickedService);
                cookingPoster.setImageResource(R.drawable.background);
                break;

        }



        return view;
    }
}
