package com.eazylivings.databasehandler;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.eazylivings.VO.UserDetails;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

import java.util.ArrayList;

public class DeviceSetup extends SQLiteOpenHelper {


    private String SERVICE_NAME="service_name";
    private String IS_SUBSCRIBED="is_subscribed";
    ContentValues values;
    SQLiteDatabase db;
    Context applicationContext;




    public DeviceSetup(Context context, String dataBaseName, SQLiteDatabase.CursorFactory factory, int dataBaseVersion) {
        super(context,dataBaseName, factory,dataBaseVersion);
        this.applicationContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //db.execSQL(Constants.DROP_TABLE_QUERY+ Constants.SIGNUP_DETAILS_TABLE);
        onCreate(db);
    }


    // Add services into the created Table. This is one time process.
    public void insertServicesIntoTable(){

        db=getWritableDatabase();
        ArrayList<String> listOfServices=populateListOfServices();

        for(int i=0;i<listOfServices.size();i++){

            values=new ContentValues();
            values.put(SERVICE_NAME,listOfServices.get(i));
            values.put(IS_SUBSCRIBED,false);

            db.insert(Constants.USER_PREFERENCES_TABLE,null,values);

        }
    }


    //At time of registration, users details like username, email address and password will be stored here. Phone number can also be captured
    public void insertUserDetails(UserDetails userDetails){
        db = getWritableDatabase();
        if(userDetails!=null){
            ContentValues values=new ContentValues();
            values.put(Constants.COLUMN_USER_NAME,userDetails.getUserName());
            values.put(Constants.COLUMN_EMAIL_ADDRESS,userDetails.getEmail_address());
            values.put(Constants.COLUMN_CONTACT_NUMBER,userDetails.getContact_number());
            values.put(Constants.COLUMN_ADDRESS,"");

            if(db!=null) {

                db.insert("user_details_" + userDetails.getUserName(), null, values);
                db.insert("user_details_" + userDetails.getUserName(), null, values);
                db.close();

            }
        }
    }

    public void saveUserDetailsUsingSharedPreference(UserDetails userDetails,Context context){

        SharedPreference preference=new SharedPreference();
        preference.setStringValueInSharedPreference(context,"user_name",userDetails.getUserName());
        preference.setStringValueInSharedPreference(context,"email_address",userDetails.getEmail_address());
        preference.setStringValueInSharedPreference(context,"contact_number",userDetails.getContact_number());
        preference.setStringValueInSharedPreference(context,"address",userDetails.getResidential_address());
        preference.setBooleanValueInSharedPreference(context,"isProfileAlreadyLoaded",true);
    }


    public UserDetails getUserDetailsUsingSharedPreferences(Context context){

        SharedPreference sharedPreference=new SharedPreference();
        UserDetails userDetails=new UserDetails();

        userDetails.setUserName(sharedPreference.getStringValueFromSharedPreference(context,"user_name"));
        userDetails.setEmail_address(sharedPreference.getStringValueFromSharedPreference(context,"email_address"));
        userDetails.setContact_number(sharedPreference.getStringValueFromSharedPreference(context,"contact_number"));
        userDetails.setResidential_address(sharedPreference.getStringValueFromSharedPreference(context,"address"));

        return userDetails;


    }

    private ArrayList<String> populateListOfServices(){

        ArrayList<String> listOfServices=new ArrayList<String>();
        listOfServices.add("Cleaning");
        listOfServices.add("Flat Setup");
        listOfServices.add("Cooking");
        listOfServices.add("Washing");
        listOfServices.add("Ironing");

        return listOfServices;
    }
}
