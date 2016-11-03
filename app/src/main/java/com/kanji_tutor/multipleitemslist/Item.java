package com.kanji_tutor.multipleitemslist;


import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by sjs on 10/23/16.
 */

public class Item extends ViewHolderDelegate {
    private static final String TAG = "Item";
    private static final int resId = R.layout.item;

    private String itemText;

    @Override public int getItemResId() { return resId; }

    public Item(String itemText) {
        super(resId, TAG);
        this.itemText = itemText;
    }
    @Override
    protected View setupVH(LayoutInflater inflater, ViewHolder vh, int position) {
        ItemVH viewHolder;
        if (vh == null || vh.getItemResId() != getItemResId())
            viewHolder = new ItemVH(inflater);
        else
            viewHolder = (ItemVH) vh;
        viewHolder.position = position;
        viewHolder.itemTV.setText(itemText);
        viewHolder.item = this;
        return viewHolder.view;
    }

    @Override
    public String toString() {
        return TAG + ":text=\"" + itemText + "\"";
    }

}