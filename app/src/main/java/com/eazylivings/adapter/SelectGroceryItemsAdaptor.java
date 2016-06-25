package com.eazylivings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.constant.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectGroceryItemsAdaptor extends BaseAdapter {

    HashMap<String, Object> map=new HashMap<>();
    String[] services;
    Context context;


    public SelectGroceryItemsAdaptor(Context context, String[] services, HashMap<String,Object> map) {

        this.context=context;
        this.services=services;
        this.map=map;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.list_layout_select_grocery_items,parent,false);

        TextView itemName=(TextView)view.findViewById(R.id.selectGroceryItems_textView_itemName);
        TextView itemWeight=(TextView)view.findViewById(R.id.selectGroceryItems_textView_itemWeight);
        final TextView itemQuantity=(TextView)view.findViewById(R.id.selectGroceryItems_textView_itemQuantity);
        TextView itemPrice=(TextView)view.findViewById(R.id.selectGroceryItems_textView_itemPrice);

        ImageView itemImage=(ImageView)view.findViewById(R.id.selectGroceryItems_imageView_itemImage);
        ImageView itemQuantityIncrease=(ImageView)view.findViewById(R.id.selectGroceryItems_imageView_increase);
        ImageView itemQuantityDecrease=(ImageView)view.findViewById(R.id.selectGroceryItems_imageView_decrease);

        if(itemName!=null && itemWeight!=null && itemQuantity!=null && itemPrice!=null){
            itemName.setText(services[position]);
            itemWeight.setText("500 gms");
            itemQuantity.setText("1");
            itemPrice.setText("Rs. 50");

        }

        itemQuantityIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(itemQuantity!=null) {

                    String currentQuantity = itemQuantity.getText().toString();
                    int quantity = Integer.parseInt(currentQuantity);
                    quantity = quantity + 1;
                    itemQuantity.setText(String.valueOf(quantity));
                }


            }
        });

        itemQuantityDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(itemQuantity!=null) {

                    String currentQuantity = itemQuantity.getText().toString();
                    int quantity = Integer.parseInt(currentQuantity);
                    if(quantity!=1) {
                        quantity = quantity - 1;
                    }
                    itemQuantity.setText(String.valueOf(quantity));
                }
            }
        });

        return view;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return services.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

}
