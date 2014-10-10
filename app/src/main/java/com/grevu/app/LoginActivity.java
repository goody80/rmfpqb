package com.grevu.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by jhkim on 2014-10-08.
 */
public class LoginActivity extends FragmentActivity {

    private FacebookFragment facebookFragment;

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
    }

}