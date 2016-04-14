package com.wxine.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxine.android.model.Info;

import java.util.ArrayList;

/**
 * Created by NM on 2016/4/5.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    private  Context context;
    private  static ArrayList<Info>list;

    public SearchAdapter(Context context,ArrayList<Info> list){
        this.list = list;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_user_name;
        private ImageView tv_user_logo;
        private TextView iv_info_content;
        private ImageView iv_info_image;
        public ViewHolder( Context context , View v) {
            super(v);
            tv_user_name = (TextView) v.findViewById(R.id.tv_user_name);
            tv_user_logo = (ImageView)v.findViewById(R.id.tv_user_logo);
            iv_info_content = (TextView)v.findViewById(R.id.iv_info_content);
            iv_info_image = (ImageView)v.findViewById(R.id.iv_info_image);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_details, parent, false);
        ViewHolder vh = new ViewHolder(context,v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Info info  = list.get(position);
        holder.tv_user_logo.setImageResource(R.drawable.tou);
        holder.tv_user_name.setText("王尼玛");
        holder.iv_info_content.setText("In my dual profession as an educator andhealth care provider, I have worked with numerous children infected with the virus that causes AIDS. The relationships that I have had with these special kids have been gifts in my life. They have taught me so many things, but I have especially learned that great courage can be found in the smallest of packages. Let me tell you about Tyler.");
        holder.iv_info_image.setImageResource(R.drawable.pic1);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
