package com.eazylivings.adapter;


import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.eazylivings.R;

public class CooksInformationAdaptor extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<HashMap<String,String>> categoryList;

    public CooksInformationAdaptor(Context context, List<HashMap<String,String>> categoryList) {

        this.context=context;
        this.categoryList = categoryList;
        this.inflater = LayoutInflater.from(this.context);
    }


    @Override
    public int getGroupCount() {
        return categoryList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoryList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return "size";
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {


        convertView = inflater.inflate(R.layout.list_layout_cook_description_expandable_view_parent, null);

        TextView cookName=(TextView)convertView.findViewById(R.id.cooksDescription_textView_cookName);
        TextView cookGender=(TextView)convertView.findViewById(R.id.cooksDescription_textView_cookGender);
        TextView cookAge=(TextView)convertView.findViewById(R.id.cooksDescription_textView_cookAge);
        TextView cookHomeTown=(TextView)convertView.findViewById(R.id.cooksDescription_textView_cookHomeTown);

        if(cookName!=null){
            cookName.setText(categoryList.get(groupPosition).get("name"));
        }
        if(cookGender!=null){
            cookGender.setText(categoryList.get(groupPosition).get("gender"));
        }
        if(cookAge!=null){
            cookAge.setText(categoryList.get(groupPosition).get("age"));
        }
        if(cookHomeTown!=null){
            cookHomeTown.setText(categoryList.get(groupPosition).get("homeTown"));
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_layout_cook_description_expandable_view_child, null);
        }

        TextView description=(TextView)convertView.findViewById(R.id.cookDescription_textView_cookDescription);
        TextView specialization=(TextView)convertView.findViewById(R.id.cookDescription_textView_cookSpecialization);

        if(description!=null){
            description.setText(categoryList.get(groupPosition).get("description"));
        }
        if(specialization!=null){
            specialization.setText(categoryList.get(groupPosition).get("specialization"));
        }

        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
