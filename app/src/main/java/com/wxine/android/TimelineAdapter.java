package com.wxine.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.wxine.android.model.Info;

import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.MyViewHolder> implements View.OnClickListener{
	List<Integer> image; //图片
	List<String> usename; //名字
	List<String> time;    //文本
	private PopupWindow popupwindow;

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

	@Override
	public void onClick(View v) {
		if (mOnItemClickListener != null) {
			//注意这里使用getTag方法获取数据
			mOnItemClickListener.onItemClick(v, (Info) v.getTag());
		}
	}

	public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
		this.mOnItemClickListener = listener;
	}

	class MyViewHolder extends RecyclerView.ViewHolder {
		ImageView icon;
		TextView name;
		TextView content;
		CardView cardView;
		ImageView imitem;
		Button btn1;
		Button btn2;
		Button btn3;
		public MyViewHolder(View itemView) {
			super(itemView);
			icon = (ImageView) itemView.findViewById(R.id.image);
			name = (TextView) itemView.findViewById(R.id.username);
			content = (TextView)itemView.findViewById(R.id.listview_time);
			imitem = (ImageView)itemView.findViewById(R.id.im_event);
			imitem.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(content.getContext(), SingActivity.class);
					content.getContext().startActivity(intent);
				}
			});

			cardView = (CardView)itemView.findViewById(R.id.relative);
			cardView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(content.getContext(), Event_content.class);
					content.getContext().startActivity(intent);

				}
			});
			btn1 = (Button)itemView.findViewById(R.id.btn1);
			btn1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mOnItemClickListener.onKQClick(v, (Info) v.getTag());
				}
			});

			btn2 = (Button)itemView.findViewById(R.id.btn2);
			btn2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mOnItemClickListener.onQJClick(v, (Info) v.getTag());
				}
			});

			btn3 = (Button)itemView.findViewById(R.id.btn3);
			btn3.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mOnItemClickListener.onPJClick(v, (Info) v.getTag());
				}
			});
		}
	}


	private static OnRecyclerViewItemClickListener mOnItemClickListener = null;


	//define interface
	public static interface OnRecyclerViewItemClickListener {
		void onItemClick(View view, Info data);
		void onKQClick(View view,Info da);
		void onQJClick(View view,Info da);
		void onPJClick(View view,Info da);
	}
}
