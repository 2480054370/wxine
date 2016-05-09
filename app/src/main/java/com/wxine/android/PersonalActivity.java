package com.wxine.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wxine.android.model.Info;

import java.util.ArrayList;

/**
 * Created by NM on 2016/4/28.
 */
public class PersonalActivity extends Activity {
    private PersonalAdapter mAdapter;
    private ArrayList<Info> list = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_personal_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.infos_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PersonalActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new PersonalAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(mAdapter);
    }
}
