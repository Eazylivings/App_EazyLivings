package com.eazylivings.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.eazylivings.R;

public class CookingChoiceAdaptor extends ArrayAdapter<String>{

    public CookingChoiceAdaptor(Context context, String[] services) {
        super(context, R.layout.service_image_short_description, services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.choice_of_cooking_options,parent,false);

        String clickedService=getItem(position);
        TextView cookingHeader=(TextView)view.findViewById(R.id.choiceOfCookingOptions_testView_header);
        ImageView cookingPoster=(ImageView)view.findViewById(R.id.choiceOfCookingOptions_imageButton_poster);

        switch (position){

            case 0:
                cookingHeader.setText("Automate grocery list. Cook will come and cook for you");
                cookingPoster.setImageResource(R.drawable.cookingprocessone);
                break;

            case 1:
                cookingHeader.setText("Dont want cook or cook yourself No worry. Find a mess nearby your place");
                cookingPoster.setImageResource(R.drawable.cookingprocesstwo);
                break;

            case 2:
                cookingHeader.setText("Automate grocery list and cook yourself");
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
