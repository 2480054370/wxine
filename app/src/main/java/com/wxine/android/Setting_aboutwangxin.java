package com.wxine.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.Toast;

/**
 * Created by zz on 2016/4/12.
 */
public class Setting_aboutwangxin extends AppCompatActivity {
    private Toolbar toolbar;
    TableRow aboutUs;
    TableRow CheckForUpdates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutwangxin);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);

        aboutUs = (TableRow) findViewById(R.id.aboutUs);
        CheckForUpdates = (TableRow) findViewById(R.id.CheckForUpdates);

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Setting_aboutwangxin.this, "关于我们", Toast.LENGTH_SHORT).show();
            }
        });

        CheckForUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Setting_aboutwangxin.this, "已经是最新版本", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
