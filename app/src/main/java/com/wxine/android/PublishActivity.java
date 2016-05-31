package com.wxine.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PublishActivity extends Activity {

    PopupWindow popupWindow;    //发布框的相机和图库视图
    EditText editText;          //发布框的文字内容编辑
    ImageButton insert_photo;         //相册
    ImageButton insert_link;     //分享
    private ImageView pub_send;     //发送
    private static String path = "/sdcard/myPub/";//sd路径

    //private DisplayMetrics dm;
    ViewGroup.LayoutParams dp;  //视图
    LinearLayout activity_publish;  //content_publish布局


    private LinearLayout mGallery;  //Gallery视图
    private Bitmap[] mImgIds;       //选中的图片
    private LayoutInflater mInflater;   //图片的item
    private boolean sendTF = false;    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_publish);


        mInflater = LayoutInflater.from(this);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        pub_send = (ImageView) findViewById(R.id.pub_send);
        editText = (EditText) findViewById(R.id.edit_text);
        activity_publish = (LinearLayout) findViewById(R.id.id_test);
        //editText监听
        editText.addTextChangedListener(watcher);

        //发布框的显示
        setFinishOnTouchOutside(false);
        getWindow().setGravity(Gravity.TOP);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = (int) (d.getWidth() * 1);
        p.height = (int) (d.getHeight() * 1);
        getWindow().setAttributes(p);


        //添加照片
        insert_photo = (ImageButton) findViewById(R.id.insert_photo);
        insert_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_photo.setImageResource(R.drawable.ic_insert_photo);
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(R.layout.popupwindow, null);

                /*
                pop inside onclick
                 */
                RelativeLayout pop_camera = (RelativeLayout) view.findViewById(R.id.pop_camera);
                RelativeLayout pop_album = (RelativeLayout) view.findViewById(R.id.pop_album);
                pop_camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent2, 2);//采用ForResult打开
                    }
                });
                pop_album.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent1, 1);
                    }
                });
                if (popupWindow == null) {
                    popupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                    popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
                    popupWindow.showAtLocation(findViewById(R.id.id_test), Gravity.BOTTOM, 0, 0);
                    dp = activity_publish.getLayoutParams();
                    LinearLayout.LayoutParams publishLayout = new LinearLayout.LayoutParams(android.app.ActionBar.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    publishLayout.bottomMargin = Dp2Px(getBaseContext(), 100);
                    activity_publish.setLayoutParams(publishLayout);
                }
            }
        });


//        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    // 此处为得到焦点时的处理内容
//                    if(popupWindow != null && popupWindow.isShowing()){
//                    popupWindow.dismiss();
//                  popupWindow = null;
//                }
////          }
//                } else {
//                    // 此处为失去焦点时的处理内容
//                }
//            }
//        });
        editText.setClickable(true);
        editText.requestFocus();
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_photo.setImageResource(R.drawable.ic_noinsert_photo);
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                    activity_publish.setLayoutParams(dp);
                }
            }
        });
