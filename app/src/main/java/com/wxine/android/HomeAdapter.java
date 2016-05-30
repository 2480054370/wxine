package com.wxine.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxine.android.model.Info;
import com.wxine.android.utils.CircleImageView;

import java.util.List;

/**
 * Created by zz on 2016/4/29.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private final Context context;
    private List<Info> list;
    private static OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);

        void onKQClick(View view, int position);
    }

    public HomeAdapter(Context context, List<Info> list) {
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
            return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_top, parent, false));
        } else {
            return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false));
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
            ((TextViewHolder) holder).PersonalName.setText(a.getUser().getName());
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
        private ImageView PersonalUser;
        private TextView PersonalName;
        private ImageView PersonalImage;
        private ImageView PersonalAgreeImg;
        private ImageView PersonalShareImg;

        TextViewHolder(View view) {
            super(view);
            PersonalUser = (ImageView) view.findViewById(R.id.PersonalUser);
            PersonalName = (TextView) view.findViewById(R.id.PersonalName);
            PersonalImage = (ImageView) view.findViewById(R.id.PersonalImage);
            PersonalAgreeImg = (ImageView) view.findViewById(R.id.PersonalAgreeImg);
            PersonalShareImg = (ImageView) view.findViewById(R.id.PersonalShareImg);
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        private Button button;
        private CircleImageView circleImageView;
        private TextView personal_name;
        private TextView FollowMan;
        private ImageView PersonalBgImage;

        ImageViewHolder(final View view) {
            super(view);
            PersonalBgImage = (ImageView) view.findViewById(R.id.PersonalBgImage);
            circleImageView = (CircleImageView) view.findViewById(R.id.icon);
            personal_name = (TextView) view.findViewById(R.id.personal_name);
            FollowMan = (TextView) view.findViewById(R.id.FollowMan);
            button = (Button) view.findViewById(R.id.personal_button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onKQClick(v, getLayoutPosition());
                }
            });

        }
    }
}
