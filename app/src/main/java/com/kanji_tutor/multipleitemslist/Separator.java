package com.kanji_tutor.multipleitemslist;


import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by sjs on 10/23/16.
 */

public class Separator extends ViewHolderDelegate {
    private static final String TAG = "Separator";
    private static final int resId = R.layout.separator;

    private String separatorText;

    public Separator(String separatorText) {
        super(resId, TAG);
        this.separatorText = separatorText;
    }

    @Override public int getItemResId() { return resId; }

    @Override
    protected View setupVH(LayoutInflater inflater, ViewHolder vh, int position) {
        SeparatorVH viewHolder = (SeparatorVH) vh;
        if (vh == null || getItemResId() != vh.getItemResId())
            viewHolder = new SeparatorVH(inflater);
        else
            viewHolder = (SeparatorVH) vh;
        viewHolder.position = position;
        viewHolder.separatorTV.setText(separatorText);
        viewHolder.separator = this;
        return viewHolder.view;
    }

    @Override
    public String toString() {
        return TAG + ":text=\"" + separatorText + "\"";
    }

}