package com.kanji_tutor.multipleitemslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by sjs on 10/23/16.
 */

public class MultiItemListAdapter<E> extends BaseAdapter {
    private static final String TAG = "MultiItemListAdapter";

    private ArrayList<ViewHolderDelegate> items;
    private LayoutInflater inflater;

    public MultiItemListAdapter(Context context, ArrayList<ViewHolderDelegate> items) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        int itemResId = (int) getItem(position).getItemResId();
        return ViewHolderDelegate.getItemViewType(itemResId);
    }

    @Override
    public int getViewTypeCount() {
        return ViewHolderDelegate.getViewTypeCount();
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
        return getItem(position).getItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderDelegate.ViewHolder vh = (convertView == null || convertView.getTag() == null)
            ? null
            : (ViewHolderDelegate.ViewHolder) convertView.getTag();
        if (vh == null || vh.getPosition() != position) {
            convertView = getItem(position).setupVH(inflater, vh, position);
        }
        return convertView;
    }

}

