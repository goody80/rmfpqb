package com.grevu.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.grevu.app.view.LoginActivity;
import com.grevu.category.CategoryActivity;
import com.grevu.view.IntroBackgroundView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IntroActivity extends Activity{

    final static String TAG = "IntroActivity";

    IntroBackgroundView draw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String fbLoginYn = pref.getString("fb", "");

        if(fbLoginYn.isEmpty()) {
            setContentView(R.layout.activity_intro);

            draw = new IntroBackgroundView(this);
            draw.setBackgroundResource(R.drawable.intro_bg);

//        ImageView logo = new ImageView(this);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.CENTER;
//        logo.setLayoutParams(params);
//        logo.setBackgroundResource(R.drawable.intro_logo);
//
//        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.intro_bg);
//        frameLayout.addView(draw);
//        frameLayout.addView(logo);
//
//        Animation slideUp = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.slide_up);
//        slideUp.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//            @Override
//            public void onAnimationRepeat(Animation animation) {}
//            @Override
//            public void onAnimationStart(Animation animation) {}
//        });
//        draw.startAnimation(slideUp);


            //인트로 테스트
            //추후 데이터 로딩 예정
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(2000);
                        Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();

        }else{
            Intent intent = new Intent(IntroActivity.this, CategoryActivity.class);
            startActivity(intent);
        }

        Log.i(TAG, printKeyHash(this).toString());


    }

    @Override
    public void onResume(){

        super.onResume();

    }

    @Override
    public void finish(){

    }

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {

            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);

            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }

        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

}
