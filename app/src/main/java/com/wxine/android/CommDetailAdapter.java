package com.wxine.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wxine.android.model.Info;

import java.util.List;

/**
 * Created by zhoubing on 15/10/16.
 */
public class CommDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List<Info> list;

    public CommDetailAdapter(Context context, List<Info> list) {
        this.context = context;
        this.list = list;
        setHasStableIds(true);

    }


    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comm_top, parent, false));
        } else {
            return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comm_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ImageViewHolder) {

        } else if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).Name.setText("aa");
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }


    public static class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView Name;

        TextViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.PersonalName);
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageViewHolder(View view) {
            super(view);
        }
    }

}

