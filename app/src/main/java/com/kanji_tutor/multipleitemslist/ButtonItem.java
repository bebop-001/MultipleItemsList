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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * child of ViewHolderDelegate.  viewholder creation and object customization
 * happen here.
 */
public class ButtonItem extends ViewHolderDelegate {
    private static final String TAG = "ButtonItem";
    private static final int resId = R.layout.button_item;

    public class ButtonItemVH extends ViewHolder {
        private static final String TAG = "ButtonItemVH";

        // view elements for viewholder.
        protected View view;
        protected Button buttonItem;
        protected ButtonItem item;

        @Override  public int getItemResId() {return resId; }

        protected ButtonItemVH(LayoutInflater inflater) {
            super();
            view = inflater.inflate(resId, null);
            final Context c = view.getContext();
            view.setId(super.getId());
            buttonItem = (Button)view.findViewById(R.id.press_me);
            buttonItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String buttonText = ((Button)v).getText().toString();
                    Toast.makeText(c
                            , buttonText
                            , Toast.LENGTH_LONG
                    ).show();
                }
            });
        }
        // this is the position of the viewholder in the layout list.  In the listadapter
        // getView method if the list position and the viewholder position differ, the
        // viewholder should be updated to correspond to the ViewHolderDelegate corresponding
        // with the listview object for the position.
        protected int position;
        @Override
        int getPosition() {
            return position;
        }
        @Override
        public String toString() {
            return String.format("%s:position=%d:text=\"%s\":resId=0x%08x",
                    TAG, position, buttonItem.getText().toString(), resId);
        }
    }

    // resid is used to match objects with viewholders.  If an delegate and a
    // viewholder share the same resource id, the delegate can update the viewsholder.
    @Override public int getItemResId() { return resId; }

    // String to display in item text view.
    private String buttonText;
    // constructor
    public ButtonItem(String buttonText) {
        super(resId);
        this.buttonText = buttonText;
    }
    // viewholder creation and update happen here.  If the viewholder passed in is
    // null or of a different type, create a new one.  Otherwise, just configure
    // this viewholder to reflect the object.
    @Override
    protected View setupVH(LayoutInflater inflater, ViewHolder vh, int position) {
        ButtonItemVH viewHolder;
        if (vh == null || vh.getItemResId() != getItemResId())
            viewHolder = new ButtonItemVH(inflater);
        else
            viewHolder =  (ButtonItemVH) vh;
        viewHolder.position = position;
        viewHolder.buttonItem.setText(buttonText);
        viewHolder.item = this;
        viewHolder.view.setTag(viewHolder);
        return viewHolder.view;
    }

    @Override
    public String toString() {
        return String.format("%s:text=%s:resId=0x%08x", TAG , buttonText, resId);
    }

}
