package com.grevu.item;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.Random;

/**
 * 단순 카드 형태의 item
 * Created by jhkim on 2014-10-27.
 */
public class ItemView extends LinearLayout {

    private DisplayImageOptions options;

    public ItemView(Context context) {
        super(context);
        init();
    }

    public ItemView(android.content.Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemView(android.content.Context context, android.util.AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {


    }

}
