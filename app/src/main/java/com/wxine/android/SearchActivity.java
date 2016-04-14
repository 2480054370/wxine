package com.wxine.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wxine.android.model.Info;

import java.util.ArrayList;

/**
 * Created by NM on 2016/4/5.
 */
public class SearchActivity extends Activity {
    private RecyclerView mReclcerView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        RecyclerView mReclcerView = (RecyclerView)findViewById(R.id.mReclcer);
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        mReclcerView.setLayoutManager(mLayoutManager);
        ArrayList<Info> Test = new ArrayList<Info>();
        mAdapter = new SearchAdapter(this.getApplicationContext(),Test);
        mReclcerView.setAdapter(mAdapter);
    }
}
