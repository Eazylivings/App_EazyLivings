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

public class SelectGroceryItemsAdaptor extends ArrayAdapter<String> {

    public SelectGroceryItemsAdaptor(Context context, String[] services) {
        super(context, R.layout.list_layout_preferred_way_of_cooking, services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.list_layout_preferred_way_of_cooking,parent,false);

        return view;
    }
}
