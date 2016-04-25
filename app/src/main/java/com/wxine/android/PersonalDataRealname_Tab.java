package com.wxine.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by zz on 2016/4/22.
 */
public class PersonalDataRealname_Tab extends Fragment {
    Button PersonalRealnameUpdata;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personaldata_realname_tab, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        PersonalRealnameUpdata = (Button) view.findViewById(R.id.PersonalRealnameUpdata);
        PersonalRealnameUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "上传已确认", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
