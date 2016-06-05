package com.eazylivings.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.eazylivings.R;
import com.eazylivings.activities.login.SignIn;
import com.eazylivings.activities.services.OfferedServices;
import com.eazylivings.activities.services.WalkthroughServices;

import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;

import com.eazylivings.carousel.MyPagerAdapter;

public class WelcomeScreen extends FragmentActivity
implements NavigationView.OnNavigationItemSelectedListener {
    String userName;
    SharedPreference sharedPreference;

    public final static int LOOPS = 1000;
    public static int FIRST_PAGE; // = count * LOOPS / 2;
    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.7f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    public MyPagerAdapter adapter;
    public ViewPager pager;
    /*** variables for the View */
    public int coverUrl[];
    public static int count;

    public static WelcomeScreen mainActivityCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        coverUrl = new int[] { R.drawable.one, R.drawable.two,
                R.drawable.three, R.drawable.four, R.drawable.five };

        mainActivityCtx = this;
        pager = (ViewPager) findViewById(R.id.myviewpager);
        count = coverUrl.length;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = 0;
        pageMargin = (int) ((metrics.widthPixels / 4) *2);
        pager.setPageMargin(-pageMargin);

        try {
            adapter = new MyPagerAdapter(this,this.getSupportFragmentManager());
            pager.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            FIRST_PAGE = count * LOOPS / 2;

            pager.setOnPageChangeListener(adapter);
            // Set current item to the middle page so we can fling to both
            // directions left and right
            pager.setCurrentItem(FIRST_PAGE); // FIRST_PAGE
            // pager.setFocusableInTouchMode(true);
            pager.setOffscreenPageLimit(3);
            // Set margin for pages as a negative number, so a part of next and
            // previous pages will be showed

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null){
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
    }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if(drawer!=null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if(navigationView!=null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    public void setWelcomeScreen(){

        //1. Check whether any user is logged in or not
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean isUserLoggedIn=prefs.getBoolean(Constants.SHARED_PREFERENCE_LOGIN_STATUS,false);

        //2. If user is logged in
        if(isUserLoggedIn){
            //2a. Set Title Bar with user Name.
            userName = prefs.getString(Constants.SHARED_PREFERENCE_USERNAME,Constants.SHARED_PREFERENCE_DEFAULT_USERNAME);
            setTitle("Welcome "+ userName + "!!");

            //2b. Set Overflow Menu. This is already taken Care of.

            //2c. Set User Profile

        }

        //3. If user is not logged In
        else{
            //3a. Set Title Bar with Newbie.
            setTitle("Welcome Newbie!!");

            //3b. Set Overflow Menu. This is already taken Care of.
        }


    }

    public void onClickStart(View view){
        Intent intent=new Intent(getApplicationContext(), OfferedServices.class);
        startActivity(intent);

    }

    public void onClickExplore(View view){

        Intent intent=new Intent(getApplicationContext(), WalkthroughServices.class);
        intent.putExtra(Constants.SHARED_PREFERENCE_CLICKED_SERVICE,0);
        startActivity(intent);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean isUserLoggedIn = prefs.getBoolean(Constants.SHARED_PREFERENCE_LOGIN_STATUS,false);

        if(isUserLoggedIn) {
            getMenuInflater().inflate(R.menu.logout, menu);
        }else{
            getMenuInflater().inflate(R.menu.login, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        super.onOptionsItemSelected(item);
        sharedPreference=new SharedPreference();

        switch(item.getItemId()){
            case R.id.userProfile:

                if(sharedPreference.getBooleanValueFromSharedPreference(getApplicationContext(),Constants.SHARED_PREFERENCE_LOGIN_STATUS)){
                    intent = new Intent(this, MyAccount.class);
                    intent.putExtra(Constants.SHARED_PREFERENCE_USERNAME,userName);
                    startActivity(intent);
                }else{
                    generatePopupMessage(Constants.LOGIN_FOR_PROFILE);
                }

                break;

            case R.id.logOut:

                intent = new Intent(this, SignIn.class);
                startActivity(intent);
                sharedPreference.removeValueFromSharedPreference(getApplicationContext(),Constants.SHARED_PREFERENCE_LOGIN_STATUS);
                sharedPreference.removeValueFromSharedPreference(getApplicationContext(),Constants.SHARED_PREFERENCE_USERNAME);
                sharedPreference.removeValueFromSharedPreference(getApplicationContext(),Constants.SHARED_PREFERENCE_PROFILE_ALREADY_LOADED);
                sharedPreference.clearSharedPreference(getApplicationContext());
                finish();
                break;
            case R.id.logIn:

                intent = new Intent(this, SignIn.class);
                startActivity(intent);
                sharedPreference.removeValueFromSharedPreference(getApplicationContext(),Constants.SHARED_PREFERENCE_LOGIN_STATUS);
                sharedPreference.removeValueFromSharedPreference(getApplicationContext(),Constants.SHARED_PREFERENCE_USERNAME);
                sharedPreference.removeValueFromSharedPreference(getApplicationContext(),Constants.SHARED_PREFERENCE_PROFILE_ALREADY_LOADED);
                finish();
                break;
            case R.id.preferences:
                finish();
                intent = new Intent(this, SignIn.class);
                startActivity(intent);
                sharedPreference.setBooleanValueInSharedPreference(getApplicationContext(),Constants.SHARED_PREFERENCE_LOGIN_STATUS, false);
                sharedPreference.setStringValueInSharedPreference(getApplicationContext(),Constants.SHARED_PREFERENCE_USERNAME,Constants.SHARED_PREFERENCE_DEFAULT_USERNAME);
                break;
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer!=null) {
            drawer.closeDrawer(GravityCompat.START);
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
