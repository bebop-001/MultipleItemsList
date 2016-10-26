package com.kanji_tutor.multipleitemslist;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sjs on 10/23/16.
 */

public class Separator extends ViewHolderDelegate {
    private static final String TAG = "Separator";
    private static final int resId = R.layout.separator;

    private static VH_Stack stack;

    // viewHolderholder items.
    private String separatorText;
    TextView tv;
/*
    class VH implements ViewHolderDelegate.Viewholder {
        View viewHolder;
        TextView tv;
        String name;
        int ID;

        @Override
        public View getViewholder() {
            return viewHolder;
        }

        @Override
        public View updateVH() {
            tv.setText(separatorText);
            return viewHolder;
        }

        @Override
        public View newVH(LayoutInflater inflater, String name, int ID) {
            viewHolder = inflater.inflate(resId, null);
            viewHolder.setId(ID);
            this.ID = ID;
            tv = (TextView)viewHolder.findViewById(R.id.separator_text);
            return updateVH();
        }
    }
*/
    public Separator(String separatorText) {
        super(resId, TAG);
        if (stack == null)
            stack = new VH_Stack(TAG);
        setStack(stack, TAG);
        this.separatorText = separatorText;
    }

    @Override
    public View updateVH(View viewHolder) {
        tv = (TextView) viewHolder.findViewById(R.id.separator_text);
        String oldText = tv.getText().toString();
        tv.setText(separatorText);
        Log.e(TAG, "Separator:updateVH:viewID=" + viewHolder.getId()
            + ":position=" + getPosition()
            + ":text=\""  + oldText + "\"->\"" + separatorText + "\"");
        return viewHolder;
    }
}
