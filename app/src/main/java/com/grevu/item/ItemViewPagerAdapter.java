package com.grevu.item;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by jhkim on 2014-10-27.
 */
public class ItemViewPagerAdapter extends PagerAdapter {

    private static final String TAG = "ItemViewPagerAdapter";
    private final String[] sDrawableUrls = {"http://tong.visitkorea.or.kr/cms/resource/12/1954312_image2_1.jpg?&name=image2&index=1", "http://tong.visitkorea.or.kr/cms/resource/11/1954311_image2_1.jpg?&name=image2&index=1"
            , "http://tong.visitkorea.or.kr/cms/resource/09/1954309_image2_1.jpg?&name=image2&index=1", "http://tong.visitkorea.or.kr/cms/resource/04/1954304_image2_1.jpg?&name=image2&index=1"};

    Context context;

    public ItemViewPagerAdapter(Context context) {
        this.context = context;
        Log.d(TAG, "create ItemViewPagerAdapter");
    }

    @Override
    public int getCount() {
        return sDrawableUrls.length;
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        ImageView photoView = new ImageView(container.getContext());
        photoView.setScaleType(ImageView.ScaleType.FIT_XY);

        // Now just add PhotoView to ViewPager and return it
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ImageLoader.getInstance().displayImage(sDrawableUrls[position], photoView);

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DescItemActivity.class);
                intent.putExtra("index", position);
                context.startActivity(intent);
            }
        });

        return photoView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
