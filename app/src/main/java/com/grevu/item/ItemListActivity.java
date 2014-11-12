package com.grevu.item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.grevu.app.R;
import com.grevu.app.data.ItemData;

import java.util.ArrayList;
import java.util.Random;

/**
 * 아이템 카테고리
 * Created by jhkim on 2014. 10. 16..
 */
public class ItemListActivity extends Activity {

    private ArrayList<ItemData> mItemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item);

        mItemList = new ArrayList<ItemData>();

        ///////////테스트 데이터 생성//////////////
        ItemData data1 = new ItemData();
        data1.setType("P");

        ItemData data2 = new ItemData();
        data2.setType("V");

        Random generator = new Random();
        for (int i = 0; i < 7; i++) {
            mItemList.add(i, (generator.nextInt(2) == 0) ? data1 : data2);
        }
        ///////////테스트 데이터 생성//////////////


        ListView listView = (ListView) findViewById(R.id.list_item);
        ItemListAdapter adapter = new ItemListAdapter(this, mItemList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(ItemListActivity.this, DescItemActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
    }

}
