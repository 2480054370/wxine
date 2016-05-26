package com.wxine.android;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Leeeeee on 2016/4/13.
 */
public class CommThirdAddActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private View vi;
    private Dialog dialog;
    private LinearLayout mLinearLayout1;
    private LinearLayout mLinearLayout2;
    private RelativeLayout mRelativeLayout;
    private TextView privacy_t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comm_third_add);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("创建社群");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        mRelativeLayout = (RelativeLayout)findViewById(R.id.Privacy);




        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    vi = LayoutInflater.from(getBaseContext()).inflate(R.layout.comm_third_adddialog,null);

                    mLinearLayout1 = (LinearLayout)vi.findViewById(R.id.Tab1);
                    mLinearLayout2 = (LinearLayout)vi.findViewById(R.id.Tab2);
                    privacy_t = (TextView)findViewById(R.id.privacy_t);

                    dialog = new Dialog(CommThirdAddActivity.this,R.style.Comma_addDialog);
                    dialog.setContentView(vi);
                    WindowManager.LayoutParams mt = getWindow().getAttributes();

                    Window dialogWindow = dialog.getWindow();
                    WindowManager.LayoutParams dg = dialogWindow.getAttributes();
                    dg.width = (mt.width*1);
                    dg.y = Dp2Px(getBaseContext(), -100);
                    dialogWindow.setAttributes(dg);
                    dialog.show();

                mLinearLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        privacy_t.setText("公开");
                        dialog.hide();
                    }
                });
                mLinearLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        privacy_t.setText("不公开");
                        dialog.hide();
                    }
                });
            }
        });
    }

    //dp -> px
    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
