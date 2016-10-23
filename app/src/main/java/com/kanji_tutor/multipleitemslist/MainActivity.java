package com.kanji_tutor.multipleitemslist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private MultiItemListAdapter mAdapter;

    private ArrayList<String> strings = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView)findViewById(R.id.main_layout);
        int sep = 1, item = 1;
        for (int i = 0; i < 50; i++) {
            strings.add((i % 4 == 0) ? ("separator " + sep++) : ("item " + item++));
        }

        mAdapter = new MultiItemListAdapter(this, strings);
        list.setAdapter(mAdapter);
    }
}
