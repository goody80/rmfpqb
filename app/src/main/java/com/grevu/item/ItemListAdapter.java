package com.grevu.item;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.grevu.app.R;
import com.grevu.app.data.ItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhkim on 2014-10-27.
 */
public class ItemListAdapter extends ArrayAdapter<ItemData> {

    private static final String TAG = "ItemListAdapter";
    private ArrayList<ItemData> items;

    public ItemListAdapter(Context context, int resourceId) {
        super(context, resourceId);
    }

    public ItemListAdapter(Context context, int resourceId, ArrayList<ItemData> items) {
        super(context, resourceId, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ItemData item = getItem(position);

        if (view == null) {

            LayoutInflater vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.row_itemview, null);

            if (item != null) {
                //임시
                if (item.getType().equals("P")) {
                    view = vi.inflate(R.layout.row_itempager, null);
                } else {
                    view = vi.inflate(R.layout.row_itemview, null);
                }
            }

        }


        return view;

    }

}
