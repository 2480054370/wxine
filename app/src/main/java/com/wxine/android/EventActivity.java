package com.wxine.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wxine.android.model.Event;
import com.wxine.android.model.Info;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zz on 2016/4/5.
 */
public class EventActivity extends AppCompatActivity {
    private TimelineAdapter mAdapter;
    private List<Integer> image = Arrays.asList(R.drawable.tou, R.drawable.tou, R.drawable.tou, R.drawable.tou, R.drawable.tou, R.drawable.tou, R.drawable.tou, R.drawable.tou, R.drawable.tou, R.drawable.tou);
    private String username = "Lorem Ipsum is simply dummy text of the printing and ";
    private String time = "今天10:00 今天10:00 今天10:00 今天10:00 今天10:00 今天10:00 今天10:00 今天10:00 今天10:00 今天10:00 ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.event_toolbar);
        toolbar.setTitle("活动");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.event_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventActivity.this, EventCreate.class);
                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(EventActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        String[] listItems = username.split(" ");
        String[] listcontent = time.split(" ");

        List<String> list = new ArrayList<String>();
        List<String> content = new ArrayList<String>();
        Collections.addAll(list, listItems);
        Collections.addAll(content, listcontent);
        mAdapter = new TimelineAdapter(image, list, content);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new TimelineAdapter.OnRecyclerViewItemClickListener() {


            @Override
            public void onItemClick(View view, Info data) {
                Log.d("------------", "sss");
            }

            @Override
            public void onKQClick(View view, Info da, int position) {
                Toast.makeText(EventActivity.this,"aaa"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onQJClick(View view, Info da, int position) {
                RelativeLayout attendance = (RelativeLayout) view.findViewById(R.id.attendance);
                RelativeLayout evaluate = (RelativeLayout) view.findViewById(R.id.evaluate);
                // i=0,it is visible;i=4,it is invisible;i=0,it is gone;
                int i = 0;
                i = attendance.getVisibility();
                if (i == 8) {
                    attendance.setVisibility(View.VISIBLE);
                    evaluate.setVisibility(View.GONE);
                } else {
                    attendance.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPJClick(View view, Info da, int position) {
                RelativeLayout evaluate = (RelativeLayout) view.findViewById(R.id.evaluate);
                RelativeLayout attendance = (RelativeLayout) view.findViewById(R.id.attendance);
                // i=0,it is visible;i=4,it is invisible;i=0,it is gone;
                int i = 0;
                i = evaluate.getVisibility();
                if (i == 8) {
                    evaluate.setVisibility(View.VISIBLE);
                    attendance.setVisibility(View.GONE);
                } else {
                    evaluate.setVisibility(View.GONE);
                }
            }
        });

    }

}
