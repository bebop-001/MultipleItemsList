package com.kanji_tutor.multipleitemslist;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sjs on 10/31/16.
 */

public class SeparatorVH extends ViewHolder {
    private static final String TAG = "SeparatorVH";
    private static final int resId = R.layout.separator;

    protected View view;
    protected TextView separatorTV;
    protected Separator separator;

    @Override protected int getItemResId() {return resId; }

    protected SeparatorVH(LayoutInflater inflater) {
        super(resId);
        view = inflater.inflate(resId, null);
        view.setId(super.getId());
        separatorTV = (TextView)view.findViewById(R.id.separator_text);
    }
    protected int position;
    @Override
    int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.format("%s:position=%d:text=\"%s\":resId=0x%08x",
                TAG, position, separatorTV.getText().toString(), resId);
    }

}
