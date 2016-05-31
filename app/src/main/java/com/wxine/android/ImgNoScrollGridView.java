package com.wxine.android;

/**
 * Created by Bumblebee on 2016/5/31.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class ImgNoScrollGridView extends GridView {
    public ImgNoScrollGridView(Context context) {
        super(context);

    }

    public ImgNoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 4, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}