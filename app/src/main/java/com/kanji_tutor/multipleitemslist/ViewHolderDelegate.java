package com.kanji_tutor.multipleitemslist;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by sjs on 10/23/16.
 */

public abstract class  ViewHolderDelegate {
    private String TAG = "ViewHolderDelegate";
    private static SparseArray<Integer> objTypeCounter = new SparseArray<Integer>();
    public static int getItemViewType(int resId) {return objTypeCounter.indexOfKey(resId); }
    public static int getViewTypeCount() {return objTypeCounter.size(); }

    private static int viewHolderCounter;
    abstract class ViewHolder {
        private static final String TAG = "ViewHolder";
        private int ID;

        abstract public String toString();
        abstract int getPosition();

        abstract protected int getItemResId();
        protected ViewHolder(int resId) {
            ID = viewHolderCounter++;
        }
        protected int getId() { return ID; }
    }

    // Setup the view holder associated with (possibly another) delegate.
    abstract protected View setupVH(LayoutInflater inflater, ViewHolder vh, int position);

    abstract public String toString();

    // contextViewholder items.
    private static int VH_counter;
    private int ID, itemViewType;

    abstract public int getItemResId();

    public ViewHolderDelegate(int resId, String childName) {
        this.itemViewType = resId;
        objTypeCounter.put(resId, objTypeCounter.get(resId, 0) + 1);
        this.TAG = "ViewHolderDelegate->" + childName;
    }
    public long getItemId() {
        return (long) ID;
    }
}
