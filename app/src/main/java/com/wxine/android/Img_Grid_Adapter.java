package com.wxine.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Tom on 2016/5/19.
 */
class Img_Grid_Adapter extends BaseAdapter {
    private Context mContext;

    public Img_Grid_Adapter(Context context) {
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return 10;
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

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }


    class ViewHolder {
        ImageView itemImg;
    }
}
