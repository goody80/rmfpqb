package com.grevu.item;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.grevu.app.R;
import com.grevu.app.data.ItemData;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jhkim on 2014-10-27.
 */
public class ItemListAdapter extends ArrayAdapter<ItemData> {

    private static final String TAG = "ItemListAdapter";
    private ArrayList<ItemData> items;
    private Context context;

    public ItemListAdapter(Context context, int resourceId) {
        super(context, resourceId);
        this.context = context;
    }

    public ItemListAdapter(Context context, int resourceId, ArrayList<ItemData> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        ItemData item = getItem(position);

        if (view == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());

            if (item != null) {
                //임시
                if (item.getType().equals("P")) {
                    view =  inflater.inflate(R.layout.row_itempager, null);

                    // viewpager에 list position 전달을 위해
                    ItemViewPager pager = (ItemViewPager) view.findViewById(R.id.pager_category);
                    pager.setListPosition(position);

                } else {
                    view = inflater.inflate(R.layout.row_itemview, null);
                    Log.d(TAG, "view : " + view + " " + parent);
                    Random rnd = new Random();
                    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    view.setBackgroundColor(color);

                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, DescItemActivity.class);
                            intent.putExtra("index", position);
                            context.startActivity(intent);
                        }
                    });
                }

            }
        }

        return view;

    }

}
