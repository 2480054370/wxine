package com.wxine.android;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.wxine.android.model.Image;
import com.wxine.android.model.Info;
import com.wxine.android.utils.Images;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bumblebee on 2016/5/9.
 */
public class ImgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public enum TYPE{
        TYPE_TITLE,
        TYPE_IMG;
    }

    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<ArrayList<HashMap<String, Object>>> mList;
    private static Context mContext;
    private final LayoutInflater mLayoutInflater;


    public ImgAdapter(Context context, ArrayList<String> mTitle,ArrayList<ArrayList<HashMap<String, Object>>> mList) {
        mContext = context;
        this.mTitle = mTitle;
        this.mList = mList;
        mLayoutInflater = LayoutInflater.from(context);
        setHasStableIds(true);
    }
    public static class ImgViewHolder extends RecyclerView.ViewHolder {

        GridView gridview;
        //private BaseAdapter gv;
       // private PhotoWallAdapter adapter;
        public ImgViewHolder(View itemView) {
            super(itemView);
            gridview = (GridView) itemView.findViewById(R.id.gridview);
           // adapter = new PhotoWallAdapter(mContext, 0, Images.imageUrls, gridview);
            //gv = new Img_Grid_Adapter(mContext);
        //    gridview.setAdapter(adapter);
        }
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        public TitleViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.title);
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
        if (holder instanceof TitleViewHolder) {
            if(position==0){
                ((TitleViewHolder) holder).time.setText(mTitle.get(position));
            }else{
                ((TitleViewHolder) holder).time.setText(mTitle.get(position / 2));
            }
        } else if (holder instanceof ImgViewHolder) {
           // ArrayList<HashMap<String, Object>> arrayListForEveryGridView = this.mList.get(position);
           // PhotoWallAdapter gridViewAdapter=new PhotoWallAdapter(mContext ,arrayListForEveryGridView);
            //holder.gridView.setAdapter(gridViewAdapter);
           // ((ImgViewHolder) holder).gridview.setAdapter(gridViewAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return mTitle == null ? 0 :mTitle.size()*2;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2==0 ?TYPE.TYPE_TITLE.ordinal():TYPE.TYPE_IMG.ordinal();
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
