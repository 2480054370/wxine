package com.wxine.android;

import android.support.v4.app.Fragment;

/**
 * Created by Bumblebee on 2016/3/29.
 */
public class PhotosAdapter extends PhotosActivity {
    @Override
    protected Fragment createFragment() {
        return new PhotosFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_photos;
    }
}
