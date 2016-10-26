package com.kanji_tutor.multipleitemslist;

import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by sjs on 10/23/16.
 */

public abstract class  ViewHolderDelegate {
    private String TAG = "ViewHolderDelegate";

    class VH_Stack {
        SparseArray<View> stack;
        String stackName;
        int current = 0;
        void push (View view) {
            view.setTag(null);
            stack.put(current++, view);
            Log.e(TAG, "push:id=" + view.getId());
        }
        View pop () {
            View view = null;
            if (current > 0) {
                view = stack.get(--current);
                Log.e(TAG, "pop:id=" + view.getId());
            }
            return view;
        }
        VH_Stack(String stackName) {
            this.stackName = stackName;
            stack = new SparseArray<View>();
        }
    }

    abstract public View updateVH(View viewHolder);

    private int resId;
    private long itemType;

    private int position;

    // viewHolderholder items.
    private View viewHolder;
    private static int VH_counter;
    private int ID = VH_counter++;
    interface Viewholder {
        View getViewholder();
        View updateVH();
        View newVH(LayoutInflater inflater, String name, int ID);
    }

    private VH_Stack stack;
    private String stackName;
    protected void setStack (VH_Stack stack, String stackName) {
        this.stack = stack;
        this.stackName = stackName;
    }
    public void saveVH() {
        stack.push(viewHolder);
        viewHolder = null;
    }

    public ViewHolderDelegate(int resId, String childName) {
        this.resId = resId;
        this.itemType = (long) resId;
        this.TAG = "ViewHolderDelegate:" + childName;
    }

    public int getPosition() {
        return (viewHolder == null) ? -1 : position;
    }
    public int getId() { return this.ID; }

    public long getItemType() {
        return itemType;
    }
    private static int viewCounter;
    private Viewholder VH;
    public View newVH(LayoutInflater inflater, int position) {
        this.position = position;
        if (viewHolder == null) {
            viewHolder = stack.pop();
            if(viewHolder == null) {
                viewHolder = (View) inflater.inflate(resId, null);
                viewHolder.setId(viewCounter++);
            }
        }
        Log.e(TAG, "newVH:itemID=" + this.ID + ":view id=" + viewHolder.getId());
        return updateVH(viewHolder);
    }

    public View getVH() {
        return viewHolder;
    }

    public void freeVH() {
        Log.e(TAG, "freeVH:itemID=" + this.ID
            + ":view=" + ((viewHolder == null) ? "Already free!" : "id=" + viewHolder.getId()));
        viewHolder = null;
    }

    public View moveToVH(View viewHolder, int position) {
        ViewHolderDelegate from = (ViewHolderDelegate)viewHolder.getTag();
        Log.e(TAG, "moveFromVH:from=" + from.getPosition() + ":to=" + this.getPosition() + ":new=" + position);
        viewHolder = updateVH(viewHolder);
        this.position = position;
        viewHolder.setTag(this);
        from.freeVH();
        return updateVH(viewHolder);
    }
    public String toString() {
        View vh = this.viewHolder;
        ViewHolderDelegate vhd = (ViewHolderDelegate)vh.getTag();
        return TAG
            + ":View:"    + ((vh == null) ? "NULL" : "ID=" + vh.getId())
            + ":view tag=" + ((vhd == null) ? "NULL" : vhd.TAG + ":ID=" + vhd.ID);
    }
}
