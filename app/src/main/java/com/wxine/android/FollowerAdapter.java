package com.wxine.android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Bumblebee on 2016/3/31.
 */
public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.MyViewHolder> {
    List<Integer> mDataset; //图片
    List<String> mListData; //名字
    List<String> mDatas;    //文本


    public FollowerAdapter(List<Integer> mDataset, List<String> mListData, List<String> mDatas) {
        this.mDataset = mDataset;
        this.mListData = mListData;
        this.mDatas = mDatas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_follower_item,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.icon.setImageResource(mDataset.get(i));
        myViewHolder.name.setText(mListData.get(i));
        myViewHolder.content.setText(mDatas.get(i));
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;
        TextView content;
        public MyViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.listitem_icon);
            name = (TextView) itemView.findViewById(R.id.listitem_name);
            content = (TextView) itemView.findViewById(R.id.listitem_content);
        }
    }
}
