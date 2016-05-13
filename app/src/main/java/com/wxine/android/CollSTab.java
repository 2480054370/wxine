package com.wxine.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by @vitovalov on 30/9/15.
 */
public class CollSTab extends Fragment {

    private CollSAdapter mAdapter;

    private String mItemData = "Lorem Ipsum is simply dummy text of the printing and ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_colls_recyclerview, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(
                R.id.fragment_list_rv);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        String[] listItems = mItemData.split(" ");

        List<String> list = new ArrayList<String>();
        Collections.addAll(list, listItems);

        mAdapter = new CollSAdapter(list);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickLitener(new CollSAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view) {
                Toast.makeText(getContext(),"---",Toast.LENGTH_SHORT).show();
            }
        });


        return view;

    }
}
