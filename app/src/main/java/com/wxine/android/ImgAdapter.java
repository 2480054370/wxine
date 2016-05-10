package com.wxine.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bumblebee on 2016/5/9.
 */
public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.MyViewHolder> implements View.OnClickListener {
    private final Context mContext;
    private ArrayList<String> mTitle = new ArrayList<>();

    public ImgAdapter(Context context, ArrayList<String> title) {
        mContext = context;
        mTitle = title;
        setHasStableIds(true);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }

    private static OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,String title);
    }

    @Override
    public ImgAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_img_item,
                viewGroup, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImgAdapter.MyViewHolder holder, int position) {
        String title = mTitle.get(position);
        holder.mTextView.setText(mTitle.get(position));
        holder.itemView.setTag(title);
    }

    @Override
    public int getItemCount() {
        return mTitle.size();
    }


    public void add(String text, int position) {
        mTitle.add(position, text);
        notifyItemInserted(position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(String)v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


}
