package com.wxine.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

/**
 * Created by zz on 2016/4/5.
 */
public class Event_create extends Activity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    private Button button;
    private SkinSettingManager mSettingManager;
    private LinearLayout mylayout;
    private LinearLayout layout;
    private TextView datatextstart;
    private TextView timetextstart;
    private TextView datatextend;
    private TextView timetextend;
    private RelativeLayout datastart;
    private RelativeLayout timestart;
    private RelativeLayout dataend;
    private RelativeLayout timend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);
        button = (Button) findViewById(R.id.close_btn);
        mylayout = (LinearLayout) findViewById(R.id.mylayout);
        datatextstart = (TextView) findViewById(R.id.datatextstart);
        timetextstart = (TextView) findViewById(R.id.timetextstart);
        datatextend = (TextView) findViewById(R.id.datatextend);
        timetextend = (TextView) findViewById(R.id.timetextend);
        datastart = (RelativeLayout) findViewById(R.id.datastart);
        timestart = (RelativeLayout) findViewById(R.id.timestart);
        dataend = (RelativeLayout) findViewById(R.id.dataend);
        timend = (RelativeLayout) findViewById(R.id.timend);

        datastart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        Event_create.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        dataend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog end = DatePickerDialog.newInstance(
                        Event_create.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                end.show(getFragmentManager(), "Datepickerdialogend");
            }
        });

        timestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        Event_create.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );
                tpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });

        timend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog ent = TimePickerDialog.newInstance(
                        Event_create.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );
                ent.show(getFragmentManager(), "Timepickerdialogend");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Event_create.this, MySkin.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        //初始化皮肤
        layout = (LinearLayout) findViewById(R.id.mylayout);
        mSettingManager = new SkinSettingManager(Event_create.this, layout);
        mSettingManager.initSkins();
        super.onResume();
    }
    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
        String minuteString = minute < 10 ? "0" + minute : "" + minute;
        String time = hourString + ":" + minuteString;
        TimePickerDialog tpd = (TimePickerDialog) getFragmentManager().findFragmentByTag("Timepickerdialog");
        TimePickerDialog ent = (TimePickerDialog) getFragmentManager().findFragmentByTag("Timepickerdialogend");
        if (tpd != null) {
            timetextstart.setText(time);
        } else if (ent != null) {
            timetextend.setText(time);
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = year + "年" + (++monthOfYear) + "月" + dayOfMonth + "日";
        DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");
        DatePickerDialog end = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialogend");
        if (dpd != null) {
            datatextstart.setText(date);
        } else if (end != null) {
            datatextend.setText(date);
        }
    }

}
