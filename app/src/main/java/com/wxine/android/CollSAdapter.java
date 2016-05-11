package com.wxine.android;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 123 on 2015/12/30.
 */
public class CollSAdapter extends RecyclerView.Adapter<CollSAdapter.MyViewHolder> implements View.OnClickListener {
    List<String> mListData;
    private static OnItemClickLitener mOnItemClickLitener = null;

    public CollSAdapter(List<String> mListData) {
        this.mListData = mListData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.f_collS_card,
                viewGroup, false);
        view.setOnClickListener(this);
        return new MyViewHolder(i,view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(mListData.get(i));
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickLitener != null) {
            mOnItemClickLitener.onItemClick(v);
        }
    }

    public static interface OnItemClickLitener
    {
        void onItemClick(View view);
    }



    public  void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        CardView cardView;

        public MyViewHolder(final int position, View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.coll_name);
        }
    }

}
