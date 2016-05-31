package com.wxine.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by zz on 2016/4/5.
 */
@EActivity(R.layout.activity_setting)
public class SettingsActivity extends AppCompatActivity {
    @ViewById
    Toolbar Setting_toolbar;

    @ViewById
    Switch ModeSwitchButton;

    @ViewById
    TableRow LoginAuthentication;

    @ViewById
    TableRow MessageAuthentication;

    @ViewById
    TableRow SecretAdmin;

    @ViewById
    TableRow NightMode;

    @ViewById
    TableRow AboutWangxin;

    @AfterViews
    void init() {
        Setting_toolbar.setTitle("返回主页");
        setSupportActionBar(Setting_toolbar);
    }

    @Click({R.id.LoginAuthentication, R.id.MessageAuthentication, R.id.SecretAdmin, R.id.NightMode, R.id.AboutWangxin})
    void TableRowSelect(View view) {
        switch (view.getId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.LoginAuthentication:
                Intent Lintent = new Intent(this, SettingsAccount_.class);
                startActivity(Lintent);
                break;
            case R.id.MessageAuthentication:
                Intent Mintent = new Intent(this, SettingsMessage_.class);
                startActivity(Mintent);
                break;
            case R.id.SecretAdmin:
                Intent Sintent = new Intent(this, SettingsSecret_.class);
                startActivity(Sintent);
                break;
            case R.id.NightMode:
                if (ModeSwitchButton.isChecked()) {
                    ModeSwitchButton.setChecked(false);
                    Toast.makeText(SettingsActivity.this, "true", Toast.LENGTH_SHORT).show();
                } else {
                    ModeSwitchButton.setChecked(true);
                    Toast.makeText(SettingsActivity.this, "true", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.AboutWangxin:
                Intent Aintent = new Intent(this, SettingsAboutwangxin_.class);
                startActivity(Aintent);
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