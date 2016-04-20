package com.wxine.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxine.android.model.Info;

import java.util.List;

/**
 * Created by NM on 2016/4/7.
 */
public class NotificationReadAdapter extends RecyclerView.Adapter<NotificationReadAdapter.ViewHolder>{
    private Context context;
    private List<Info>list;

    public NotificationReadAdapter(Context context, List<Info>list){
        this.list = list;
        this.context = context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView userName;
        private TextView userStatut;
        private ImageView headImg;
        public ViewHolder(Context context,View itemView) {
            super(itemView);
            headImg = (ImageView)itemView.findViewById(R.id.headImg);
            userStatut = (TextView)itemView.findViewById(R.id.userStatut);
            userName =(TextView)itemView.findViewById(R.id.userName);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_dateil, parent, false);
        ViewHolder view  = new ViewHolder(context,v);
        return view;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.userName.setText("张三");
        holder.userStatut.setText("关注了你!");
        holder.headImg.setImageResource(R.drawable.tou);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
