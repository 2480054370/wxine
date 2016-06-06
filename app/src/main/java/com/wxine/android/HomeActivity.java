package com.wxine.android;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.wxine.android.model.Comment;
import com.wxine.android.model.Info;
import com.wxine.android.model.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@EActivity(R.layout.home_main)
public class HomeActivity extends AppCompatActivity {
    @ViewById
    Toolbar Home_toolbar;
    private HomeAdapter mAdapter;

    @ViewById
    SwipeRefreshLayout home_refresh_widget;

    private LinearLayoutManager mLayoutManager;

    @ViewById
    RecyclerView home_main_recyclerView;

    ArrayList<Info> list = new ArrayList<Info>();

    @ViewById
    ImageView PersonalAgreeImg;

    @ViewById
    ImageView PersonalShareImg;

    @ViewById
    ImageView PersonalComment;


    @AfterViews
    void init() {
        Home_toolbar.setTitle("");
        setSupportActionBar(Home_toolbar);

        View a = LayoutInflater.from(this).inflate(R.layout.home_item, null);
        RecyclerView recyclerView = (RecyclerView) a.findViewById(R.id.home_item_recycler);

        datainit();
        home_main_recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        home_main_recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new HomeAdapter(this.getApplicationContext(), list);
        home_main_recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new HomeAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Log.e("--------", "skajdksa");
            }

            @Override
            public void onKQClick(View view, int position) {
                Intent intent = new Intent(HomeActivity.this, PersonalDataActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setHasFixedSize(true);
        FullyLinearLayoutManager mLayoutManager = new FullyLinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        final CommentsAdapter mAdapter = new CommentsAdapter(this, new ArrayList<Comment>(list.get(1).getComments()));
        //Log.v("==height======", String.valueOf(0));
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void datainit() {
        User user = new User();
        user.setName("aaa");
        user.setImage("http://img.teapic.com/thumbs/201305/28/101143gftcfpzwvukwqjsl.jpg.middle.jpg");
        user.setAddress("asgeq");
        Info info = new Info();
        info.setUser(user);
        info.setImage("http://icon.nipic.com/BannerPic/20160224/home/20160224113102.jpg");
        info.setAddress("aaa");
        info.setBrief("qiuehgqlehg");
        info.setCmcount(12);
        info.setContent("this is content");
        info.setTitle("this is title");
        info.setName("aaa");
        info.setCleancontent("this is c content1");

        Comment comment1 = new Comment();
        comment1.setContent("this is a comment1");
        comment1.setUser(user);
        comment1.setScore(60);
        comment1.setCtime(new java.sql.Timestamp(2014121123));
        Set<Comment> set1 = new HashSet<>();
        set1.add(comment1);

        Comment comment2 = new Comment();
        comment2.setContent("this is a comment1");
        comment2.setUser(user);
        comment2.setScore(60);
        comment2.setCtime(new java.sql.Timestamp(2014121123));
        set1.add(comment2);
        info.setComments(set1);

        Comment comment3 = new Comment();
        comment3.setContent("this is a comment1");
        comment3.setUser(user);
        comment3.setScore(60);
        comment3.setCtime(new java.sql.Timestamp(2014121123));
        set1.add(comment3);
        info.setComments(set1);

        User user2 = new User();
        user2.setName("bbb");
        user2.setImage("http://www.qqpk.cn/Article/UploadFiles/201112/20111228132051137.jpg");
        user2.setAddress("asgeq");
        Info info2 = new Info();
        info2.setUser(user2);
        info2.setImage("http://icon.nipic.com/BannerPic/20160224/home/20160224113102.jpg");
        info2.setAddress("aaa");
        info2.setBrief("this is brief");
        info2.setCmcount(12);
        info2.setTitle("this is title");
        info2.setName("bbb");
        info2.setContent("this is content2");
        info2.setCleancontent("this is c content");
        info2.setComments(set1);

        list.add(info);
        list.add(info2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            return true;
        } else if (id == R.id.action_copylink) {
            return true;
        } else if (id == R.id.action_site) {
            Intent i = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(i);
        } else if (id == R.id.action_opinion) {
            return true;
        } else if (id == R.id.action_help) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
