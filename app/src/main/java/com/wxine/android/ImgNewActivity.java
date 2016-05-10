package com.wxine.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Bumblebee on 2016/5/9.
 */
public class ImgNewActivity extends Activity{
    private RelativeLayout no;
    private RelativeLayout yes;
    private EditText edtext;
    private TextView yes_text;
    public Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_new);
        no = (RelativeLayout) findViewById(R.id.no);
        yes = (RelativeLayout) findViewById(R.id.yes);
        edtext = (EditText) findViewById(R.id.ed_img);
        yes_text = (TextView) findViewById(R.id.yes_text);
        edtext.addTextChangedListener(watcher);


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = "";
                str1 = edtext.getText().toString();
                intent = new Intent(ImgNewActivity.this, ImgActivity.class);
                intent.putExtra("text", str1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //发布框的显示
        setFinishOnTouchOutside(false);
        getWindow().setGravity(Gravity.TOP);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = (int) (d.getWidth() * 1);
        p.height = (int) (d.getHeight() * 1);
        getWindow().setAttributes(p);
    }
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            yes_text.setTextColor(0xff067DFF);
            if(edtext.getText().length() == 0){
                yes_text.setTextColor(0xff757575);
            }
        }
    };
}
