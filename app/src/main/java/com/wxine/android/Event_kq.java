package com.wxine.android;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

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

    private boolean isShowDelete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_kq);
        Toolbar toolbar = (Toolbar) findViewById(R.id.kq_toolbar);
        toolbar.setTitle("考勤");
        setSupportActionBar(toolbar);
        setupViewPager();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        qbGridView = (GridView) findViewById(R.id.gridView_qb);
//        qbGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                if (isShowDelete) {
//                    isShowDelete = false;
//                } else {
//                    isShowDelete = true;
//                }
//                qbAdapter.setIsShowDelete(isShowDelete);
//                return true;
//
//            }
//        });
//        qbAdapter = new QBAdapter(Event_kq.this, new String[]{"abc", "dsaf", "redv", "gdsfs", "test", "ffff", "aaaa"}, new int[]{R.drawable.face, R.drawable.face, R.drawable.face, R.drawable.face, R.drawable.face, R.drawable.face, R.drawable.face});
//        qbGridView.setAdapter(qbAdapter);
//
//        cdGridView = (GridView) findViewById(R.id.gridView_cd);
//        cdGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                if (isShowDelete) {
//                    isShowDelete = false;
//                } else {
//                    isShowDelete = true;
//                }
//                cdAdapter.setIsShowDelete(isShowDelete);
//                return true;
//            }
//        });
//        cdAdapter = new CDAdapter(Event_kq.this, new String[]{"abc", "dsaf", "redv"}, new int[]{R.drawable.face, R.drawable.face, R.drawable.face});
//        cdGridView.setAdapter(cdAdapter);
//        mGridView = (GridView) findViewById(R.id.gridView_wcj);
//        mGridView.setOnItemLongClickListener(this);
//        mAdapter = new GridViewAdapter(Event_kq.this, new String[]{"abc", "dsaf"}, new int[]{R.drawable.face, R.drawable.face});
//        mGridView.setAdapter(mAdapter);
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

    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.event_kq_ViewPager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.event_kq_TabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new PersonalDataInformation_Tab(), "基本信息");
        adapter.addFrag(new PersonalDataRealname_Tab(), "实名认证");
        adapter.addFrag(new PersonalDataContact_Tab(), "联系信息");
        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}



