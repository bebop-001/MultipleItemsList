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
public class Separator extends ViewHolderDelegate {
    private static final String TAG = "Separator";
    private static final int resId = R.layout.separator;

    public class SeparatorVH extends ViewHolder {
        private static final String TAG = "SeparatorVH";
        // view elements for viewholder.
        protected View view;
        protected TextView separatorTV;
        protected Separator separator;
        // constructor.  create the view and locate (find) any view elements.
        protected SeparatorVH(LayoutInflater inflater) {
            super();
            view = inflater.inflate(resId, null);
            view.setId(super.getId());
            separatorTV = (TextView)view.findViewById(R.id.separator_text);
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
        // resid is used to match objects with viewholders.  If an delegate
        // and a viewholder share the same resource id, the delegate can
        // update the viewsholder.
        @Override
        public int getItemResId() { return resId; }
        // debug toString method.
        @Override
        public String toString() {
            return String.format("%s:position=%d:text=\"%s\":resId=0x%08x",
                    TAG, position, separatorTV.getText().toString(), resId);
        }
    }

    // String to display in item text view.
    private String separatorText;
    // constructor
    public Separator(String separatorText) {
        super(resId);
        this.separatorText = separatorText;
    }

    // resid is used to match objects with viewholders.  If an delegate and a
    // viewholder share the same resource id, the delegate can update the
    // viewsholder.
    @Override public int getItemResId() { return resId; }

    // viewholder creation and update happen here.  If the viewholder
    // passed in is null or of a different type, create a new one.
    // Otherwise, just configure this viewholder to reflect the object.
    @Override
    protected View setupVH(LayoutInflater inflater, ViewHolder vh, int position) {
        SeparatorVH viewHolder;
        if (vh == null || getItemResId() != vh.getItemResId())
            viewHolder = new SeparatorVH(inflater);
        else
            viewHolder = (SeparatorVH) vh;
        viewHolder.position = position;
        viewHolder.separatorTV.setText(separatorText);
        viewHolder.separator = this;
        viewHolder.view.setTag(viewHolder);
        return viewHolder.view;
    }

    @Override
    public String toString() {
        return String.format("%s:text=%s:resId=0x%08x"
                , TAG , separatorText, resId);
    }

}
