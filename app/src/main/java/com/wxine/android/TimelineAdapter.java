package com.wxine.android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class TimelineAdapter extends BaseAdapter {

	private Context context;
	private List<Map<String, Object>> list;
	private LayoutInflater inflater;
	private ImageView imageview;

	public TimelineAdapter(Context context, List<Map<String, Object>> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.listview_item, null);
			viewHolder = new ViewHolder();
			viewHolder.image = (ImageView)convertView.findViewById(R.id.image_1);
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			viewHolder.cardView = (CardView)convertView.findViewById(R.id.relative);
			viewHolder.cardView.setOnClickListener(new View.OnClickListener() {

						@Override
				                     public void onClick(View v) {
							Intent intent = new Intent(context, Event_content.class);
							context.startActivity(intent);
					                     }
				                });
			viewHolder.im_event = (ImageView)convertView.findViewById(R.id.im_event);
			viewHolder.im_event.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, SingActivity.class);
					context.startActivity(intent);
				}
			});
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		String titleStr = list.get(position).get("title").toString();
		viewHolder.title.setText(titleStr);

		viewHolder.image.setBackgroundResource((Integer) list.get(position).get("image"));

		return convertView;
	}

	static class ViewHolder {
		public TextView year;
		public TextView month;
		public TextView title;
		public ImageView image;
		public CardView cardView;
		public ImageView im_event;
	}
}
