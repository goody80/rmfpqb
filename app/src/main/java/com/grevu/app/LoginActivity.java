package com.grevu.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.grevu.category.CategoryActivity;

/**
 * Created by jhkim on 2014-10-08.
 */
public class LoginActivity extends FragmentActivity {

    private FacebookFragment facebookFragment;
    private ImageView btn_twitter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            facebookFragment = new FacebookFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, facebookFragment)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            facebookFragment = (FacebookFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }

        setContentView(R.layout.activity_login);
        btn_twitter = (ImageView) findViewById(R.id.btn_twitter);
        btn_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
    }

}