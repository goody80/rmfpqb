package com.grevu.item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.grevu.app.R;

/**
 * 아이템 상세 화면
 * Created by jhkim on 2014-10-27.
 */
public class DescItemActivity extends Activity {

    TextView idxPhoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_desc_item);
        idxPhoto = (TextView) findViewById(R.id.idx_photo);

        Intent intent = getIntent();
        idxPhoto.setText("Shop #" + String.valueOf(intent.getIntExtra("index", 0)) + "의 메뉴화면");
    }
}
