package com.wxine.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MySkin extends Activity implements OnClickListener{
	private SkinSettingManager mSettingManager;
	private ImageView iv1,iv2,iv3,iv4,iv5,iv6;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.myskin);
        init();
       }
	private void init() {
		//��ʼ��Ƥ��
		mSettingManager=new SkinSettingManager(this);
		mSettingManager.initSkins();
		iv1=(ImageView) findViewById(R.id.imageView1);
		iv1.setOnClickListener(MySkin.this);
		iv2=(ImageView) findViewById(R.id.imageView2);
		iv2.setOnClickListener(MySkin.this);
		iv3=(ImageView) findViewById(R.id.imageView3);
		iv3.setOnClickListener(MySkin.this);
		iv4=(ImageView) findViewById(R.id.imageView4);
		iv4.setOnClickListener(MySkin.this);
		iv5=(ImageView) findViewById(R.id.imageView5);
		iv5.setOnClickListener(MySkin.this);
		iv6=(ImageView) findViewById(R.id.imageView6);
		iv6.setOnClickListener(MySkin.this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageView1:
			mSettingManager.toggleSkins(0);
			break;
		case R.id.imageView2:
			mSettingManager.toggleSkins(1);
			break;
		case R.id.imageView3:
			mSettingManager.toggleSkins(2);
			break;
		case R.id.imageView4:
			mSettingManager.toggleSkins(3);
			break;
		case R.id.imageView5:
			mSettingManager.toggleSkins(4);
			break;
		case R.id.imageView6:
			mSettingManager.toggleSkins(5);
			break;
		}
	}
}
