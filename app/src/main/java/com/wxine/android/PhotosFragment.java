package com.wxine.android;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.wxine.android.utils.ImageLoader;
import com.wxine.android.utils.ImageLoader.Type;
import com.wxine.android.utils.Images;

public class PhotosFragment extends Fragment {
	private GridView mGridView;
	private String[] mUrlStrs = Images.imageThumbUrls;
	private ImageLoader mImageLoader;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImageLoader = ImageLoader.getInstance(3, Type.LIFO);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_photos, container,
				false);
		mGridView = (GridView) view.findViewById(R.id.id_gridview);
		setUpAdapter();
		return view;
	}

	private void setUpAdapter() {
		if (getActivity() == null || mGridView == null)
			return;

		if (mUrlStrs != null) {
			mGridView.setAdapter(new ListImgItemAdaper(getActivity(), 0,
					mUrlStrs));
		} else {
			mGridView.setAdapter(null);
		}

	}

	private class ListImgItemAdaper extends ArrayAdapter<String> {

		public ListImgItemAdaper(Context context, int resource, String[] datas) {
			super(getActivity(), 0, datas);
			Log.e("TAG", "ListImgItemAdaper");
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.adapter_photos_item, parent, false);
			}
			ImageView imageview = (ImageView) convertView
					.findViewById(R.id.id_img);
			imageview.setImageResource(R.drawable.pictures_no);
			mImageLoader.loadImage(getItem(position), imageview, true);
			return convertView;
		}

	}

}
