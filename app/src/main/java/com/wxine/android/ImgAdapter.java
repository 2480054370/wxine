package com.wxine.android;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bumblebee on 2016/5/9.
 */
public class ImgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public enum TYPE{
        TYPE_TITLE,
        TYPE_IMG;
    }

    private ArrayList<String> mTitle = new ArrayList<>();
    private static  Context mContext;
    private final LayoutInflater mLayoutInflater;


    public ImgAdapter(Context context, ArrayList<String> title) {
        mContext = context;
        mTitle = title;
        mLayoutInflater = LayoutInflater.from(context);
        setHasStableIds(true);
    }
    public static class ImgViewHolder extends RecyclerView.ViewHolder {

        private GridView gridview;
        private BaseAdapter gv;
        public ImgViewHolder(View itemView) {
            super(itemView);
            gridview = (GridView) itemView.findViewById(R.id.gridview);
            gv = new Img_Grid_Adapter(mContext);
            gridview.setAdapter(gv);
        }
    }
    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        public TitleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    private static OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,String title);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewType==TYPE.TYPE_IMG.ordinal()){
            return new ImgViewHolder(mLayoutInflater.inflate(R.layout.activity_img_img,viewGroup,false));
        }else{
            return new TitleViewHolder(mLayoutInflater.inflate(R.layout.activity_img_title,viewGroup,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2==0 ?TYPE.TYPE_TITLE.ordinal():TYPE.TYPE_IMG.ordinal();
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
