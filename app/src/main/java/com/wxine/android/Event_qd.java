package com.wxine.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;

import java.io.File;

/**
 * Created by zz on 2016/4/5.
 */
public class Event_qd extends AppCompatActivity {
    private final static int SCANNIN_GREQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_qd);
        Toolbar toolbar = (Toolbar) findViewById(R.id.qd_toolbar);
        toolbar.setTitle("签到");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TableRow tableRow = (TableRow)findViewById(R.id.im_qd);
        tableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                        "head.jpg")));
                startActivityForResult(intent2, 2);//采用ForResult打开
            }
        });

        TableRow saoma = (TableRow)findViewById(R.id.saoma);
        saoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Event_qd.this, MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case 2:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent(Event_qd.this, Event_qd_show.class);
                    startActivity(intent);
                }

                break;
            case SCANNIN_GREQUEST_CODE:
                if(resultCode == RESULT_OK){
                    Intent intent = new Intent(Event_qd.this, Event_qd_show.class);
                    startActivity(intent);

                }
                break;
        }

    }

}



