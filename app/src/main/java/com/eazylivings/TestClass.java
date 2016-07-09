package com.eazylivings;

import com.eazylivings.constant.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public class TestClass {

    public static ArrayList<HashMap<String, String>> inputsForGroceryItems(){


        ArrayList<HashMap<String, String>> listOfMap=new ArrayList<>();
        HashMap<String, String> map=new HashMap<>();

        map.put(Constants.GROCERY_LIST_ITEM_NAME,"Potato");
        map.put(Constants.GROCERY_LIST_ITEM_WEIGHT,"500 gm");
        map.put(Constants.GROCERY_LIST_ITEM_PRICE,"Rs. 50");
        map.put(Constants.GROCERY_LIST_ITEM_IMAGE,String.valueOf(R.drawable.fruits));
        listOfMap.add(map);


        map=new HashMap<>();
        map.put(Constants.GROCERY_LIST_ITEM_NAME,"Tomato");
        map.put(Constants.GROCERY_LIST_ITEM_WEIGHT,"500 gm");
        map.put(Constants.GROCERY_LIST_ITEM_PRICE,"Rs. 40");
        map.put(Constants.GROCERY_LIST_ITEM_IMAGE,String.valueOf(R.drawable.vegetables));
        listOfMap.add(map);

        map=new HashMap<>();
        map.put(Constants.GROCERY_LIST_ITEM_NAME,"Onion");
        map.put(Constants.GROCERY_LIST_ITEM_WEIGHT,"500 gm");
        map.put(Constants.GROCERY_LIST_ITEM_PRICE,"Rs. 30");
        map.put(Constants.GROCERY_LIST_ITEM_IMAGE,String.valueOf(R.drawable.masale));
        listOfMap.add(map);

        map=new HashMap<>();
        map.put(Constants.GROCERY_LIST_ITEM_NAME,"Tomato");
        map.put(Constants.GROCERY_LIST_ITEM_WEIGHT,"500 gm");
        map.put(Constants.GROCERY_LIST_ITEM_PRICE,"Rs. 40");
        map.put(Constants.GROCERY_LIST_ITEM_IMAGE,String.valueOf(R.drawable.wheat_flour));
        listOfMap.add(map);

        map=new HashMap<>();
        map.put(Constants.GROCERY_LIST_ITEM_NAME,"Potato");
        map.put(Constants.GROCERY_LIST_ITEM_WEIGHT,"500 gm");
        map.put(Constants.GROCERY_LIST_ITEM_PRICE,"Rs. 50");
        map.put(Constants.GROCERY_LIST_ITEM_IMAGE,String.valueOf(R.drawable.fruits));
        listOfMap.add(map);

        map=new HashMap<>();
        map.put(Constants.GROCERY_LIST_ITEM_NAME,"Tomato");
        map.put(Constants.GROCERY_LIST_ITEM_WEIGHT,"500 gm");
        map.put(Constants.GROCERY_LIST_ITEM_PRICE,"Rs. 40");
        map.put(Constants.GROCERY_LIST_ITEM_IMAGE,String.valueOf(R.drawable.vegetables));
        listOfMap.add(map);

        return listOfMap;
    }
}
