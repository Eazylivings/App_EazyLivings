package com.eazylivings;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ServiceImageDescriptionAdaptor extends ArrayAdapter<String>{

    public ServiceImageDescriptionAdaptor(Context context, String[] services) {
        super(context,R.layout.service_image_short_description, services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.service_image_short_description,parent,false);


        String clickedService=getItem(position);
        TextView serviceName=(TextView)view.findViewById(R.id.serviceImageShortDescription_imageView_sericeName);
        ImageView serviceImage=(ImageView)view.findViewById(R.id.serviceImageShortDescription_imageView_sericeImage);
        TextView serviceDescription=(TextView)view.findViewById(R.id.serviceImageShortDescription_imageView_sericeDescription);

        serviceName.setText(clickedService);
        serviceImage.setImageResource(R.drawable.background);
        serviceDescription.setText("Hello..........................");

        return view;
    }
}
