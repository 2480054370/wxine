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
                showPJDig();

            }

            @Override
            public void onQDClick(View view, Info da, int position) {
                Intent intent = new Intent(EventActivity.this, SingActivity.class);
                startActivity(intent);
            }
        });

    }
    public void showQJDig() {
        LayoutInflater inflaterDl = LayoutInflater.from(this);
        LinearLayout layout = (LinearLayout) inflaterDl.inflate(R.layout.event_qj, null);

        //对话框
        final Dialog dialog = new AlertDialog.Builder(EventActivity.this, R.style.Pub_Dialog).create();
        dialog.show();
        dialog.getWindow().setContentView(layout);

        WindowManager.LayoutParams mt = getWindow().getAttributes();
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams dg = dialogWindow.getAttributes();
        dg.width = 600;
        dg.height = 350;

        dialogWindow.setAttributes(dg);
        dialog.show();
        //取消按钮
        Button btnCancel = (Button) layout.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });

        ImageView imageView = (ImageView)layout.findViewById(R.id.im_qj);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                        "head.jpg")));
                startActivityForResult(intent2, 2);//采用ForResult打开
            }
        });

    }


    public void showPJDig() {
        LayoutInflater inflaterDl = LayoutInflater.from(this);
        RelativeLayout layout = (RelativeLayout) inflaterDl.inflate(R.layout.event_pingjia,null);

        //对话框
        final Dialog dialog = new AlertDialog.Builder(EventActivity.this, R.style.Pub_Dialog).create();
        dialog.show();
        dialog.getWindow().setContentView(layout);

        WindowManager.LayoutParams mt = getWindow().getAttributes();
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams dg = dialogWindow.getAttributes();
        dg.width = 600;
        dg.height = 900;

        dialogWindow.setAttributes(dg);
        dialog.show();
        //取消按钮
        Button btnCancel = (Button) layout.findViewById(R.id.cancel1);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });

        ImageView imageView = (ImageView)layout.findViewById(R.id.im_pj);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                        "head.jpg")));
                startActivityForResult(intent2, 2);//采用ForResult打开
            }
        });




    }


    public void showKQDig() {
        LayoutInflater inflaterDl = LayoutInflater.from(this);
        final RelativeLayout layout = (RelativeLayout) inflaterDl.inflate(R.layout.event_kq, null);

        //对话框
        final Dialog dialog = new AlertDialog.Builder(EventActivity.this, R.style.Pub_Dialog).create();
        dialog.show();
        dialog.getWindow().setContentView(layout);

        WindowManager.LayoutParams mt = getWindow().getAttributes();
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams dg = dialogWindow.getAttributes();
       // dg.width =(int)Math.ceil(mt.width*3.8);
        //dg.height=(int)Math.ceil(mt.height*1);
        dg.width=600;
        dg.height=800;

        dialogWindow.setAttributes(dg);
        dialog.show();
        final Button btn4 = (Button)layout.findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            RelativeLayout qb = (RelativeLayout) layout.findViewById(R.id.qb);
            RelativeLayout cd = (RelativeLayout) layout.findViewById(R.id.cd);
            RelativeLayout wcj = (RelativeLayout) layout.findViewById(R.id.wcj);
            TextView kqtext = (TextView) layout.findViewById(R.id.kqtext);
            final Button btn2 = (Button) layout.findViewById(R.id.btn2);
            final Button btn1 = (Button) layout.findViewById(R.id.btn1);

            @Override
            public void onClick(View v) {
                // i=0,it is visible;i=4,it is invisible;i=0,it is gone;

                int i = 0;
                i = qb.getVisibility();
                if (i == 8) {
                    kqtext.setVisibility(View.GONE);
                    cd.setVisibility(View.GONE);
                    wcj.setVisibility(View.GONE);
                    qb.setVisibility(View.VISIBLE);
                    btn4.setBackgroundColor(0xff0F9D58);
                    btn1.setBackgroundColor(0xff33AC71);
                    btn2.setBackgroundColor(0xff33AC71);

                } else {
                    qb.setVisibility(View.GONE);
                    btn4.setBackgroundColor(0xff33AC71);

                }
            }
        });
        //取消按钮
        Button btnCancel = (Button) layout.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });

        Button btnCancel1 = (Button) layout.findViewById(R.id.cancel1);
        btnCancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });
        Button btnCance2 = (Button) layout.findViewById(R.id.cancel2);
        btnCance2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });
        final Button btn1 = (Button)layout.findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            RelativeLayout cd =(RelativeLayout)layout.findViewById(R.id.cd);
            RelativeLayout qb =(RelativeLayout)layout.findViewById(R.id.qb);
            RelativeLayout wcj =(RelativeLayout)layout.findViewById(R.id.wcj);
            final Button btn4 = (Button)layout.findViewById(R.id.btn4);
            final Button btn2 = (Button)layout.findViewById(R.id.btn2);
            TextView kqtext = (TextView)layout.findViewById(R.id.kqtext);
            @Override
            public void onClick(View v) {
                int i = 0;
                i = cd.getVisibility();
                if (i == 8) {
                    cd.setVisibility(View.VISIBLE);
                    qb.setVisibility(View.GONE);
                    wcj.setVisibility(View.GONE);
                    btn1.setBackgroundColor(0xff0F9D58);
                    btn4.setBackgroundColor(0xff33AC71);
                    btn2.setBackgroundColor(0xff33AC71);
                    kqtext.setVisibility(View.GONE);
                } else {
                    cd.setVisibility(View.GONE);
                    btn1.setBackgroundColor(0xff33AC71);

                }
            }
        });
        final Button btn2 = (Button)layout.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            RelativeLayout wcj =(RelativeLayout)layout.findViewById(R.id.wcj);
            RelativeLayout cd =(RelativeLayout)layout.findViewById(R.id.cd);
            RelativeLayout qb =(RelativeLayout)layout.findViewById(R.id.qb);
            TextView kqtext = (TextView)layout.findViewById(R.id.kqtext);
            @Override
            public void onClick(View v) {
                int i = 0;
                i = wcj.getVisibility();
                if (i == 8) {
                    wcj.setVisibility(View.VISIBLE);
                    cd.setVisibility(View.GONE);
                    qb.setVisibility(View.GONE);
                    btn2.setBackgroundColor(0xff0F9D58);
                    btn4.setBackgroundColor(0xff33AC71);
                    btn1.setBackgroundColor(0xff33AC71);
                    kqtext.setVisibility(View.GONE);
                } else {
                    wcj.setVisibility(View.GONE);
                    btn2.setBackgroundColor(0xff33AC71);

                }

            }
        });

    }
}

