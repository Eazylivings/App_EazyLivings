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

public class ServiceImageDescriptionAdaptor extends ArrayAdapter<String>{

    public ServiceImageDescriptionAdaptor(Context context, String[] services) {
        super(context, R.layout.service_image_short_description, services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.service_image_short_description,parent,false);


        String clickedService=getItem(position);
        TextView serviceName=(TextView)view.findViewById(R.id.serviceImageShortDescription_textView_serviceName);
        ImageView serviceImage=(ImageView)view.findViewById(R.id.serviceImageShortDescription_imageView_serviceImage);

        switch (position){

            case 0:
                if(serviceName!=null && serviceImage!=null) {
                    serviceName.setText(Constants.FLAT_NAME);
                    serviceImage.setImageResource(R.drawable.flatsetup);
                }
                break;

            case 1:
                if(serviceName!=null && serviceImage!=null) {
                    serviceName.setText(Constants.COOKING_NAME);
                    serviceImage.setImageResource(R.drawable.cooking_offeredservices);
                }
                break;

            case 2:
                if(serviceName!=null && serviceImage!=null) {
                    serviceName.setText(Constants.CLEANING_NAME);
                    serviceImage.setImageResource(R.drawable.cleaning);
                }
                break;

            case 3:
                if(serviceName!=null && serviceImage!=null) {
                    serviceName.setText(Constants.WASHING_NAME);
                    serviceImage.setImageResource(R.drawable.washing);
                }
                break;

            default:
                if(serviceName!=null && serviceImage!=null) {
                    serviceName.setText(clickedService);
                    serviceImage.setImageResource(R.drawable.background);
                }
                break;

        }



        return view;
    }
}
