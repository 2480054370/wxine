package com.wxine.android;


import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.wxine.android.utils.SystemStatusManager;

/**
 * Created by zz on 2016/4/8.
 */
public class Setting_message extends AppCompatActivity {
    private Toolbar toolbar;
    private Switch EmailSwitchButton;
    private Switch PhoneSwitchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);

        PhoneSwitchButton = (Switch) findViewById(R.id.PhoneSwitchButton);
        EmailSwitchButton = (Switch) findViewById(R.id.EmailSwitchButton);

        if (PhoneSwitchButton.getTag().toString().equals("true")) {
            Log.d("-------------------", "test");
            PhoneSwitchButton.setChecked(true);
        } else {
            Log.d("---------------------", "test2");
            PhoneSwitchButton.setChecked(false);
        }
        EmailSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    Toast.makeText(Setting_message.this, "Hello baishi1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Setting_message.this, "Hello baishi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        PhoneSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    //Log.d("---------------------", "test2");
                    //PhoneSwitchButton.setTag("true");
                    Toast.makeText(Setting_message.this, "Hello biao1", Toast.LENGTH_SHORT).show();
                } else {
                    //PhoneSwitchButton.setTag("false");
                    Toast.makeText(Setting_message.this, "Hello biao", Toast.LENGTH_SHORT).show();
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
