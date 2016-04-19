package com.wxine.android;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bumblebee on 2016/3/31.
 */
public class FindFragment extends Fragment {
    private FindAdapter mAdapter;
    private List<Integer> mDataset = Arrays.asList(R.drawable.tou, R.drawable.tou, R.drawable.tou,R.drawable.tou, R.drawable.tou, R.drawable.tou,R.drawable.tou, R.drawable.tou, R.drawable.tou,R.drawable.tou);
    private String mItemData = "Lorem Ipsum is simply dummy text of the printing and ";
    private String mDatas = "typesetting industry Lorem Ipsum has been the industry's standard dummy ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(
                R.id.fragment_list_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        String[] listItems = mItemData.split(" ");
        String[] listcontent=mDatas.split(" ");

        List<String> list = new ArrayList<String>();
        List<String> content=new ArrayList<String>();
        Collections.addAll(list, listItems);
        Collections.addAll(content,listcontent);
        mAdapter = new FindAdapter(mDataset,list,content);
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}
