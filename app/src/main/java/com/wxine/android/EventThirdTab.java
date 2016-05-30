package com.wxine.android;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by zz on 2016/4/21.
 */
public class EventThirdTab extends Fragment {
    private GridView mGridView;
    private GridViewAdapter mAdapter;
    private boolean isShowDelete=false;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_wcj_tab, container, false);

        mGridView = (GridView) view.findViewById(R.id.gridView_wcj);
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (isShowDelete) {
                    isShowDelete = false;

                } else {
                    isShowDelete = true;
                }
                mAdapter.setIsShowDelete(isShowDelete);
                return true;

            }
        });
        mAdapter=new GridViewAdapter(getContext(), new String[]{"abc","dsaf"}, new int[]{R.drawable.face,R.drawable.face});
        mGridView.setAdapter(mAdapter);
        return view;


    }
}
