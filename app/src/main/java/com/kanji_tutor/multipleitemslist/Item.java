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

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * child of ViewHolderDelegate.  viewholder creation and object customization
 * happen here.
 */
public class Item extends ViewHolderDelegate {
    private static final String TAG = "Item";
    private static final int resId = R.layout.item;

    public class ItemVH extends ViewHolder {
        private static final String TAG = "ItemVH";

        // view elements for viewholder.
        protected View view;
        protected TextView itemTV;
        protected Item item;

        @Override  public int getItemResId() {return resId; }

        protected ItemVH(LayoutInflater inflater) {
            super();
            view = inflater.inflate(resId, null);
            view.setId(super.getId());
            itemTV = (TextView)view.findViewById(R.id.item_text);
        }
        // this is the position of the viewholder in the layout list. 
        // In the listadapter getView method if the list position and
        // the viewholder position differ, the viewholder should be
        // updated to correspond to the ViewHolderDelegate corresponding
        // with the listview object for the position.
        protected int position;
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

    // resid is used to match objects with viewholders.  If an delegate and a
    // viewholder share the same resource id, the delegate can update the
    // viewsholder.
    @Override public int getItemResId() { return resId; }

    // String to display in item text view.
    private String itemText;
    // constructor
    public Item(String itemText) {
        super(resId);
        this.itemText = itemText;
    }
    // viewholder creation and update happen here.  If the viewholder passed
    // in is  null or of a different type, create a new one.  Otherwise, just
    // configure this viewholder to reflect the object.
    @Override
    protected View setupVH(LayoutInflater inflater
            , ViewHolderDelegate.ViewHolder vh, int position) {
        ItemVH viewHolder;
        if (vh == null || vh.getItemResId() != getItemResId())
            viewHolder = new ItemVH(inflater);
        else
            viewHolder =  (ItemVH) vh;
        viewHolder.position = position;
        viewHolder.itemTV.setText(itemText);
        viewHolder.item = this;
        viewHolder.view.setTag(viewHolder);
        return viewHolder.view;
    }

    @Override
    public String toString() {
        return String.format("%s:text=%s:resId=0x%08x", TAG , itemText, resId);
    }
}
