package com.wxine.android;

import android.app.Notification;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.wxine.android.model.Info;

import java.util.ArrayList;

/**
 * Created by NM on 2016/4/7.
 */
public class NotifacationReadFragment extends Fragment {
    private RecyclerView mReclcerView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private RelativeLayout mRelativeLayout;

    public NotifacationReadFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_notification_read, container,false);
        mReclcerView = (RecyclerView)view.findViewById(R.id.notification);
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mReclcerView.setLayoutManager(mLayoutManager);
        ArrayList<Info> Test = new ArrayList<Info>();
        mAdapter = new NotificationAdapter(this.getContext(),Test);
        mReclcerView.setAdapter(mAdapter);
        view.findViewById(R.id.notification_read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotifacationFragment fragment = new NotifacationFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_down,R.anim.fragment_slide_left_exit);      //自定义动画

               //transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);                  //系统自带的动画
            //    transaction.hide(NotifacationReadFragment.this).show(fragment).commit();
                transaction.addToBackStack(null);
                if (!fragment.isAdded()) {
                    // 隐藏当前的fragment，add下一个到Activity中
                    transaction.hide(NotifacationReadFragment.this).add(R.id.content, fragment).commit();
                } else {
                    // 隐藏当前的fragment，显示下一个
                    transaction.hide(NotifacationReadFragment.this).show(fragment).commit();
                }
  //              transaction.commit();
            }
        });
        return view;
    }
}
