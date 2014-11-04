package com.grevu.item;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.grevu.app.R;
import com.grevu.app.data.ItemData;

import java.util.ArrayList;

/**
 * Created by jhkim on 2014-10-27.
 */
public class ItemListAdapter extends BaseAdapter {

    private static final String TAG = "ItemListAdapter";
    private ArrayList<ItemData> items;
    private LayoutInflater mInflater;

    public ItemListAdapter(Context context, ArrayList<ItemData> items){
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @Override
    public int getViewTypeCount(){
        return 2;
    }

    @Override
    public int getItemViewType(int position){
        // 0이면 pager, 1이면 item
        return items.get(position).getType().equals("P") ? 0 : 1;
    }

    @Override
    public ItemData getItem(int position){
        return items.get(position);
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        int type = getItemViewType(position);
        ItemData item = items.get(position);
        ViewHolder viewHolder = null;

        if(view == null) {

            viewHolder = new ViewHolder();

            switch(type){
                case 0:
                    view = mInflater.inflate(R.layout.row_itempager, null);
                    viewHolder.itemViewPager = (ItemViewPager) view.findViewById(R.id.pager_category);
                    break;
                case 1:
                    view = mInflater.inflate(R.layout.row_itemview, null);
                    viewHolder.itemView = (ItemView) view;
            }

            view.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) view.getTag();
        }

        // viewpager에 list position 전달을 위해
        if (item.getType().equals("P")) {
            viewHolder.itemViewPager.setListPosition(position);
        }

        return view;

    }

    static class ViewHolder{
        ItemViewPager itemViewPager;
        ItemView itemView;
    }

}
