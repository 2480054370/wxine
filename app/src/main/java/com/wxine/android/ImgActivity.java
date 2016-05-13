package com.wxine.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Bumblebee on 2016/5/9.
 */
public class ImgActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    //item 显示所需
    private String[] title = {"默认相册"};
    private ArrayList<String> mTitle = new ArrayList<>();
    private ImgAdapter mRecyclerViewAdapter;
    public Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
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
        GridLayoutManager layoutManager = new GridLayoutManager(ImgActivity.this, 2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        Collections.addAll(mTitle, title);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mRecyclerViewAdapter = new ImgAdapter(this, mTitle));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intent == null) {
                    intent = new Intent(ImgActivity.this, ImgNewActivity.class);
                    startActivityForResult(intent, 0);
                } else {
                    startActivityForResult(intent, 0);
                }
            }
        });
        mRecyclerViewAdapter.setOnItemClickListener(new ImgAdapter.OnRecyclerViewItemClickListener() {

            @Override
            public void onItemClick(View view,String title) {
                Intent intent = new Intent(ImgActivity.this, PhotosActivity.class);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        //    super.onBackPressed();
        MainActivity a = new MainActivity();
        Intent back = new Intent(ImgActivity.this,a.getClass());
        startActivity(back);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                String message = data.getStringExtra("text");
                if (message != null) {
                    mTitle.add(message);
                    mRecyclerViewAdapter.notifyItemInserted(0);
                    mRecyclerView.scrollToPosition(0);
                }
                break;
            default:
                break;
        }
    }
}
