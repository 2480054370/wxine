package com.wxine.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by @vitovalov on 30/9/15.
 */
public class CourseFirstTab extends Fragment {

    private CourseFirstAdapter mAdapter;

    private String mItemData = "Lorem Ipsum is simply dummy text of the printing and ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_first_recyclerview, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(
                R.id.fragment_list_rv);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        String[] listItems = mItemData.split(" ");

        List<String> list = new ArrayList<String>();
        Collections.addAll(list, listItems);

        mAdapter = new CourseFirstAdapter(list);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickLitener(new CourseFirstAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view) {
                Intent intent = new Intent(getActivity().getBaseContext(),CourseFirstPage.class);
                getActivity().startActivity(intent);
            }
        });


        return view;

    }
}
