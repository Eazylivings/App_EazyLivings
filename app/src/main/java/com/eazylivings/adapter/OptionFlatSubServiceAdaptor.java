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

public class OptionFlatSubServiceAdaptor extends ArrayAdapter<String>{

    public OptionFlatSubServiceAdaptor(Context context, String[] services) {
        super(context, R.layout.list_layout_flat_sub_services, services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.list_layout_flat_sub_services,parent,false);

        String clickedService=getItem(position);
        TextView subServiceName=(TextView)view.findViewById(R.id.optionsFlatSubService_mediumText);
        ImageView subServiceImage=(ImageView)view.findViewById(R.id.optionsFlatSubService_imageView);

        switch (position){

            case 0:
                subServiceName.setText(Constants.INTRODUCTION_FLAT_SUB_SERVICE_ENTRANCE);
                subServiceImage.setImageResource(R.drawable.flat_entrance);
                break;

            case 1:
                subServiceName.setText(Constants.INTRODUCTION_FLAT_SUB_SERVICE_COMMON_AREA);
                subServiceImage.setImageResource(R.drawable.flat_common_area);
                break;

            case 2:
                subServiceName.setText(Constants.INTRODUCTION_FLAT_SUB_SERVICE_KITCHEN);
                subServiceImage.setImageResource(R.drawable.flat_kitchen);
                break;

            case 3:
                subServiceName.setText(Constants.INTRODUCTION_FLAT_SUB_SERVICE_BEDROOM);
                subServiceImage.setImageResource(R.drawable.flat_bedroom);
                break;

            case 4:
                subServiceName.setText(Constants.INTRODUCTION_FLAT_SUB_SERVICE_WASHROOM);
                subServiceImage.setImageResource(R.drawable.flat_washroom);
                break;

            default:
                subServiceName.setText(clickedService);
                subServiceImage.setImageResource(R.drawable.background);
                break;

        }



        return view;
    }
}
