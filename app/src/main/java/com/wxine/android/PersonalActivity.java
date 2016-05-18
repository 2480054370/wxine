package com.wxine.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wxine.android.model.Info;

import java.util.ArrayList;

/**
 * Created by NM on 2016/4/28.
 */
public class PersonalActivity extends AppCompatActivity {
    private PersonalAdapter mAdapter;
    private ArrayList<Info> list = null;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_personal_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.infos_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PersonalActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new PersonalAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
