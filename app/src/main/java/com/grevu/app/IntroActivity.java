package com.grevu.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.grevu.view.IntroBackgroundView;

public class IntroActivity extends Activity{

    IntroBackgroundView draw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);

        draw = new IntroBackgroundView(this);
        draw.setBackgroundResource(R.drawable.intro_bg);

        ImageView logo = new ImageView(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        logo.setLayoutParams(params);
        logo.setBackgroundResource(R.drawable.intro_logo);

        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.intro_bg);
        frameLayout.addView(draw);
        frameLayout.addView(logo);

        Animation slideUp = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.slide_up);
        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationStart(Animation animation) {}
        });
        draw.startAnimation(slideUp);


        //인트로 테스트
        //추후 데이터 로딩 예정
//        Thread thread = new Thread(new Runnable() {
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        thread.start();

    }

    @Override
    public void onResume(){

        super.onResume();



    }

    @Override
    public void finish(){

    }
}
