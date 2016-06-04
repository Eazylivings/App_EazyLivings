package com.eazylivings.carousel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;

public class MyPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    private MyLinearLayout cur = null;
    private MyLinearLayout next = null;
    private WelcomeScreen context;
    private FragmentManager fm;
    private float scale;
    int pCount = 0;

    public MyPagerAdapter(WelcomeScreen context, FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        try {
            if (position == WelcomeScreen.FIRST_PAGE)
                scale = WelcomeScreen.BIG_SCALE;
            else
                scale = WelcomeScreen.SMALL_SCALE;

            position = position % WelcomeScreen.count;

        } catch (Exception e) {
            // TODO: handle exception
        }
        return MyFragment.newInstance(context, position, scale);
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = WelcomeScreen.count * WelcomeScreen.LOOPS;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return  count  ;
    }

    @Override
    public void onPageScrolled( int position, float positionOffset, int positionOffsetPixels){
        try {
            if (positionOffset >= 0f && positionOffset <= 1f) {
                cur = getRootView( position );
                next = getRootView( position +1 );

                cur.setScaleBoth(WelcomeScreen.BIG_SCALE   - WelcomeScreen.DIFF_SCALE * positionOffset);
                next.setScaleBoth(WelcomeScreen.SMALL_SCALE  + WelcomeScreen.DIFF_SCALE * positionOffset);

                pCount++;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private MyLinearLayout getRootView(int position) {
        return (MyLinearLayout) fm.findFragmentByTag(this.getFragmentTag(position)).getView().findViewById(R.id.root);
    }

    private String getFragmentTag(int position) {
        return "android:switcher:" + context.pager.getId() + ":" + position;
    }

}

