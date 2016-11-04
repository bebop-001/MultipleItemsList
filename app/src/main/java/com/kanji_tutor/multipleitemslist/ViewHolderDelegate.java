/*
 *  Copyright 2016 Steven Smith sjs@sonic.net
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.kanji_tutor.multipleitemslist;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

/**
 * ViewHolderDelegate is used in conjunction with its children to simplify
 * listadapter use with multiple objects.  Customization occurs in the child
 * classes.  Each child class must have a customized child viewholder which
 * is the interface between the view and the object and the rest of the child
 * object is the implementation of the object.
 */
public abstract class  ViewHolderDelegate {
    private static final String TAG = "ViewHolderDelegate";
    /**
     * These three items are used to count the number of different child
     * view types for the objects.  ObjTypeCounter contains the count of
     * the view holder children by their resource id's.
     */
    private static SparseArray<Integer> objTypeCounter
                    = new SparseArray<Integer>();
    /**
     * getItemViewTypeCoung supplies the list adapter getViewTypeCount
     * method with the total number of differeent views supplied by
     * this method.
     *
     * @return
     */
    public static int getViewTypeCount() {return objTypeCounter.size(); }

    /**
     * This abstract class is the parent class used to generate the
     * viewholder used by the listviewadapter.  It's extended in each
     * child class to support the various views used by the child objects.
     *
     * The viewholder is tucked into the listadapter getView convertview
     * and is fetched from the view using the view.getTAG() method.
     */
    private static int viewHolderCounter;
    abstract class ViewHolder {
        private static final String TAG = "ViewHolder";
        private int ID;
        /**
         * getPosition is used in the listviewadapter to query the
         * viewholder about what posiiton it is set up for.  The line in
         * the adapter getView will look like
         * "if (position != vh.getPosition()) ...
         *
         * @return position     position for which object is configured.
         */
        abstract int getPosition();
        abstract public int getItemResId();

        protected ViewHolder() {
            ID = viewHolderCounter++;
        }
        protected int getId() { return ID; }
        abstract public String toString();
    }
    /**
     * The setupVH method is where the viewHolder is updated.  This is the
     * method called by the listadapter to update the viewholder.  The
     * ViewHolder can be
     *  1) null: create a new viewholder that matches this object.
     *  2) the same type but with a different 'position'.  In that case,
     *     update it to match the current object.
     *  3) a different type: as with the null object, create a new viewholder
     *     that corresponds to the current object.
     *
     * @param   inflater
     * @param   vh
     * @param   position
     * @return  a view corresponding to the current object with the
     *          viewholder as its TAG'ed object.
     */
    abstract protected View setupVH(
                    LayoutInflater inflater, ViewHolder vh, int position);

    /**
     * debug toString method.
     * @return a text stromg that describes the object.
     */
    abstract public String toString();

    // contextViewholder items.
    private static int delegateCounter;
    private int ID = delegateCounter++;
    public long getItemId() {
        return (long) ID;
    }
    /**
     * the resid is used to compare objects.  If two objects share the
     * same resid, they can share viewholders.
     *
     * @return an int corresponding to the view resource id.
     */
    abstract public int getItemResId();
    /**
     * Return the view type as needed by the layoutadapter getItemViewType()
     * method.  This is a 0-indexed value determined by the object resource
     * id.  See objTypeCounter above.
     */
    private int itemViewType;
    public int getItemViewType() { return itemViewType; }

    public ViewHolderDelegate(int resId) {
        // track creation and view type for each child.
        objTypeCounter.put(resId, objTypeCounter.get(resId, 0) + 1);
        itemViewType = objTypeCounter.indexOfKey(resId);
    }
}
