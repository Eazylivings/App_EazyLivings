package com.eazylivings.carousel;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;

public class MyFragment extends Fragment {

    public static Fragment newInstance(WelcomeScreen context, int pos, float scale) {
        Bundle b = new Bundle();
        b.putInt("pos", pos);
        b.putFloat("scale", scale);

        return Fragment.instantiate(context, MyFragment.class.getName(), b);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500,500);
        inflater=getLayoutInflater(savedInstanceState);
        LinearLayout fragmentLL  = (LinearLayout) inflater.inflate(R.layout.mf, container, false);
        int pos   = this.getArguments().getInt("pos");
        /*TextView tv  = (TextView) fragmentLL.findViewById(R.id.text);

        tv.setText("Image " +  pos );*/

        ImageView iv = (ImageView) fragmentLL.findViewById(R.id.pagerImg);

        iv.setLayoutParams(layoutParams);
        iv.setImageResource(WelcomeScreen.mainActivityCtx.coverUrl[pos] );
        iv.setPadding(5, 5, 5, 5);

        MyLinearLayout root = (MyLinearLayout) fragmentLL.findViewById(R.id.root);
        float scale   = this.getArguments().getFloat("scale");
        root.setScaleBoth(scale);

        return fragmentLL;
    }
}
