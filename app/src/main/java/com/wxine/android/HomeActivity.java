package com.wxine.android;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wxine.android.model.Info;
import com.wxine.android.model.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.home_main)
public class HomeActivity extends AppCompatActivity {
    @ViewById
    Toolbar Home_toolbar;

    private HomeAdapter mAdapter;

    @ViewById
    SwipeRefreshLayout home_refresh_widget;

    private LinearLayoutManager mLayoutManager;

    @ViewById
    RecyclerView home_recycler_view;

    ArrayList<Info> list = new ArrayList<Info>();

    @AfterViews
    void init() {
        Home_toolbar.setTitle("");
        setSupportActionBar(Home_toolbar);

        datainit();
        home_recycler_view.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        home_recycler_view.setLayoutManager(mLayoutManager);
        mAdapter = new HomeAdapter(this.getApplicationContext(), list);
        home_recycler_view.setAdapter(mAdapter);
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
