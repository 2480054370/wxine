package com.wxine.android;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.wxine.android.utils.CircleImageView;


public class CDAdapter extends BaseAdapter{
 private String names[];

private int icons[];
 private Context mContext;
 private TextView name_tv;
 private CircleImageView img;



 private View deleteView;
 private boolean isShowDelete;//根据这个变量来判断是否显示删除图标，true是显示，false是不显示

 public CDAdapter(Context mContext, String names[], int icons[]) {
  this.mContext = mContext;
  this.names=names;
  this.icons=icons;
 }
 public void setIsShowDelete(boolean isShowDelete){
  this.isShowDelete=isShowDelete;
  notifyDataSetChanged();
 }

 @Override
 public int getCount() {

  return icons.length;
 }

 @Override
 public Object getItem(final int position) {
  // TODO Auto-generated method stub
  deleteView.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View v) {
    
   }
  });

  return icons[position];
 }

 @Override
 public long getItemId(int position) {
  // TODO Auto-generated method stub
  return position;
 }

 @Override
 public View getView(int position, View convertView, ViewGroup parent) {
  convertView = LayoutInflater.from(mContext).inflate(
    R.layout.grid_item, null);
  img = (CircleImageView) convertView.findViewById(R.id.img);
  name_tv = (TextView) convertView.findViewById(R.id.name_tv);
  deleteView = convertView.findViewById(R.id.delete_markView);
  deleteView.setVisibility(isShowDelete ? View.VISIBLE : View.GONE);//设置删除按钮是否显示

   img.setImageResource(icons[position]);
  name_tv.setText(names[position]);
  return convertView;
 }
}
