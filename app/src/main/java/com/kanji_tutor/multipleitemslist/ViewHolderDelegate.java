package com.kanji_tutor.multipleitemslist;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by sjs on 10/23/16.
 */

public abstract class  ViewHolderDelegate {
    private String TAG = "ViewHolderDelegate";
    private static int viewHolderCounter;

    abstract class ViewHolder {
        private int ID;
        protected int getId() { return ID; }
        protected ViewHolder() {
            ID = viewHolderCounter++;
        }
    }

    class VH_Stack<E> {
        SparseArray<E> stack;
        String stackName;
        int current = 0;
        void push(E viewHolder) {
            stack.put(current++, viewHolder);
        }
        E pop() {
            E viewHolder = null;
            if (current > 0) {
                viewHolder = stack.get(--current);
            }
            return viewHolder;
        }
        VH_Stack(String stackName) {
            this.stackName = stackName;
            stack = new SparseArray<E>();
        }
    }

    private long itemType;
    private int position;

    // contextViewholder items.
    private static int VH_counter;
    private int ID = VH_counter++;

    public ViewHolderDelegate(int resId, String childName) {
        this.itemType = (long) resId;
        this.TAG = "ViewHolderDelegate->" + childName;
    }

    public int getPosition() {
        return (getVH() == null) ? -1 : position;
    }
    public int getId() { return this.ID; }

    public long getItemType() {
        return itemType;
    }

    abstract public View getView();
    abstract protected ViewHolder getVH();
    abstract protected View setVH();
    abstract public void freeVH();
    abstract public void saveVH();
    /**
     * protected View newView(LayoutInflater inflater)
     * @param inflater
     * @return
     */
    abstract protected View newVH(LayoutInflater inflater);

    protected View newVH(LayoutInflater inflater, int position) {
        this.position = position;
        return newVH(inflater);
    }
    abstract protected View moveView (LayoutInflater inflater, View fromView);
    public View moveView(LayoutInflater inflater, View fromView, int position) {
        this.position = position;
        return moveView(inflater, fromView);
    }
}
