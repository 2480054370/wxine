package com.wxine.android;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class ImgLoading extends Dialog {
    public ImgLoading(Context context) {
        super(context, R.style.ImgLoadingStyle);
        //setOwnerActivity((Activity) context);// 设置dialog全屏显示
    }

    private ImgLoading(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_loading);
    }
}
