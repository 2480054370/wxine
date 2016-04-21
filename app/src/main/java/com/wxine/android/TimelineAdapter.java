package com.wxine.android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.MyViewHolder> {
	List<Integer> image; //图片
	List<String> usename; //名字
	List<String> time;    //文本


	public TimelineAdapter(List<Integer> image, List<String> usename, List<String> time) {
		this.image = image;
		this.usename = usename;
		this.time = time;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_item,
				viewGroup, false);
		return new MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
		myViewHolder.icon.setImageResource(image.get(i));
		myViewHolder.name.setText(usename.get(i));
		myViewHolder.content.setText(time.get(i));
	}

	@Override
	public int getItemCount() {
		return usename == null ? 0 : usename.size();
	}

	class MyViewHolder extends RecyclerView.ViewHolder {
		ImageView icon;
		TextView name;
		TextView content;
		public MyViewHolder(View itemView) {
			super(itemView);
			icon = (ImageView) itemView.findViewById(R.id.image);
			name = (TextView) itemView.findViewById(R.id.username);
			content = (TextView) itemView.findViewById(R.id.time);
		}
	}
}