//        editText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(popupWindow != null && popupWindow.isShowing()){
//                    popupWindow.dismiss();
//                    popupWindow = null;
//                }
//            }
//        });


        //分享
        insert_link = (ImageButton) findViewById(R.id.insert_link);
        insert_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!TextUtils.isEmpty(editText.getText()) || mGallery.findViewById(R.id.pub_photos) != null) {
                pub_send.setImageResource(R.drawable.ic_issend);
                sendTF = true;
            } else {
                pub_send.setImageResource(R.drawable.ic_send);
                sendTF = false;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    //选中图片后的返回时事件
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //图库
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();


            //解码图像大小,对图片进行缩放...防止图片过大导致内存溢出...
            BitmapFactory.Options o = new BitmapFactory.Options();//实例化一个对象...

            o.inJustDecodeBounds = true;//这个就是Options的第一个属性,设置为true的时候，不会完全的对图片进行解码操作,不会为其分配内存，只是获取图片的基本信息...

            BitmapFactory.decodeFile(picturePath,o);

            /*
             * 下面也就是对图片进行的一个压缩的操作...如果图片过大，最后会根据指定的数值进行缩放...
             * 找到正确的刻度值，它应该是2的幂.
             * 这里我指定了图片的长度和宽度为70个像素...
             *
             * */

            final int REQUIRED_SIZE=70;
            int width_tmp=o.outWidth, height_tmp=o.outHeight;
            int scale=1;
            while(true){
                if(width_tmp/2<REQUIRED_SIZE || height_tmp/2<REQUIRED_SIZE)
                    break;
                width_tmp/=2;
                height_tmp/=2;
                scale*=2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options(); //这里定义了一个新的对象...获取的还是同一张图片...
            o2.inSampleSize=scale;   //对这张图片设置一个缩放值...inJustDecodeBounds不需要进行设置...
            initData(BitmapFactory.decodeFile(picturePath,o2));
            initView();

            //显示在Gallery视图
//            initData(BitmapFactory.decodeFile(picturePath));

        }

        //相机
        if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
            Bundle extras = data.getExtras();

            Bitmap head = extras.getParcelable("data");
            setPicToView(head);//保存在SD卡中
            initData(head);
            initView();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    //SD卡检测
    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    //照片的显示
    private void initData(Bitmap pub_photos) {
        mImgIds = new Bitmap[]{pub_photos};
    }

    private void initView() {
        mGallery = (LinearLayout) findViewById(R.id.id_gallery);
        for (int i = 0; i < mImgIds.length; i++) {
            final View view = mInflater.inflate(R.layout.album_item,
                    mGallery, false);
            final ImageView img = (ImageView) view.findViewById(R.id.pub_photos);
            img.setImageBitmap(mImgIds[i]);
            mGallery.addView(view);
            //删除照片
            ImageView remove = (ImageView) view.findViewById(R.id.remove_img);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGallery.removeView(view);
                    if(mGallery.findViewById(R.id.pub_photos) == null){
                        pub_send.setImageResource(R.drawable.ic_send);
                        sendTF = false;
                    }
                }
            });
        }

        if (!TextUtils.isEmpty(editText.getText()) || mGallery.findViewById(R.id.pub_photos) != null) {
            pub_send.setImageResource(R.drawable.ic_issend);
            sendTF = true;
        } else {
            pub_send.setImageResource(R.drawable.ic_send);
            sendTF = false;
        }
    }

    //dp -> px
    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    //判断发布框是否有内容
    @Override
    public void onBackPressed() {
        if (sendTF == true) {
            LayoutInflater inflaterDl = LayoutInflater.from(this);
            LinearLayout layout = (LinearLayout) inflaterDl.inflate(R.layout.publish_dialog, null);

            //对话框
            final Dialog dialog = new AlertDialog.Builder(PublishActivity.this, R.style.Pub_Dialog).create();
            dialog.show();
            dialog.getWindow().setContentView(layout);

            WindowManager.LayoutParams mt = getWindow().getAttributes();
            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams dg = dialogWindow.getAttributes();
            dg.width = (int) Math.ceil(mt.width * 0.8);
            dialogWindow.setAttributes(dg);
            dialog.show();
            //取消按钮
            TextView btnCancel = (TextView) layout.findViewById(R.id.cancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.hide();
                }
            });
            //确定按钮
            TextView btnOK = (TextView) layout.findViewById(R.id.confirm);
            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });


            /*new AlertDialog.Builder(PublishActivity.this)
                    .setMessage("要舍弃这条信息吗?")//设置显示的内容
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {//添加确定按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                            finish();
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {//添加返回按钮

                @Override
                public void onClick(DialogInterface dialog, int which) {//响应事件

                }

            }).show();//在按键响应事件中显示此对话框*/
        } else {
            finish();
        }
    }
}
