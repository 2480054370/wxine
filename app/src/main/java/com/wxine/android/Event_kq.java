package com.wxine.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by zz on 2016/4/5.
 */
public class Event_kq extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private GridView mGridView;
    private GridViewAdapter mAdapter;
    private CDAdapter cdAdapter;
    private GridView cdGridView;
    private GridView qbGridView;
    private QBAdapter qbAdapter;

    private boolean isShowDelete=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_kq);
        Toolbar toolbar = (Toolbar) findViewById(R.id.kq_toolbar);
        toolbar.setTitle("考勤");
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        qbGridView = (GridView) findViewById(R.id.gridView_qb);
        qbGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (isShowDelete) {
                    isShowDelete = false;

                } else {
                    isShowDelete = true;

                }
                qbAdapter.setIsShowDelete(isShowDelete);
                return true;

            }
        });
        qbAdapter=new QBAdapter(Event_kq.this, new String[]{"abc","dsaf","redv","gdsfs","test","ffff","aaaa"}, new int[]{R.drawable.face,R.drawable.face,R.drawable.face,R.drawable.face,R.drawable.face,R.drawable.face,R.drawable.face});
        qbGridView.setAdapter(qbAdapter);



        cdGridView = (GridView) findViewById(R.id.gridView_cd);
        cdGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (isShowDelete) {
                    isShowDelete = false;

                } else {
                    isShowDelete = true;
                }
                cdAdapter.setIsShowDelete(isShowDelete);
                return true;

            }
        });
        cdAdapter=new CDAdapter(Event_kq.this, new String[]{"abc","dsaf","redv"}, new int[]{R.drawable.face,R.drawable.face,R.drawable.face});
        cdGridView.setAdapter(cdAdapter);



        mGridView = (GridView) findViewById(R.id.gridView_wcj);
        mGridView.setOnItemLongClickListener(this);
        mAdapter=new GridViewAdapter(Event_kq.this, new String[]{"abc","dsaf"}, new int[]{R.drawable.face,R.drawable.face});
        mGridView.setAdapter(mAdapter);



        final Button btn4 = (Button)findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            LinearLayout qb = (LinearLayout) findViewById(R.id.qb);
            LinearLayout cd = (LinearLayout) findViewById(R.id.cd);
            LinearLayout wcj = (LinearLayout) findViewById(R.id.wcj);
            TextView kqtext = (TextView)findViewById(R.id.kqtext);
            final Button btn2 = (Button)findViewById(R.id.btn2);
            final Button btn1 = (Button)findViewById(R.id.btn1);

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

        Button btnCancel = (Button)findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button btnCancel1 = (Button) findViewById(R.id.cancel1);
        btnCancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Button btnCance2 = (Button) findViewById(R.id.cancel2);
        btnCance2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        final Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            LinearLayout cd = (LinearLayout) findViewById(R.id.cd);
            LinearLayout qb = (LinearLayout) findViewById(R.id.qb);
            LinearLayout wcj = (LinearLayout) findViewById(R.id.wcj);
            final Button btn4 = (Button)findViewById(R.id.btn4);
            final Button btn2 = (Button)findViewById(R.id.btn2);
            TextView kqtext = (TextView)findViewById(R.id.kqtext);

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


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long id) {
        if (isShowDelete) {
            isShowDelete = false;

        } else {
            isShowDelete = true;

        }
        mAdapter.setIsShowDelete(isShowDelete);
        return true;
    }
}



