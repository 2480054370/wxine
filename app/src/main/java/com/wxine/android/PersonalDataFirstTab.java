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
 * Created by zz on 2016/4/21.
 */
public class PersonalDataFirstTab extends Fragment {
    Button PersonalInformationUpdata;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personaldata_first_tab, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        PersonalInformationUpdata = (Button) view.findViewById(R.id.PersonalInformationUpdata);
        PersonalInformationUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "个人基本信息确认更新", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
