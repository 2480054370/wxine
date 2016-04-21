package com.wxine.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
public class EventCreate extends Activity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    private RelativeLayout close;
    private Button but;
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
        close = (RelativeLayout) findViewById(R.id.close);
        but = (Button) findViewById(R.id.but);
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
                        EventCreate.this,
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
                        EventCreate.this,
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
                        EventCreate.this,
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
                        EventCreate.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );
                ent.show(getFragmentManager(), "Timepickerdialogend");
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventCreate.this, MySkin.class);
                startActivity(intent);
            }
        });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventCreate.this, MySkin.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        //初始化皮肤
        layout = (LinearLayout) findViewById(R.id.mylayout);
        mSettingManager = new SkinSettingManager(EventCreate.this, layout);
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
