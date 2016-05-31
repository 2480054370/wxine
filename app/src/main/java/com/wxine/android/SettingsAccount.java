package com.wxine.android;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by zz on 2016/4/5.
 */
@EActivity(R.layout.settings_account)
public class SettingsAccount extends AppCompatActivity {
    @ViewById
    Toolbar Account_toolbar;

    @ViewById
    Button SettingAccountUpdata;

    @AfterViews
    void init() {
        Account_toolbar.setTitle("设置");
        setSupportActionBar(Account_toolbar);
    }

    @Click(R.id.SettingAccountUpdata)
    void SettingAccountUpdata() {
        Toast.makeText(SettingsAccount.this, "登录认证确认更新", Toast.LENGTH_SHORT).show();
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
