package com.wxine.android.album;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wxine.android.R;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity implements View.OnClickListener {
    private final ImageLoader imageLoader = ImageLoader.getInstance();
    private GridView gridView;
    private TextView back,ok;
    //private PhotoUpImageBucket photoUpImageBucket;
    private ArrayList<PhotoUpImageItem> selectImages;
    private AlbumItemAdapter adapter;

    //private PhotoUpAlbumHelper photoUpAlbumHelper;//加载相册和图片的异步线程类
    //private List<PhotoUpImageBucket> list;//存放相册列表数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
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

        init();
        setListener();
    }

    private void init(){
        gridView = (GridView) findViewById(R.id.gv_album);
        back = (TextView) findViewById(R.id.back);
        ok = (TextView) findViewById(R.id.sure);
        selectImages = new ArrayList<PhotoUpImageItem>();

        Intent intent = getIntent();
        PhotoUpImageBucket photoUpImageBucket = (PhotoUpImageBucket) intent.getSerializableExtra("imagelist");////////
        adapter = new AlbumItemAdapter(photoUpImageBucket.getImageList(), AlbumActivity.this);
        gridView.setAdapter(adapter);
    }

    private void loadData(){
        PhotoUpAlbumHelper photoUpAlbumHelper = PhotoUpAlbumHelper.getHelper();//创建异步线程实例
        photoUpAlbumHelper.init(AlbumActivity.this);//初始化实例
        //回调接口，创建匿名内部对象，实现接口中的方法，获取到PhotoUpAlbumHelper的接口GetAlbumList所传递的数据
        photoUpAlbumHelper.setGetAlbumList(new PhotoUpAlbumHelper.GetAlbumList() {
            @Override
            public void getAlbumList(List<PhotoUpImageBucket> list) {
                //adapter.setArrayList(list);
                adapter.notifyDataSetChanged();//更新视图
                //AlbumActivity.this.list = list;
            }
        });
        photoUpAlbumHelper.execute(false);//异步线程执行
    }

    private void setListener(){
        back.setOnClickListener(this);
        ok.setOnClickListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                CheckBox checkBox = (CheckBox) view.findViewById(R.id.check);
                //photoUpImageBucket.getImageList().get(position).setSelected(
                //        !checkBox.isChecked());
                adapter.notifyDataSetChanged();

                /*if (photoUpImageBucket.getImageList().get(position).isSelected()) {
                    if (selectImages.contains(photoUpImageBucket.getImageList().get(position))) {

                    }else {
                        selectImages.add(photoUpImageBucket.getImageList().get(position));
                    }
                }else {
                    if (selectImages.contains(photoUpImageBucket.getImageList().get(position))) {
                        selectImages.remove(photoUpImageBucket.getImageList().get(position));
                    }else {

                    }
                }*/
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.sure:
                //Intent intent = new Intent(AlbumActivity.this,SelectedImagesActivity.class);
                //intent.putExtra("selectIma", selectImages);
                //startActivity(intent);
                break;
            default:
                break;
        }
    }

    public class AlbumItemAdapter extends BaseAdapter {
        private List<PhotoUpImageItem> list;//存放所选相册里面的图片列表
        private LayoutInflater layoutInflater;

        public AlbumItemAdapter(List<PhotoUpImageItem> list,Context context){
            this.list = list;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.activity_album_item, parent, false);
                holder = new Holder();
                holder.imageView = (ImageView) convertView.findViewById(R.id.image_item);
                holder.checkBox = (CheckBox) convertView.findViewById(R.id.check);
                convertView.setTag(holder);
            }else {
                holder = (Holder) convertView.getTag();
            }
            //图片加载器的使用代码，就这一句代码即可实现图片的加载。请注意
            //这里的uri地址，因为我们现在实现的是获取本地图片，所以使
            //用"file://"+图片的存储地址。如果要获取网络图片，
            //这里的uri就是图片的网络地址。
            imageLoader.displayImage("file://"+list.get(position).getImagePath(), holder.imageView);
            holder.checkBox.setChecked(list.get(position).isSelected());
            return convertView;
        }

        class Holder{
            ImageView imageView;
            CheckBox checkBox;
        }
    }
}
