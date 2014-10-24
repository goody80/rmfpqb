package com.grevu.category;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.grevu.app.R;
import com.grevu.app.util.DataUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by jhkim on 2014. 10. 16..
 */
public class ItemActivity extends Activity{

    private ImageView main_thumb;
    private ImageLoader imageLoader;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item);

        main_thumb = (ImageView) findViewById(R.id.main_thumb);

        imageLoader = ImageLoader.getInstance();

    }

    @Override
    public void onResume(){
        super.onResume();

        DisplayImageOptions option = new DisplayImageOptions.Builder()
                .cacheInMemory()
                .cacheOnDisc()
                .build();

//        imageLoader.displayImage(DataUtil.IMAGE_URL+"source_food_01.jpg", main_thumb, option);
        imageLoader.loadImage(DataUtil.IMAGE_URL+"source_food_01.jpg", option, new SimpleImageLoadingListener(){

            @Override
            public void onLoadingComplete(String imageUri, View view,
                                          Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);

                //write your code here to use loadedImage
                main_thumb.setBackground(new BitmapDrawable(getResources(), loadedImage));
            }

        });
    }


}
