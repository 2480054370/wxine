package com.wxine.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

/**
 * Created by zz on 2016/4/5.
 */
public class Event_content extends AppCompatActivity {
    FloatingActionButton fab;
    private Button button;
    private EditText startDateTime;
    private EditText endDateTime;
    private SkinSettingManager mSettingManager;
    private Button btn;
    private LinearLayout layout;
    private Toolbar toolbar;
    private Spinner spCity = null;
    private ArrayAdapter<CharSequence> adapterCity = null;
    private static String[] cityInfo={"会参加","不会参加"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_content);
        toolbar = (Toolbar) findViewById(R.id.content_toolbar);
        toolbar.setTitle("活动");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        this.spCity = (Spinner) super.findViewById(R.id.spinnerCity);
        this.adapterCity = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_dropdown_item, cityInfo);
        this.spCity.setAdapter(adapterCity);
    }


}
