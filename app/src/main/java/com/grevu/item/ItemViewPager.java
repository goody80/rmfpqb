package com.grevu.item;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * viewpager 형태의 item
 * Created by jhkim on 2014-10-27.
 */
public class ItemViewPager extends ViewPager {

    private static final String TAG = "ItemViewPager";

    private ItemViewPagerAdapter pagerAdapter;
    private int listPosition = 0;

    public ItemViewPager(Context context) {
        super(context);
        init(context);
    }

    public ItemViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        pagerAdapter = new ItemViewPagerAdapter(context);
        setAdapter(pagerAdapter);
    }

    // list의 position 정보 set,get
    public void setListPosition(int listPosition) {
        this.listPosition = listPosition;
    }

    public int getListPosition() {
        return listPosition;
    }

}
