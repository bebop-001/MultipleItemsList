package com.kanji_tutor.multipleitemslist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private MultiItemListAdapter<ViewHolderDelegate> mAdapter;

    private ArrayList<ViewHolderDelegate> items = new ArrayList<ViewHolderDelegate>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView)findViewById(R.id.main_layout);
        int sep = 1, item = 1;
        for (int i = 0; i < 50; i++) {
            items.add((i % 4 == 0)
                    ? new Separator("separator " + sep++)
                    : new Item("item " + item++));
        }

        mAdapter = new MultiItemListAdapter<ViewHolderDelegate>(this, items);
        list.setAdapter(mAdapter);
    }
}
