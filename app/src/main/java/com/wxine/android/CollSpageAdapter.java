package com.wxine.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wxine.android.model.Info;
import com.wxine.android.utils.CircleImageView;

import java.util.List;

/**
 * Created by zz on 2016/4/29.
 */
public class CollSpageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private final Context context;
    private List<Info> list;
    private static OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);

    }

    public CollSpageAdapter(Context context, List<Info> list) {
        this.context = context;
        this.list = list;
        setHasStableIds(true);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.coll_s_top, parent, false));
        } else {
            return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.coll_s_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Info a;
        if (position > 0) {
            a = list.get(position - 1);
        } else {
            a = list.get(position);
        }
        if (holder instanceof ImageViewHolder) {

        } else if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).CollSName.setText(a.getUser().getName());
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        private ImageView CollSUser;
        private TextView CollSName;
        private ImageView CollSImage;
        private ImageView CollSAgreeImg;
        private ImageView CollSShareImg;

        TextViewHolder(View view) {
            super(view);
            CollSUser = (ImageView) view.findViewById(R.id.CollSUser);
            CollSName = (TextView) view.findViewById(R.id.CollSName);
            CollSImage = (ImageView) view.findViewById(R.id.CollSImage);
            CollSAgreeImg = (ImageView) view.findViewById(R.id.CollSAgreeImg);
            CollSShareImg = (ImageView) view.findViewById(R.id.CollSShareImg);
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        private Button button;
        private CircleImageView circleImageView;
        private TextView CollS_name;
        private TextView CollS_FollowMan;
        private ImageView CollS_BgImage;

        ImageViewHolder(final View view) {
            super(view);
            CollS_BgImage = (ImageView) view.findViewById(R.id.CollS_BgImage);
            circleImageView = (CircleImageView) view.findViewById(R.id.icon);
            CollS_name = (TextView) view.findViewById(R.id.CollS_name);
            CollS_FollowMan = (TextView) view.findViewById(R.id.CollS_FollowMan);
            button = (Button) view.findViewById(R.id.class_button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
