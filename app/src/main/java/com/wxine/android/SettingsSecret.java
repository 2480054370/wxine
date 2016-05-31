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
 * Created by zz on 2016/4/11.
 */
@EActivity(R.layout.settings_secret)
public class SettingsSecret extends AppCompatActivity {

    @ViewById
    Toolbar Secret_toolbar;

    @ViewById
    Button SettingSecretUpdata;

    @AfterViews
    void init() {
        Secret_toolbar.setTitle("设置");
        setSupportActionBar(Secret_toolbar);
    }

    @Click(R.id.SettingSecretUpdata)
    void SettingSecretUpdata() {
        Toast.makeText(SettingsSecret.this, "隐私设置确认更新", Toast.LENGTH_SHORT).show();
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
