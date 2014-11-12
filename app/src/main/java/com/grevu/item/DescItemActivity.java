package com.grevu.item;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.grevu.app.R;
import com.grevu.view.CustomEditTextDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 아이템 상세 화면
 * Created by jhkim on 2014-10-27.
 */
public class DescItemActivity extends Activity {

    private ScrollView menuLayout;
    private ImageView menuImg1, menuImg2;
    private TextView btn_pay;
    private CustomEditTextDialog mCustomEditTextDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_desc_item);

        menuLayout = (ScrollView) findViewById(R.id.menu_food);

        menuImg1 = (ImageView) menuLayout.findViewById(R.id.image_menu1);
        menuImg2 = (ImageView) menuLayout.findViewById(R.id.image_menu2);

        btn_pay = (TextView) findViewById(R.id.btn_pay);

        ImageLoader.getInstance().displayImage("http://121.189.39.226/source_food_01.jpg", menuImg1);
        ImageLoader.getInstance().displayImage("http://121.189.39.226/source_food_02.jpg", menuImg2);

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // custom dialog
                mCustomEditTextDialog = new CustomEditTextDialog(DescItemActivity.this,
                        "결제 비밀번호",
                        leftClickListener,
                        rightClickListener);

                mCustomEditTextDialog.show();
            }
        });
    }

    private View.OnClickListener leftClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCustomEditTextDialog.dismiss();
        }
    };

    private View.OnClickListener rightClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCustomEditTextDialog.dismiss();
        }
    };

}
