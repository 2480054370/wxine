package com.wxine.android;

import android.app.Activity;
import android.net.Uri;
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
public class NotificationReadFragment extends Fragment{
    private OnFragmentInteractionListener mListener;
    private RecyclerView mReclcerView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private RelativeLayout mRelativeLayout;
    private Fragment fragment;

    public NotificationReadFragment(){

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
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_down,R.anim.fragment_slide_left_exit);      //自定义动画

               //transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);                  //系统自带的动画
            //    transaction.hide(NotifacationReadFragment.this).show(fragment).commit();
                transaction.addToBackStack(null);
                if (!fragment.isAdded()) {
                    // 隐藏当前的fragment，add下一个到Activity中
                    transaction.hide(NotificationReadFragment.this).add(R.id.content, fragment).commit();
                } else {
                    // 隐藏当前的fragment，显示下一个
                    transaction.hide(NotificationReadFragment.this).show(fragment).commit();
                }
  //              transaction.commit();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        MainActivity a = new MainActivity();
        fragment = a.getNotiFra();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
