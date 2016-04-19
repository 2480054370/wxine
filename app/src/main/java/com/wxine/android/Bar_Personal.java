package com.wxine.android;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.wxine.android.model.Info;
import com.wxine.android.model.User;
import com.wxine.android.utils.InfoPage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class Bar_Personal extends AppCompatActivity {
    private Toolbar toolbar;
    private Button button;
    private InfosAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private LinearLayoutManager mLayoutManager;
    private boolean isLoading = false;
    private boolean isRefreshing = false;
    private int page = 0;
    ArrayList<Info> list = new ArrayList<Info>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_personal_main);
        button = (Button) findViewById(R.id.personal_button);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.info_refresh_widget);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.infos_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        datainit();
        mAdapter = new InfosAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new InfosAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Info data) {
                Log.v("点击====", data.getTitle() + "here");
                Intent i = new Intent(Bar_Personal.this, InfoActivity.class);
                ImageView iview = (ImageView) view.findViewById(R.id.iv_agree_img);
                String tag = iview.getTag().toString();
                Toast.makeText(Bar_Personal.this, "aaa" + iview.getTag(), Toast.LENGTH_SHORT).show();
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("info", data);
                i.putExtra("tag", tag);
                i.putExtras(mBundle);
                startActivity(i);
            }

            public void onShareClick(View view, int position) {

            }

            public void onCommentClick(View view, Info data) {

            }

            public void onAgreeClick(ImageView view, int position) {
                Toast.makeText(Bar_Personal.this, "aaa" + position, Toast.LENGTH_SHORT).show();
                //  view.setAlpha(0.0001f);
//                ImageView v = (ImageView) view.findViewById(R.id.iv_agree_img);
//                v.setImageResource(R.drawable.xreply);
                // view.setImageResource(R.drawable.favorite);
                if (view.getTag().toString().equals("no")) {
                    view.setImageResource(R.drawable.favorite);
                    view.setTag("yes");
                } else {
                    view.setImageResource(R.drawable.xfavorite);
                    view.setTag("no");
                }
            }
        });
        new LoadDataTask().execute();

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isRefreshing) {
                    Log.d("===", "ignore manually update!");
                } else {
                    new LoadDataTask().execute();
                }
            }
        });

        mRecyclerView.addOnScrollListener(new OnMyScrollListener() {
            @Override
            public void onBottom() {
                super.onBottom();
                // 到底部自动加载
                if (!isLoading) {
                    Log.d("", "loading old data");
                    new LoadDataTask("load").execute();
                    isLoading = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //解决RecyclerView和SwipeRefreshLayout共用存在的bug
                //((LinearLayoutManager) mLayoutManager).findFirstCompletelyVisibleItemPosition()
                mRefreshLayout.setEnabled(mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }
        });

    }

    private class LoadDataTask extends AsyncTask<String, Void, InfoPage> {
        private String actiontype = "refresh";//load

        public LoadDataTask() {
            page = 0;
        }

        public LoadDataTask(String actiontype) {
            if (StringUtils.equals(actiontype, "refresh"))
                page = 0;
            this.actiontype = actiontype;
        }

        @Override
        protected InfoPage doInBackground(String... params) {
            InfoPage pagesupport = null;
            RestTemplate restTemplate = new RestTemplate();
            FastJsonHttpMessageConverter c = new FastJsonHttpMessageConverter();
            c.setFeatures(SerializerFeature.UseISO8601DateFormat);
            restTemplate.getMessageConverters().add(c);

            return pagesupport;
        }

        @Override
        protected void onPostExecute(final InfoPage result) {
            if (null != result) {
                Log.v("---------", "download ok");
                if (StringUtils.equals(actiontype, "load"))
                    mAdapter.addItems(result.getItems());
                else
                    mAdapter.setList(result.getItems());
                //mAdapter.notifyDataSetChanged();
                page = result.getCurrentPage();
                isLoading = false;
                mRefreshLayout.setRefreshing(false);
            }
        }
    }


    public void datainit() {
        User user = new User();
        user.setName("abc");
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
        info.setName("abc");
        info.setCleancontent("this is c content1");

        User user2 = new User();
        user2.setName("aaa");
        user2.setImage("http://www.qqpk.cn/Article/UploadFiles/201112/20111228132051137.jpg");
        user2.setAddress("asgeq");
        Info info2 = new Info();
        info2.setUser(user2);
        info2.setImage("http://icon.nipic.com/BannerPic/20160224/home/20160224113102.jpg");
        info2.setAddress("aaa");
        info2.setBrief("this is brief");
        info2.setCmcount(12);
        info2.setTitle("this is title");
        info2.setName("abc");
        info2.setContent("this is content2");
        info2.setCleancontent("this is c content");
        list.add(info);
        list.add(info2);
        Log.d("InfosFragment", "Socket Type: " + info.getCleancontent() + "111");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal, menu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Bar_Personal.this, changheandActivity.class);
                startActivity(intent);
            }
        });

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish();
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
