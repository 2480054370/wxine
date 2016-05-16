package com.wxine.android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wxine.android.model.Info;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
                showKQDig();
            }

            @Override
            public void onQJClick(View view, Info da, int position) {
                showQJDig();
            }


            @Override
            public void onPJClick(View view, Info da, int position) {
                Intent intent = new Intent(EventActivity.this, Event_pj.class);
                startActivity(intent);
            }

            @Override
            public void onQDClick(View view, Info da, int position) {
                showQDDig();
            }
        });

    }
    public void showQJDig() {
//        LayoutInflater inflaterDl = LayoutInflater.from(this);
//        LinearLayout layout = (LinearLayout) inflaterDl.inflate(R.layout.event_qj, null);
//
//        //对话框
//        final Dialog dialog = new AlertDialog.Builder(EventActivity.this, R.style.Pub_Dialog).create();
//        dialog.show();
//        dialog.getWindow().setContentView(layout);
//
//        Display mt = getWindowManager().getDefaultDisplay();
//        Window dialogWindow = dialog.getWindow();
//        WindowManager.LayoutParams dg = dialogWindow.getAttributes();
//        dg.width = (int) Math.ceil(mt.getWidth() * 0.8);
//        dg.height = (int) Math.ceil(mt.getHeight() * 0.35);
//
//        dialogWindow.setAttributes(dg);
//        dialog.show();
//        //取消按钮
//        Button btnCancel = (Button) layout.findViewById(R.id.cancel);
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.hide();
//            }
//        });
//
//        ImageView imageView = (ImageView)layout.findViewById(R.id.im_qj);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
//                        "head.jpg")));
//                startActivityForResult(intent2, 2);//采用ForResult打开
//            }
//        });
        Intent intent = new Intent(EventActivity.this, Event_qj.class);
        startActivity(intent);


    }






    public void showQDDig() {
        Intent intent = new Intent(EventActivity.this, Event_qd.class);
        startActivity(intent);
    }


    public void showKQDig() {
        Intent intent = new Intent(EventActivity.this, Event_kq.class);
        startActivity(intent);
    }
}

