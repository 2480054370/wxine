package com.wxine.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
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
    private PopupWindow popupwindow;
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
        String[] listcontent=time.split(" ");

        List<String> list = new ArrayList<String>();
        List<String> content=new ArrayList<String>();
        Collections.addAll(list, listItems);
        Collections.addAll(content,listcontent);
        mAdapter = new TimelineAdapter(image,list,content);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new TimelineAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Info data) {

            }

            public void onKQClick(View view,Info da){
                switch (view.getId()) {
                    case R.id.btn1:
                        Button btn1 = (Button)findViewById(R.id.btn1);

                        if (popupwindow != null&&popupwindow.isShowing()) {
                            btn1.setBackgroundColor(0xffE8E8E8);
                            popupwindow.dismiss();

                            return;
                        } else {
                            initmPopupWindowView();
                            btn1.setBackgroundColor(0xff979797);
                            popupwindow.showAtLocation(view, 0, 84, 497);
                            //popupwindow.showAsDropDown(btn1);
                        }
                        break;
                    default:
                        break;
                }


                }

            public void onQJClick(View view,Info da){
                switch (view.getId()) {
                    case R.id.btn2:
                        Button btn2 = (Button)findViewById(R.id.btn2);
                        if (popupwindow != null&&popupwindow.isShowing()) {
                            btn2.setBackgroundColor(0xffE8E8E8);
                            popupwindow.dismiss();

                            return;
                        } else {
                            initmPopupWindowView2();
                            btn2.setBackgroundColor(0xff979797);
                            popupwindow.showAtLocation(view, 0, 84, 497);
                            //popupwindow.showAsDropDown(btn1);
                        }
                        break;
                    default:
                        break;
                }


            }

            public void onPJClick(View view,Info da){
                switch (view.getId()) {
                    case R.id.btn3:
                        Button btn3 = (Button)findViewById(R.id.btn3);

                        if (popupwindow != null&&popupwindow.isShowing()) {
                            btn3.setBackgroundColor(0xffE8E8E8);
                            popupwindow.dismiss();

                            return;
                        } else {
                            initmPopupWindowView3();
                            btn3.setBackgroundColor(0xff979797);
                            popupwindow.showAtLocation(view, 0, 84, 497);
                            //popupwindow.showAsDropDown(btn1);
                        }
                        break;
                    default:
                        break;
                }


            }
        });


    }
    public void initmPopupWindowView() {

        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.popview_item,
                null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        popupwindow = new PopupWindow(customView, 595, 410);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        //popupwindow.setAnimationStyle(R.style.AnimationFade);


    }

    public void initmPopupWindowView2() {

        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.popview_qinjia,
                null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        popupwindow = new PopupWindow(customView, 595, 410);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        //popupwindow.setAnimationStyle(R.style.AnimationFade);

    }

    public void initmPopupWindowView3() {

        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.popview_pingjia,
                null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        popupwindow = new PopupWindow(customView, 595, 410);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        //popupwindow.setAnimationStyle(R.style.AnimationFade);

    }
}
