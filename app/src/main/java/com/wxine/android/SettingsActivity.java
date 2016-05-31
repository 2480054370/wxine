package com.wxine.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by zz on 2016/4/5.
 */
@EActivity(R.layout.activity_setting)
public class SettingsActivity extends AppCompatActivity {
    @ViewById
    Toolbar toolbar;

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

    @Click({R.id.LoginAuthentication, R.id.MessageAuthentication, R.id.SecretAdmin, R.id.NightMode, R.id.AboutWangxin})
    void TableRowSelect(View view) {
        switch (view.getId()) {
            case R.id.LoginAuthentication:
                Intent Lintent = new Intent(this, SettingsAccount.class);
                startActivity(Lintent);
                break;
            case R.id.MessageAuthentication:
                Intent Mintent = new Intent(this, SettingsMessage.class);
                startActivity(Mintent);
                break;
            case R.id.SecretAdmin:
                Intent Sintent = new Intent(this, SettingsSecret.class);
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
                Intent Aintent = new Intent(this, SettingsAboutwangxin.class);
                startActivity(Aintent);
                break;
        }
    }
}
