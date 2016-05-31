package com.wxine.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bumblebee on 2016/5/9.
 */
public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.MyViewHolder> {
    private ArrayList<String> mTitle = new ArrayList<>();
    private Context mContext;
    private ArrayList<ArrayList<HashMap<String, Object>>> mList;


    public ImgAdapter(Context context, ArrayList<String> mTitle,ArrayList<ArrayList<HashMap<String, Object>>> mList) {
        super();
        this.mContext = context;
        this.mTitle = mTitle;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.img_title,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.time.setText(mTitle.get(position));
        if (holder.gridView != null) {
            ArrayList<HashMap<String, Object>> arrayListForEveryGridView = mList.get(position);
            ImgGridAdapter gridViewAdapter=new ImgGridAdapter(mContext, arrayListForEveryGridView);
            holder.gridView.setAdapter(gridViewAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return mTitle.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        GridView gridView;
        public MyViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.title);
            gridView = (GridView) itemView.findViewById(R.id.listview_item_gridview);
        }
    }


}
