package com.wxine.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wxine.android.model.Comment;
import com.wxine.android.utils.YepDateFormat;

import java.util.List;

/**
 * Created by zhoubing on 15/10/20.
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private final ImageLoader imageLoader = ImageLoader.getInstance();
    private final Context context;
    private List<Comment> list;

    public void setList(List<Comment> list) {
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView comment_user_logo;
        public TextView comment_user_name;
        public TextView comment_content;
        public TextView comment_ctime;

        public ViewHolder(View v) {
            super(v);
            comment_user_logo = (ImageView)v.findViewById(R.id.comment_user_logo);
            comment_user_name = (TextView)v.findViewById(R.id.comment_user_name);
            comment_content = (TextView)v.findViewById(R.id.comment_content);
            comment_ctime = (TextView)v.findViewById(R.id.comment_ctime);
        }
    }

    public CommentsAdapter(Context context, List<Comment> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_comments_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment comment = list.get(position);
        try {
            imageLoader.displayImage(comment.getUser().getImage(), holder.comment_user_logo);
            holder.comment_user_name.setText(comment.getUser().getName());
            holder.comment_content.setText(comment.getContent());
            String format = "#a";
            String fullFormat = "yyyy-MM-dd";
            String ctime = new YepDateFormat(format, fullFormat).format(comment.getCtime());
            holder.comment_ctime.setText(ctime);
            //Log.v("=====---2222---========", comment.getContent());
        } catch (NullPointerException e){};
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
