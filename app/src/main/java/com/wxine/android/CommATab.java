package com.wxine.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CommATab extends Fragment {

    private CommAAdapter mAdapter;
    FloatingActionButton fab;

    private String mItemData = "Lorem Ipsum.";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_comma_recyclerview, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(getContext(), CommAddActivity.class);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView = (RecyclerView) view.findViewById(
                R.id.fragment_list_rv);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        String[] listItems = mItemData.split(" ");

        List<String> list = new ArrayList<String>();
        Collections.addAll(list, listItems);

        mAdapter = new CommAAdapter(list);
        recyclerView.setAdapter(mAdapter);

        return view;

    }
}
