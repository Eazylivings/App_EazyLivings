package com.eazylivings.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.eazylivings.R;
import com.eazylivings.activities.login.SignIn;
import com.eazylivings.activities.services.OfferedServices;
import com.eazylivings.activities.services.WalkthroughServices;

import com.eazylivings.adapter.CustomSwipeAdapter;
import com.eazylivings.commonfuntionality.CommonFunctionality;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

import android.support.v4.app.FragmentActivity;

public class WelcomeScreen extends FragmentActivity
implements NavigationView.OnNavigationItemSelectedListener {
    String userName;
    SharedPreference sharedPreference;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        PagerAdapter adapter = new CustomSwipeAdapter(this,this);
        viewPager.setAdapter(adapter);

        sharedPreference=new SharedPreference(getApplicationContext());

        try {

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null){
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
    }*/


            HorizontalScrollView horizontalScrollView=(HorizontalScrollView)findViewById(R.id.welcomeScreen_horizontalScrollView);
            horizontalScrollView.setHorizontalScrollBarEnabled(false);

            toolbar = (Toolbar) findViewById(R.id.appBarWelcomeScreen_toolBar);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            if(drawer!=null) {
                drawer.setDrawerListener(toggle);
            }
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            Menu menu = navigationView.getMenu();

            boolean isUserLoggedIn = sharedPreference.getBooleanValueFromSharedPreference(Constants.SHARED_PREFERENCE_LOGIN_STATUS);
            if(isUserLoggedIn){
                menu.removeItem(R.id.drawer_logIn);
            }else{
                menu.removeItem(R.id.drawer_logOut);
                menu.removeItem(R.id.drawer_userPreference);
            }
            View header=navigationView.getHeaderView(0);
            navigationView.setNavigationItemSelectedListener(this);

            TextView drawerUserName = (TextView)header.findViewById(R.id.navHeaderWelcomeScreen_largeText_userName);
            TextView drawerEmailAddress = (TextView)header.findViewById(R.id.navHeaderWelcomeScreen_mediumText_emailAddress);
            drawerUserName.setText(sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_USERNAME));
            drawerEmailAddress.setText(sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_EMAIL_ADDRESS));

            setWelcomeScreen();
        } catch (Exception e) {
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_welcome_screen_drawer, menu);
        return true;
    }

    public void setWelcomeScreen(){

        CommonFunctionality commonFunctionality=new CommonFunctionality(this,this);
        commonFunctionality.setTextSize(R.id.welcomeScreen_textView_explore);
        commonFunctionality.setTextSize(R.id.welcomeScreen_textView_movingToFlat);
        commonFunctionality.setTextSize(R.id.contentWelcomeScreen_mediumText_messageTwo);
        commonFunctionality.setTextSize(R.id.contentWelcomeScreen_mediumText_messageThree);

        boolean isUserLoggedIn=sharedPreference.getBooleanValueFromSharedPreference(Constants.SHARED_PREFERENCE_LOGIN_STATUS);


        if(isUserLoggedIn){

            userName = sharedPreference.getStringValueFromSharedPreference(Constants.SHARED_PREFERENCE_USERNAME);
            if(toolbar!=null) {
                toolbar.setTitle(Constants.WELCOME + userName + "!!");
            }
        }
        else{
            if(toolbar!=null) {
                toolbar.setTitle(Constants.WELCOME+ Constants.NEWBIE);
            }
        }
    }

    public void onClickStart(View view){

        sharedPreference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY,Constants.ACTIVITY_WELCOME_SCREEN);
        Intent intent=new Intent(getApplicationContext(), OfferedServices.class);
        startActivity(intent);
        finish();
    }

    public void onClickExplore(View view){

        int clickedId=view.getId();
        Intent intent;


        if(clickedId==R.id.welcomeScreen_imageButton_bathroom){
            intent=new Intent(getApplicationContext(), WalkthroughServices.class);
            intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE,0);
            intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_FLAT_SUB_SERVICE,Constants.FLAT_SUB_SERVICE_WASHROOM);
            startActivity(intent);

        }else if(clickedId==R.id.welcomeScreen_imageButton_kitchen){
            intent=new Intent(getApplicationContext(), WalkthroughServices.class);
            intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE,0);
            intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_FLAT_SUB_SERVICE,Constants.FLAT_SUB_SERVICE_KITCHEN);
            startActivity(intent);

        }else if(clickedId==R.id.welcomeScreen_imageButton_bedroom){
            intent=new Intent(getApplicationContext(), WalkthroughServices.class);
            intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE,0);
            intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_FLAT_SUB_SERVICE,Constants.FLAT_SUB_SERVICE_BEDROOM);
            startActivity(intent);

        }else if(clickedId==R.id.welcomeScreen_textView_movingToFlat || clickedId==R.id.welcomeScreen_textView_moveForward || clickedId==R.id.welcomeScreen_textView_explore   ){
            intent=new Intent(getApplicationContext(), WalkthroughServices.class);
            intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE,0);
            intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_FLAT_SUB_SERVICE,"");
            startActivity(intent);
        }
        sharedPreference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY,Constants.ACTIVITY_WELCOME_SCREEN);



    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer!=null &&drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

      if (exit) {
          finish();
      } else {
          Toast.makeText(this, "Press Back again to Exit.",
                  Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 3 * 1000);

            }

        }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        try {

            Intent intent;
            super.onOptionsItemSelected(item);

            switch (item.getItemId()) {
                case R.id.drawer_userProfile:

                    if (sharedPreference.getBooleanValueFromSharedPreference(Constants.SHARED_PREFERENCE_LOGIN_STATUS)) {
                        intent = new Intent(this, MyAccount.class);
                        startActivity(intent);
                        finish();
                    } else {
                        generatePopupMessage(Constants.LOGIN_FOR_PROFILE);
                    }

                    break;

                case R.id.drawer_logOut:

                    intent = new Intent(this, WelcomeScreen.class);
                    startActivity(intent);
                    sharedPreference.removeValueFromSharedPreference(Constants.SHARED_PREFERENCE_LOGIN_STATUS);
                    sharedPreference.removeValueFromSharedPreference(Constants.SHARED_PREFERENCE_EMAIL_ADDRESS);
                    sharedPreference.removeValueFromSharedPreference(Constants.SHARED_PREFERENCE_PROFILE_ALREADY_LOADED);
                    sharedPreference.clearSharedPreference();
                    finish();
                    break;
                case R.id.drawer_logIn:

                    intent = new Intent(this, SignIn.class);
                    startActivity(intent);
                    sharedPreference.removeValueFromSharedPreference(Constants.SHARED_PREFERENCE_LOGIN_STATUS);
                    sharedPreference.removeValueFromSharedPreference(Constants.SHARED_PREFERENCE_EMAIL_ADDRESS);
                    sharedPreference.removeValueFromSharedPreference(Constants.SHARED_PREFERENCE_PROFILE_ALREADY_LOADED);
                    sharedPreference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_PREVIOUS_ACTIVITY,Constants.ACTIVITY_WELCOME_SCREEN);
                    finish();
                    break;
                case R.id.drawer_userPreference:

                    intent = new Intent(this, SignIn.class);
                    startActivity(intent);
                    sharedPreference.setBooleanValueInSharedPreference(Constants.SHARED_PREFERENCE_LOGIN_STATUS, false);
                    sharedPreference.setStringValueInSharedPreference(Constants.SHARED_PREFERENCE_EMAIL_ADDRESS, Constants.SHARED_PREFERENCE_DEFAULT_EMAIL);
                    finish();
                    break;

                case R.id.drawer_aboutUs:
                    intent = new Intent(this, AboutUs.class);
                    startActivity(intent);
                    finish();
                    break;

                case R.id.drawer_contactUs:
                    intent = new Intent(this, ContactUs.class);
                    startActivity(intent);
                    finish();
                    break;
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer != null) {
                drawer.closeDrawer(GravityCompat.START);
            }
        }catch(Exception e){
            generatePopupMessage(Constants.EXCEPTION_LOADING_PAGE);
        }
        return true;
    }



    private void generatePopupMessage(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create(); //Use context
        alertDialog.setTitle(Constants.ALERT_WARNING);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
