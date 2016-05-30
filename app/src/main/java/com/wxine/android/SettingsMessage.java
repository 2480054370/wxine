package com.wxine.android;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.Toast;

/**
 * Created by zz on 2016/4/8.
 */
public class SettingsMessage extends AppCompatActivity {
    private Toolbar toolbar;
    private Switch EmailSwitchButton;
    private Switch PhoneSwitchButton;
    TableRow EmailNotice;
    TableRow PhoneNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_message);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);

        PhoneSwitchButton = (Switch) findViewById(R.id.PhoneSwitchButton);
        EmailSwitchButton = (Switch) findViewById(R.id.EmailSwitchButton);
        EmailNotice = (TableRow) findViewById(R.id.EmailNotice);
        PhoneNotice = (TableRow) findViewById(R.id.PhoneNotice);

        EmailNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EmailSwitchButton.isChecked()) {
                    EmailSwitchButton.setChecked(false);
                    Toast.makeText(SettingsMessage.this, "true", Toast.LENGTH_SHORT).show();
                } else {
                    EmailSwitchButton.setChecked(true);
                    Toast.makeText(SettingsMessage.this, "true", Toast.LENGTH_SHORT).show();
                }
            }
        });

        PhoneNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PhoneSwitchButton.isChecked()) {
                    PhoneSwitchButton.setChecked(false);
                    Toast.makeText(SettingsMessage.this, "true", Toast.LENGTH_SHORT).show();
                } else {
                    PhoneSwitchButton.setChecked(true);
                    Toast.makeText(SettingsMessage.this, "true", Toast.LENGTH_SHORT).show();
                }
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
