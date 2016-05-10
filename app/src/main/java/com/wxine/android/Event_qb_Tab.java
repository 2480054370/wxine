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
public class Event_qb_Tab extends Fragment {
    private GridView qbGridView;
    private QBAdapter qbAdapter;
    private boolean isShowDelete=false;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_qb_tab, container, false);
        qbGridView = (GridView) view.findViewById(R.id.gridView_qb);
        qbGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (isShowDelete) {
                    isShowDelete = false;

                } else {
                    isShowDelete = true;

                }
                qbAdapter.setIsShowDelete(isShowDelete);
                return true;

            }
        });
        qbAdapter=new QBAdapter(getContext(), new String[]{"abc","dsaf","redv","gdsfs","test","ffff","aaaa"}, new int[]{R.drawable.face,R.drawable.face,R.drawable.face,R.drawable.face,R.drawable.face,R.drawable.face,R.drawable.face});
        qbGridView.setAdapter(qbAdapter);
        return view;


    }
}
