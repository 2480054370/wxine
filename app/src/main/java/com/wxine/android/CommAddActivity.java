package com.wxine.android;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Leeeeee on 2016/4/13.
 */
public class CommAddActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_comma_add);
        findViewById(R.id.Privacy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View vi = LayoutInflater.from(getBaseContext()).inflate(R.layout.f_comma_adddialog,null);
                Dialog dialog = new Dialog(CommAddActivity.this,R.style.Comma_addDialog);
                dialog.setContentView(vi);
                WindowManager.LayoutParams mt = getWindow().getAttributes();

                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams dg = dialogWindow.getAttributes();
                dg.width = (mt.width*1);
                dg.y = Dp2Px(getBaseContext(), -100);
                dialogWindow.setAttributes(dg);
                dialog.show();
            }
        });
    }
    //dp -> px
    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
