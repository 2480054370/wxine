package com.wxine.android;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.wxine.android.model.Info;
import com.wxine.android.model.User;

import java.util.ArrayList;

public class CollSpage extends AppCompatActivity {
    private Toolbar toolbar;
    private CollSpageAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private LinearLayoutManager mLayoutManager;
    ArrayList<Info> list = new ArrayList<Info>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coll_s_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(CollSpage.this,
                R.array.pass_type, R.layout.layout_drop_title);
        adapter.setDropDownViewResource(R.layout.layout_drop_list);

        Spinner mNavigationSpinner = new Spinner(getSupportActionBar().getThemedContext());
        mNavigationSpinner.setAdapter(adapter);
        toolbar.addView(mNavigationSpinner);

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.info_refresh_widget);
        datainit();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.infos_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CollSpageAdapter(this.getApplicationContext(), list);
        mRecyclerView.setAdapter(mAdapter);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
            return true;
        } else if (id == R.id.action_opinion) {
            return true;
        } else if (id == R.id.action_help) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
