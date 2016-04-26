package com.wxine.android;

import android.content.ContentResolver;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by zz on 2016/4/22.
 */
public class PersonalDataRealname_Tab extends Fragment {
    Button PersonalRealnameUpdata;
    Button SelectFile;
    View view;
    private LayoutInflater mInflater;
    private LinearLayout mGallery;
    private Bitmap[] mImgIds;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.personaldata_realname_tab, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        PersonalRealnameUpdata = (Button) view.findViewById(R.id.PersonalRealnameUpdata);
        PersonalRealnameUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "上传已确认", Toast.LENGTH_LONG).show();
            }
        });

        SelectFile = (Button) view.findViewById(R.id.SelectFile);
        SelectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(Intent.ACTION_PICK, null);
                int1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(int1, 1);
            }
        });
        return view;
    }
}
