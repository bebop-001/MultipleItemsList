package com.kanji_tutor.multipleitemslist;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sjs on 10/23/16.
 */

public class Item extends ViewHolderDelegate {
    private static final String TAG = "Item";
    private static final int resId = R.layout.item;

    private static VH_Stack stack;

    // viewHolderholder items.
    private TextView tv;
    private String itemText;

    public Item(String itemText) {
        super(resId, TAG);
        if (stack == null)
            stack = new ViewHolderDelegate.VH_Stack(TAG);
        setStack(stack, TAG);
        this.itemText = itemText;
    }

    @Override
    public View updateVH(View viewHolder) {
        tv = (TextView) viewHolder.findViewById(R.id.item_text);
        String oldText = tv.getText().toString();
        tv.setText(itemText);
        Log.e(TAG, "Item:UpdateVHviewID=" + viewHolder.getId()
            + ":position=" + getPosition()
            + ":text=\"" + oldText + "\"->\"" + itemText + "\"");
        return viewHolder;
    }
}
