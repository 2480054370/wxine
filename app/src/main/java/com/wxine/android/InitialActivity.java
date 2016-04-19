package com.wxine.android;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.wxine.android.auth.LoginActivity;
import com.wxine.android.utils.SystemStatusManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Leeeeee on 2016/3/31.
 */
public class InitialActivity extends AppCompatActivity {
    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
状态栏颜色
 */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemStatusManager tintManager = new SystemStatusManager(this);
            tintManager.setStatusBarTintEnabled(true);
            // 设置状态栏的颜色
            //tintManager.setStatusBarTintResource(R.color.black);
            //getWindow().getDecorView().setFitsSystemWindows(true);
        }

        app = (MyApplication) getApplication();
        setContentView(R.layout.activity_initial);

        if (app.isLogin()) {
            final Intent intent = new Intent(InitialActivity.this, MainActivity.class);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    startActivity(intent);
                    finish();
                }
            };
            timer.schedule(task, 1000 * 3);
        } else {
            final Intent intent = new Intent(this, LoginActivity.class);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    startActivity(intent);
                    finish();
                }
            };
            timer.schedule(task, 1000 * 3);
        }
    }
}
