package com.kanji_tutor.multipleitemslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by sjs on 10/23/16.
 */

public class MultiItemListAdapter<E> extends BaseAdapter {
    private static final String TAG = "MultiItemListAdapter";

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

    private ArrayList<ViewHolderDelegate> items;
    private LayoutInflater inflater;

    private TreeSet<Integer> mSeparatorsSet = new TreeSet<Integer>();

    public static class ViewHolder {
        public TextView textView;
    }

    public MultiItemListAdapter(Context c, ArrayList<ViewHolderDelegate> items) {
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return (position % 4 == 0) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ViewHolderDelegate getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getItemType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderDelegate item = items.get(position);
        if (convertView == null) {
            convertView = item.newVH(inflater, position);
        }
        else {
            ViewHolderDelegate currentItem = (ViewHolderDelegate)convertView.getTag();
            if (currentItem.getItemType() == item.getItemType()) {
                if (currentItem.getPosition() != item.getPosition()) {
                    item.moveFromVH(currentItem, position);
                }
            }
            else {
                currentItem.freeVH();
                item.newVH(inflater, position);
            }
        }
        convertView.setTag(item);
        return convertView;
    }

}

