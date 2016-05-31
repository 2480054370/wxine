package com.wxine.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ImgActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    //item 显示所需
    private String[] title = {"2013-10-24 星期四", "2013-10-4 星期san", "2013-10-4 星期二", "2013-10-14 星期一", "2013-10-24 星期四", "2013-10-4 星期san", "2013-10-4 星期二", "2013-10-14 星期一"};
    private ArrayList<String> mTitle = new ArrayList<>();
    ImgAdapter mRecyclerViewAdapter;
    private ArrayList<ArrayList<HashMap<String, Object>>> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img);
        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("相册");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ImgActivity.this);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        Collections.addAll(mTitle, title);
        initData();
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mRecyclerViewAdapter = new ImgAdapter(this, mTitle, mArrayList));

    }

    private void initData() {
        mArrayList = new ArrayList<ArrayList<HashMap<String, Object>>>();
        HashMap<String, Object> hashMap = null;
        ArrayList<HashMap<String, Object>> arrayListForEveryGridView;
        for (int i = 0; i < title.length+1; i++) {
            arrayListForEveryGridView = new ArrayList<HashMap<String, Object>>();
            for (int j = 0; j < 10; j++) {
                hashMap = new HashMap<String, Object>();
                hashMap.put("content", "i=" + i + " ,j=" + j);
                arrayListForEveryGridView.add(hashMap);
            }
            mArrayList.add(arrayListForEveryGridView);
        }
    }
}
