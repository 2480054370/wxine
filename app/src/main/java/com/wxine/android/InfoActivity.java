package com.wxine.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wxine.android.model.Comment;
import com.wxine.android.model.Info;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {
    private final ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_info);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.comments_recycler_view);
        //mRecyclerView.setHasFixedSize(true);

        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        //mRecyclerView.setLayoutManager(mLayoutManager);
        final LinearLayout commentLayout = (LinearLayout)findViewById(R.id.comment_layout);
        ImageView user_logo = (ImageView)findViewById(R.id.iv_user_logo);
        ImageView agree_img = (ImageView)findViewById(R.id.iv_info_agree_img);
        TextView user_name = (TextView)findViewById(R.id.tv_user_name);
        TextView info_content = (TextView)findViewById(R.id.tv_info_content);
        ImageView info_image = (ImageView)findViewById(R.id.iv_info_image);
        ImageView info_share_img = (ImageView) findViewById(R.id.iv_share_img);
        TextView info_comment_num = (TextView)findViewById(R.id.info_comment_num);
        ImageView info_comment_img = (ImageView)findViewById(R.id.iv_info_comment);
        RecyclerView comments_recycler_view = (RecyclerView)findViewById(R.id.comments_recycler_view);
        comments_recycler_view.setHasFixedSize(true);
        FullyLinearLayoutManager mLayoutManager = new FullyLinearLayoutManager(this);
        comments_recycler_view.setLayoutManager(mLayoutManager);

        Info info = (Info)getIntent().getSerializableExtra("info");
        String tag = getIntent().getStringExtra("tag");
        imageLoader.displayImage(info.getUser().getImage(), user_logo);
        user_name.setText(info.getUser().getName());
        info_content.setText(info.getCleancontent());
        imageLoader.displayImage(info.getImage(), info_image);
        info_comment_num.setText(String.valueOf(info.getComments().size()));
        Log.i("InfoActivity.this","aaa"+tag);

        // comment
        if(getIntent().getBooleanExtra("IsComment",false)){
            commentLayout.setVisibility(View.VISIBLE);
        }else{
            commentLayout.setVisibility(View.GONE);
        }

        if(tag.equals("no")){
            agree_img.setImageResource(R.drawable.xfavorite);
        }else{
            agree_img.setImageResource(R.drawable.favorite);
        }

        info_share_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showPopwindow();
            }
        });

        info_comment_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentLayout.setVisibility(View.VISIBLE);
            }
        });

        final CommentsAdapter mAdapter = new CommentsAdapter(this, new ArrayList<Comment>(info.getComments()));
        //Log.v("==height======", String.valueOf(0));
        comments_recycler_view.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 显示popupWindow
     */
    public void showPopwindow() {
        // 利用layoutInflater获得View
        //   LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //   View view = inflater.inflate(R.layout.popwindowlayout, null);
        LayoutInflater inflater =(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popwindowlayout, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);


        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        this.getWindow().setAttributes(lp);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(findViewById(R.id.iv_share_img),
                Gravity.BOTTOM, 0, 0);

        RelativeLayout OtherShare = (RelativeLayout) view.findViewById(R.id.OtherShareLayout);
        OtherShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                window.dismiss();
            }
        });
//        TextView OtherShare = (TextView) view.findViewById(R.id.OtherShareText);
//        OtherShare.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//                sendIntent.setType("text/plain");
//                startActivity(sendIntent);
//            }
//        });

        //popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                System.out.println("popWindow消失");
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (item.getItemId() == android.R.id.home){
//            finish();
//            return true ;
//        }
        return super.onOptionsItemSelected(item);
    }


}
