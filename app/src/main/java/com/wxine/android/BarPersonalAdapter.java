package com.wxine.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wxine.android.model.Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2016/4/29.
 */
public class BarPersonalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List<Info> list;

    public BarPersonalAdapter(Context context, List<Info> list) {
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
            return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bar_personal_top, parent, false));
        } else {
            return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bar_personal_item, parent, false));
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
            ((TextViewHolder) holder).Name.setText(a.getUser().getName());
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
