package com.kanji_tutor.multipleitemslist;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sjs on 10/31/16.
 */

public class ItemVH extends ViewHolder {
    private static final String TAG = "ItemVH";
    private static final int resId = R.layout.item;

    protected View view;
    protected TextView itemTV;
    protected int position;
    protected Item item;

    @Override  protected int getItemResId() {return resId; }

    protected ItemVH(LayoutInflater inflater) {
        super(resId);
        view = inflater.inflate(resId, null);
        view.setId(super.getId());
        itemTV = (TextView)view.findViewById(R.id.item_text);
    }
    @Override
    int getPosition() {
        return position;
    }
    @Override
    public String toString() {
        return String.format("%s:position=%d:text=\"%s\":resId=0x%08x",
            TAG, position, itemTV.getText().toString(), resId);
    }
}
