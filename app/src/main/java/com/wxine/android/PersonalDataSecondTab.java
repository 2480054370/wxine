package com.wxine.android;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by zz on 2016/4/22.
 */
public class PersonalDataSecondTab extends Fragment {
    Button PersonalRealnameUpdata;
    Button SelectFile;
    private View view;
    private LayoutInflater mInflater;
    private LinearLayout mGallery;
    private Bitmap[] mImgIds;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(getContext());
        view = inflater.inflate(R.layout.personaldata_second_tab, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        PersonalRealnameUpdata = (Button) view.findViewById(R.id.PersonalRealnameUpdata);
        PersonalRealnameUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "上传已确认", Toast.LENGTH_LONG).show();
            }
        });

        SelectFile = (Button) view.findViewById(R.id.SelectFile);
        SelectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(Intent.ACTION_PICK, null);
                int1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(int1, 1);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //图库
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
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

            /*//压缩，用于节省BITMAP内存空间--解决BUG的关键步骤
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = 2;    //这个的值压缩的倍数（2的整数倍），数值越小，压缩率越小，图片越清晰

            //返回原图解码之后的bitmap对象
            initData(BitmapFactory.decodeFile(picturePath,opts));*/
            //显示在Gallery视图
//            initData(BitmapFactory.decodeFile(picturePath));

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    //照片的显示
    private void initData(Bitmap per_photos) {
        mImgIds = new Bitmap[]{per_photos};
    }

    private void initView() {
        mGallery = (LinearLayout) view.findViewById(R.id.person_gallery);
        for (int i = 0; i < mImgIds.length; i++) {
            final View personalphoto_item = mInflater.inflate(R.layout.personalphoto_item,
                    mGallery, false);
            final ImageView img = (ImageView) personalphoto_item.findViewById(R.id.per_photos);
            img.setImageBitmap(mImgIds[i]);
            mGallery.addView(personalphoto_item);
            //删除照片
            ImageView remove = (ImageView) personalphoto_item.findViewById(R.id.remove_perimg);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGallery.removeView(personalphoto_item);
                }
            });
        }
    }
}
