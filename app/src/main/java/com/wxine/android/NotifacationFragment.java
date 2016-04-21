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
public class NotifacationFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private RecyclerView mReclcerView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private RelativeLayout mRelativeLayout;

    public NotifacationFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_notification, container,false);
        mReclcerView = (RecyclerView)view.findViewById(R.id.notification);
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mReclcerView.setLayoutManager(mLayoutManager);
        ArrayList<Info> Test = new ArrayList<Info>();
        mAdapter = new NotificationAdapter(this.getContext(),Test);
        mReclcerView.setAdapter(mAdapter);
        mRelativeLayout  = (RelativeLayout)view.findViewById(R.id.notification_read);
        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotifacationReadFragment fragment = new NotifacationReadFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_up,R.anim.fragment_slide_left_exit);
           //     transaction.hide(NotifacationFragment.this).show(fragment).commit();
                transaction.addToBackStack(null);
                if (!fragment.isAdded()) {
                    // 隐藏当前的fragment，add下一个到Activity中
                    transaction.hide(NotifacationFragment.this).add(R.id.content, fragment).commit();
                } else {
                    // 隐藏当前的fragment，显示下一个
                    transaction.hide(NotifacationFragment.this).show(fragment).commit();
                }
                //transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                transaction.commit();
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
