package com.wxine.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 2016/5/19.
 */
class Img_Grid_Adapter extends BaseAdapter {
    private Context mContext;
    private List<Integer> imageList = new ArrayList();

    public Img_Grid_Adapter(Context context) {
        this.mContext = context;
        /*imageList.add(R.drawable.b1);
        imageList.add(R.drawable.b2);
        imageList.add(R.drawable.b3);
        imageList.add(R.drawable.b4);
        imageList.add(R.drawable.b5);*/

    }


    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;


        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_img_img_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.itemImg = (ImageView) convertView.findViewById(R.id.iv_head);
            AbsListView.LayoutParams lpRef = new AbsListView.LayoutParams(120, 120);
            viewHolder.itemImg.setLayoutParams(lpRef);
            //viewHolder.itemImg.setImageResource(imageList.get(position));

            convertView.setTag(viewHolder);
            return viewHolder.itemImg;
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }


    class ViewHolder {
        ImageView itemImg;
    }
}
