package com.kanji_tutor.multipleitemslist;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sjs on 10/23/16.
 */

public class Item extends ViewHolderDelegate {
    private static final String TAG = "Item";
    private static final int resId = R.layout.item;

    private String itemText;
    public class ItemVH extends ViewHolder {
        private static final String TAG = "ItemVH";

        protected View view;
        protected TextView itemTV;
        protected int position;
        protected Item item;

        @Override  public int getItemResId() {return resId; }

        protected ItemVH(LayoutInflater inflater) {
            super();
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

    @Override public int getItemResId() { return resId; }

    public Item(String itemText) {
        super(resId, TAG);
        this.itemText = itemText;
    }
    @Override
    protected View setupVH(LayoutInflater inflater, ViewHolderDelegate.ViewHolder vh, int position) {
        ItemVH viewHolder;
        if (vh == null || vh.getItemResId() != getItemResId())
            viewHolder = new ItemVH(inflater);
        else
            viewHolder =  (ItemVH) vh;
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