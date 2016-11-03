package com.kanji_tutor.multipleitemslist;

/**
 * Created by sjs on 10/31/16.
 */

abstract class ViewHolder {
    private static final String TAG = "ViewHolder";
    private static int viewHolderCounter;
    private int ID;

    abstract public String toString();
    abstract int getPosition();

    abstract protected int getItemResId();
    protected ViewHolder(int resId) {
        ID = viewHolderCounter++;
    }
    protected int getId() { return ID; }
}
