package com.grevu.item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grevu.app.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 아이템 상세 화면
 * Created by jhkim on 2014-10-27.
 */
public class DescItemActivity extends Activity {

    private LinearLayout menuLayout;
    private ImageView menuImg1, menuImg2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_desc_item);

        menuLayout = (LinearLayout) findViewById(R.id.menu_food);

        menuImg1 = (ImageView) menuLayout.findViewById(R.id.image_menu1);
        menuImg2 = (ImageView) menuLayout.findViewById(R.id.image_menu2);


        ImageLoader.getInstance().displayImage("http://121.189.39.226/source_food_01.jpg", menuImg1);
        ImageLoader.getInstance().displayImage("http://121.189.39.226/source_food_02.jpg", menuImg2);
    }
}
