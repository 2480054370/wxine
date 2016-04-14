package com.wxine.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wxine.android.auth.LoginActivity;

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
        app = (MyApplication) getApplication();
        setContentView(R.layout.activity_initial);

        if(app.isLogin()) {
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
        }else {
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
