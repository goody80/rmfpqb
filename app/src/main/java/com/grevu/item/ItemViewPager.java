package com.grevu.item;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.grevu.app.R;

/**
 * viewpager 형태의 item
 * Created by jhkim on 2014-10-27.
 */
public class ItemViewPager extends ViewPager {

    private static final String TAG = "ItemViewPager";

    private ItemViewPagerAdapter pagerAdapter;
    private Context context;
    private ViewPager mViewPager;

    public ItemViewPager(Context context) {
        super(context);
        init();
    }

    public ItemViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        pagerAdapter = new ItemViewPagerAdapter(context);
        setAdapter(pagerAdapter);

    }

}
