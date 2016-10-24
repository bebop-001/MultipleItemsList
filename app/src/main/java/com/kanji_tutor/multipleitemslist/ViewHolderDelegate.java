package com.kanji_tutor.multipleitemslist;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by sjs on 10/23/16.
 */

public interface ViewHolderDelegate {
    public int getPosition();
    public long getItemType();

    public View newVH(LayoutInflater inflater, int position);
    public View getVH();
    public void freeVH();
    public View updateVH(View viewHolder);
    public View moveFromVH(ViewHolderDelegate from, int position);
}
