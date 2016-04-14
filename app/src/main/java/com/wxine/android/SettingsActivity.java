package com.wxine.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

/**
 * Created by zz on 2016/4/5.
 */
public class SettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    TableRow login;
    TableRow message;
    TableRow secret;
    TableRow AboutWangxin;
    TableRow NightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("返回");
        setSupportActionBar(toolbar);

        login = (TableRow) findViewById(R.id.LoginAuthentication);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, Setting_account.class);
                startActivity(intent);
            }
        });

        message = (TableRow) findViewById(R.id.MessageAuthentication);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, Setting_message.class);
                startActivity(intent);
            }
        });

        secret = (TableRow) findViewById(R.id.SecretAdmin);
        secret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, Setting_secret.class);
                startActivity(intent);
            }
        });

        NightMode = (TableRow) findViewById(R.id.NightMode);
        NightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingsActivity.this, "暂停使用", Toast.LENGTH_SHORT).show();
            }
        });

        AboutWangxin = (TableRow) findViewById(R.id.AboutWangxin);
        AboutWangxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, Setting_aboutwangxin.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home){
            finish();
            return true ;
        }
        return super.onOptionsItemSelected(item);
    }
}
