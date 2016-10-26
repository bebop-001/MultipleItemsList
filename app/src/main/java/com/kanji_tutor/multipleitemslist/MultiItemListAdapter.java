package com.kanji_tutor.multipleitemslist;

import android.content.Context;
import android.util.Log;
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
Log.e(TAG, "0 cv:position=" + position
    + ((convertView == null)
        ? ":no view"
        : ":viewID=" + convertView.getId() + ":tag:" + ((convertView.getTag() == null)
            ? "NULL"
            : "ItemID=" + ((ViewHolderDelegate) convertView.getTag()).getId())));
        if (convertView == null) {
            convertView = item.newVH(inflater, position);
            convertView.setTag(item);
        }
        else {
            ViewHolderDelegate currentItem = (ViewHolderDelegate)convertView.getTag();
Log.e(TAG, "1 cv:viewID=" + convertView.getId() + ":tag:" + ((convertView.getTag() == null) ? "NULL" : "ItemID=" + ((ViewHolderDelegate) convertView.getTag()).getId()));
            if (currentItem.getItemType() == item.getItemType()) {
                if (position != currentItem.getPosition()) {
Log.e(TAG, "2 cv:viewID=" + convertView.getId() + ":tag:" + ((convertView.getTag() == null) ? "NULL" : "ItemID=" + ((ViewHolderDelegate) convertView.getTag()).getId()));
                    convertView = item.moveToVH(convertView, position);
                    convertView.setTag(item);
                }
                else {
                    Log.e(TAG, "3 cv:viewID=" + convertView.getId() + ":tag:" + ((convertView.getTag() == null) ? "NULL" : "ItemID=" + ((ViewHolderDelegate) convertView.getTag()).getId()));

                }
                // else previous position and current are same -- this does happen!S
            }
            else {
                Log.e(TAG, "4  cv:viewID=" + convertView.getId() + ":tag:" + ((convertView.getTag() == null) ? "NULL" : "ItemID=" + ((ViewHolderDelegate) convertView.getTag()).getId()));
                Log.e(TAG, "4+ cv:itemID=" + item.getId() + ":itemPosition=" + item.getPosition() + "new position=" + position);
                currentItem.freeVH();
                // currentItem.saveVH();
                convertView = item.newVH(inflater, position);
                convertView.setTag(item);
            }
        }
        parent.invalidate();
Log.e(TAG, "5 cv:viewID=" + convertView.getId() + ":tag:" + ((convertView.getTag() == null) ? "NULL" : "ItemID=" + ((ViewHolderDelegate) convertView.getTag()).getId()));
        return convertView;
    }

}

