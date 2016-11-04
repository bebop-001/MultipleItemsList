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

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private MultiItemListAdapter mAdapter;
    private ArrayList<ViewHolderDelegate> items
            = new ArrayList<ViewHolderDelegate>();

    // create a bunch of view objects, put them in an array list and
    // instantiate a new list adapter.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView)findViewById(R.id.main_layout);
        int sep = 1, item = 1, button = 1;
        for (int i = 0; i < 200; i++) {
            items.add((i % 4 == 0)
                    ? new Separator("separator " + sep++)
                    : new Item("item " + item++));
            if (i % 5 == 0) {
                items.add(new ButtonItem("push me " + button++ + " ;)"));
                if (i % 2 == 0)
                    items.add(new ButtonItem("push me " + button++ + " ;)"));
            }
        }
        mAdapter = new MultiItemListAdapter(this, items);
        list.setAdapter(mAdapter);
    }
}
