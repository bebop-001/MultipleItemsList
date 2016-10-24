package com.kanji_tutor.multipleitemslist;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sjs on 10/23/16.
 */

public class Item implements ViewHolderDelegate {
    private static final String TAG = "ViewHolderDelegate";
    private static final int resId = R.layout.item;
    private static final long itemType = (long) R.layout.item;

    private int position;

    // viewHolderholder items.
    private View viewHolder;
    private TextView tv;
    private String itemText;

    public Item(String itemText) {
        this.itemText = itemText;
    }

    @Override
    public int getPosition() {
        return (viewHolder == null) ? -1 : position;
    }

    @Override
    public long getItemType() {
        return itemType;
    }

    @Override
    public View updateVH(View viewHolder) {
        tv = (TextView) viewHolder.findViewById(R.id.item_text);
        tv.setText(itemText);
        return viewHolder;

    }
    @Override
    public View newVH(LayoutInflater inflater, int position) {
        this.position = position;
        if (viewHolder == null) {
            viewHolder = (View) inflater.inflate(resId, null);
        }
        return updateVH(viewHolder);
    }

    @Override
    public View getVH() {
        return viewHolder;
    }

    @Override
    public void freeVH() {
        viewHolder = null;
    }

    @Override
    public View moveFromVH(ViewHolderDelegate from, int position) {
        Log.e(TAG, "moveFromVH:from=" + from.getPosition() + ":to=" + position);
        this.position = position;
        viewHolder = from.getVH();
        from.freeVH();
        return updateVH(viewHolder);
    }
}
