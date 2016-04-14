package com.wxine.android;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AlbumsActivity extends AppCompatActivity {
    private ImageAdapter myImageAdapter;
    private ArrayList<Uri> itemList = new ArrayList<Uri>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        GridView gridview = (GridView) findViewById(R.id.gridView);
        myImageAdapter = new ImageAdapter(this);
        gridview.setAdapter(myImageAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("点击了:", String.valueOf(position));
                if (position == 0) {//第一个启动相机
                    startCamera();
                } else {
                    //其他的直接启动裁剪
                    currentPhoto = itemList.get(position);
                    startCrop(currentPhoto);
                }
            }
        });

        myImageAdapter.add(getImageUri(R.drawable.photo));//第一张
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File[] files = storageDir.listFiles();
        if (files != null && files.length>0) {
            for (File file : files) {
                myImageAdapter.add(Uri.fromFile(file));
            }
        }
    }

    private Uri getImageUri(int resId) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(resId)
                + '/' + getResources().getResourceTypeName(resId)
                + '/' + getResources().getResourceEntryName(resId));
    }

    public class ImageAdapter extends BaseAdapter {
        private final ImageLoader imageLoader = ImageLoader.getInstance();
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        void add(Uri uri){
            itemList.add(uri);
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.v("position:", String.valueOf(position));
            ImageView imageView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(220, 220));//在这里修改布局参数可能引起第一项点击出问题
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }
            if (position == 0)
                imageLoader.displayImage("drawable://" + R.drawable.photo, imageView);
            else
                imageLoader.displayImage(itemList.get(position).toString(), imageView);
            return imageView;
        }
    }

    /**
     * 裁剪图片方法实现
     * @param uri
     */
    public void startCrop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop = true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例，这里设置的是正方形（长宽比为1:1）
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, FROM_CROP);
        Log.v("message:", "启动了裁剪");
    }

    //照相机
    static final int FROM_CAMERA = 1;//相机
    static final int FROM_CROP = 2;//裁剪
    Uri currentPhoto;
    private void startCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            try {
                currentPhoto = createImageFile();
            } catch (IOException ex) {
                Log.v("create_image_error:", ex.getMessage());
            }
            if (currentPhoto != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, currentPhoto);
                startActivityForResult(takePictureIntent, FROM_CAMERA);
            }
        }
    }

    //String mCurrentPhotoPath;
    private Uri createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //app私有文件getExternalFilesDir()
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Log.v("===DIRECTORY_PICTURES==", Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return Uri.fromFile(image);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("=====", requestCode + "===" + resultCode);
        switch (requestCode) {
            case FROM_CAMERA:
                startCrop(currentPhoto);
                break;
            case FROM_CROP:
                if (data != null) {
                    Log.v("裁剪后：", data.getExtras().toString());
                }
                Intent intent = new Intent();
                intent.setData(currentPhoto);
                if (getParent() == null) {
                    setResult(Activity.RESULT_OK, intent);
                } else {
                    getParent().setResult(Activity.RESULT_OK, intent);
                }
                finish();
                break;
        }
    }
}
