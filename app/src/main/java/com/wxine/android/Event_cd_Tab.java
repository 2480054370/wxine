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
public class Event_cd_Tab extends Fragment {
    private CDAdapter cdAdapter;
    private GridView cdGridView;
    private boolean isShowDelete=false;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_cd_tab, container, false);

        cdGridView = (GridView) view.findViewById(R.id.gridView_cd);
        cdGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (isShowDelete) {
                    isShowDelete = false;

                } else {
                    isShowDelete = true;
                }
                cdAdapter.setIsShowDelete(isShowDelete);
                return true;

            }
        });
        cdAdapter=new CDAdapter(getContext(), new String[]{"abc","dsaf","redv"}, new int[]{R.drawable.face,R.drawable.face,R.drawable.face});
        cdGridView.setAdapter(cdAdapter);
        return view;


    }
}
