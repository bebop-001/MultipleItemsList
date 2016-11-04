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

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * The MultiItemListAdapter class is used in conjunction with the
 * ViewHolderDelegate and its children to create list views that
 * support multiple different types of views.
 */
public class MultiItemListAdapter extends BaseAdapter {
    private static final String TAG = "MultiItemListAdapter";

    // items to display in the list.
    private ArrayList<ViewHolderDelegate> items;
    // inflater used to 'inflate' a view from its resource id.
    private LayoutInflater inflater;
    /**
     * class constructor.  Context is necessary for getting an inflater
     * for creating the views placed in the list view.  items is the
     * object list for items to be displayed.
     *
     * @param context
     * @param items
     */
    public MultiItemListAdapter(Context context
                    , ArrayList<ViewHolderDelegate> items) {
        inflater
            = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }
    /**
     * getItemViewType returns an int between 0 and N where N is the one
     * less than the number of different view types that the list objects
     * create.
     *
     * @param  position the position of the object in the list.
     * @return view     type index
     */
    @Override
    public int getItemViewType(int position) {
        return getItem(position).getItemViewType();
    }
    /**
     * Used to get the number of view types in the input object list..
     * @return int total number of possible views.
     */
    @Override
    public int getViewTypeCount() {
        return ViewHolderDelegate.getViewTypeCount();
    }
    /**
     * Return the total number of objects in the list.
     * @return int number of items in list
     */
    @Override
    public int getCount() {
        return items.size();
    }
    /**
     * getItem returns the object associted with the position in the list.
     * @param position  position of an item in the list view/item list.
     * @return object   from the items list.
     */
    @Override
    public ViewHolderDelegate getItem(int position) {
        return items.get(position);
    }
    /**
     * ID doesn't seem to have a defined meaning so is used (I think) for
     * whatever you want.
     *
     * @param position  position of an item in the list view/item list
     * @return ID       ID of associated object.
     */
    @Override
    public long getItemId(int position) {
        return getItem(position).getItemId();
    }
    /**
     * getView is the method called when the list view changes.  It is used
     * for view recycling.  convertView is the view for the item in the
     * list that has just become visible.  It will be either null or contain
     * info for a list view that has just disappeared.  The purpose of
     * this method is to update the convertView so it displays the proper
     * information for the position in the listview.
     *
     * The convertView TAG contains the view holder which contains info
     * on the view and allows the program to update the convertView
     * appropriately.
     *
     * @param position      position of item that has just become visible.
     * @param convertView   view for object that has just become visible.
     * @param parent        parent for convert view.  Usualy the id for
     *                      the list view.
     * @return view         for associated object.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderDelegate.ViewHolder vh
                = (convertView == null || convertView.getTag() == null)
            ? null
            : (ViewHolderDelegate.ViewHolder) convertView.getTag();
        if (vh == null || vh.getPosition() != position) {
            Log.e(TAG, "getView 1:convertView:"
                + ((convertView == null) ? "null"
                    : ("ID=" + convertView.getId()
                    + (":vh=" + ((vh == null)
                        ? "null"
                        : vh.toString())
                        )
                    )
                )
            );
            convertView = getItem(position).setupVH(inflater, vh, position);
            Log.e(TAG, "getView 2:convertView:ID=" + convertView.getId()
                + ":vh="
                + ((ViewHolderDelegate.ViewHolder)convertView.getTag())
                        .toString());
        }
        return convertView;
    }
}

