package com.wxine.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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
                CardView cardview_qj = (CardView) view.findViewById(R.id.cardview_qj);
                CardView cardview_pj = (CardView) view.findViewById(R.id.cardview_pj);
                Button btn2 = (Button)view.findViewById(R.id.btn2);
                Button btn3 = (Button)view.findViewById(R.id.btn3);
                // i=0,it is visible;i=4,it is invisible;i=0,it is gone;

                int i = 0;
                i = cardview_qj.getVisibility();
                if (i == 8) {
                    cardview_qj.setVisibility(View.VISIBLE);
                    btn2.setBackgroundColor(0xff979797);
                    btn3.setBackgroundColor(0xffE8E8E8);
                    cardview_pj.setVisibility(View.GONE);
                } else {
                    btn2.setBackgroundColor(0xffE8E8E8);
                    cardview_qj.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPJClick(View view, Info da, int position) {
                CardView cardview_pj = (CardView) view.findViewById(R.id.cardview_pj);
                CardView cardview_qj = (CardView) view.findViewById(R.id.cardview_qj);
                Button btn3 = (Button)view.findViewById(R.id.btn3);
                Button btn2 = (Button)view.findViewById(R.id.btn2);

                int i = 0;
                i = cardview_pj.getVisibility();
                if (i == 8) {
                    cardview_pj.setVisibility(View.VISIBLE);
                    btn3.setBackgroundColor(0xff979797);
                    btn2.setBackgroundColor(0xffE8E8E8);
                    cardview_qj.setVisibility(View.GONE);
                } else {
                    cardview_pj.setVisibility(View.GONE);
                    btn3.setBackgroundColor(0xffE8E8E8);
                }
            }
        });

    }
}
