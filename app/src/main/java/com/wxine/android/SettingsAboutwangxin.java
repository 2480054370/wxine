package com.wxine.android;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by zz on 2016/4/12.
 */
@EActivity(R.layout.settings_aboutwangxin)
public class SettingsAboutwangxin extends AppCompatActivity {

    @ViewById
    Toolbar Aboutwx_toolbar;

    @ViewById
    TableRow aboutUs;

    @ViewById
    TableRow CheckForUpdates;

    @AfterViews
    void init() {
        Aboutwx_toolbar.setTitle("设置");
        setSupportActionBar(Aboutwx_toolbar);
    }

    @Click({R.id.aboutUs, R.id.CheckForUpdates})
    void Aboutwangxin(View view) {
        switch (view.getId()) {
            case R.id.aboutUs:
                Toast.makeText(SettingsAboutwangxin.this, "关于我们", Toast.LENGTH_SHORT).show();
                break;
            case R.id.CheckForUpdates:
                Toast.makeText(SettingsAboutwangxin.this, "已经是最新版本", Toast.LENGTH_SHORT).show();
                break;
        }
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
