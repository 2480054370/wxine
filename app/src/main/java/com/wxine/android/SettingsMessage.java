package com.wxine.android;


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
 * Created by zz on 2016/4/8.
 */
@EActivity(R.layout.settings_message)
public class SettingsMessage extends AppCompatActivity {

    @ViewById
    Toolbar Message_toolbar;

    @ViewById
    Switch EmailSwitchButton;

    @ViewById
    Switch PhoneSwitchButton;

    @ViewById
    TableRow EmailNotice;

    @ViewById
    TableRow PhoneNotice;

    @AfterViews
    void init() {
        Message_toolbar.setTitle("设置");
        setSupportActionBar(Message_toolbar);
    }

    @Click({R.id.EmailNotice, R.id.PhoneNotice})
    void SButtonSelect(View view) {
        switch (view.getId()) {
            case R.id.EmailNotice:
                if (EmailSwitchButton.isChecked()) {
                    EmailSwitchButton.setChecked(false);
                    Toast.makeText(SettingsMessage.this, "false", Toast.LENGTH_SHORT).show();
                } else {
                    EmailSwitchButton.setChecked(true);
                    Toast.makeText(SettingsMessage.this, "true", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.PhoneNotice:
                if (PhoneSwitchButton.isChecked()) {
                    PhoneSwitchButton.setChecked(false);
                    Toast.makeText(SettingsMessage.this, "false", Toast.LENGTH_SHORT).show();
                } else {
                    PhoneSwitchButton.setChecked(true);
                    Toast.makeText(SettingsMessage.this, "true", Toast.LENGTH_SHORT).show();
                }
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
