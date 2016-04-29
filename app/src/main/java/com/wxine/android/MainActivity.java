package com.wxine.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,//系统自带item侧滑菜单监听
        CollectionFragment.OnFragmentInteractionListener,
        InfosFragment.OnFragmentInteractionListener,
        CommunitiesFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        ProfileUncertFragment.OnFragmentInteractionListener,
        NotifacationFragment.OnFragmentInteractionListener,
        NotifacationReadFragment.OnFragmentInteractionListener,
        View.OnClickListener {
    private RelativeLayout content_layout;
    private RelativeLayout Collection_layout;
    private RelativeLayout group_layout;
    private RelativeLayout notice_layout;
    private ImageView content_image;
    private ImageView Collection_image;
    private ImageView group_image;

    private ImageView notice_image;
    private TextView content_text;
    private TextView Collection_text;
    private TextView group_text;
    private TextView notice_text;
    private int white = 0xFFFFFFFF;
    private int gray = 0xFF979797;
    private Fragment fg1, fg2, fg3;
    public static Fragment fg4;
    public static Fragment fg5;
    private boolean frag = false;
    FragmentManager fManager;


    private ImageView im;
    private Toolbar toolbar;
    private boolean viewIsAtHome;
    private MyApplication app;
    private final ImageLoader imageLoader = ImageLoader.getInstance();
    private LinearLayout nav;
    private ImageView open;
    private Animation rotate;
    FloatingActionButton fab;
    DrawerLayout drawer;

    Timer timer = new Timer();
    TimerTask task;

    CoordinatorLayout status;   //状态栏颜色


//    public  interface ButtonClickListener{
//        void buttonClick();
//    }

    public Fragment getNotiReadFra() {
        return fg5;
    }

    public Fragment getNotiFra() {
        return fg4;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (MyApplication) getApplication();
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        //状态栏颜色
        status = (CoordinatorLayout) findViewById(R.id.mainCoordLayoutStatus);
        status.setStatusBarBackgroundColor(getResources().getColor(R.color.barmain));

        fManager = getSupportFragmentManager();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(MainActivity.this, PublishActivity.class);
                startActivity(intent);
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        View nav_header = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        ImageView user_logo = (ImageView) nav_header.findViewById(R.id.iv_user_logo);
        TextView user_name = (TextView) nav_header.findViewById(R.id.tv_user_name);
        TextView user_sign = (TextView) nav_header.findViewById(R.id.tv_user_sign);

        im = (ImageView) findViewById(R.id.iv_user_logo);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Bar_Personal.class);
                startActivity(intent);
            }
        });
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        nav = (LinearLayout) findViewById(R.id.nav_background);
        open = (ImageView) findViewById(R.id.open);
        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu menu = navigationView.getMenu();
                if (menu.findItem(R.id.nav_profile) != null) {
                    rotate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tip);
                    rotate.setFillAfter(true);
                    open.startAnimation(rotate);
                    menu.clear();
                    navigationView.inflateMenu(R.menu.activity_next_drawer);
                } else {
                    rotate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tip_next);
                    rotate.setFillAfter(true);
                    open.startAnimation(rotate);
                    menu.clear();
                    navigationView.inflateMenu(R.menu.activity_main_drawer);
                }

            }
        });

        imageLoader.displayImage(app.getSessionUser().getImage(), user_logo);
        user_name.setText(app.getSessionUser().getName());
        user_sign.setText(app.getSessionUser().getIntro());

    }

    //完成组件的初始化
    public void initViews() {
        content_image = (ImageView) findViewById(R.id.content_image);
        group_image = (ImageView) findViewById(R.id.group_image);
        Collection_image = (ImageView) findViewById(R.id.Collection_image);
        notice_image = (ImageView) findViewById(R.id.notice_image);
        content_text = (TextView) findViewById(R.id.content_text);
        group_text = (TextView) findViewById(R.id.group_text);
        Collection_text = (TextView) findViewById(R.id.Collection_text);
        notice_text = (TextView) findViewById(R.id.notice_text);
        content_layout = (RelativeLayout) findViewById(R.id.content_layout);
        Collection_layout = (RelativeLayout) findViewById(R.id.Collection_layout);
        group_layout = (RelativeLayout) findViewById(R.id.group_layout);
        notice_layout = (RelativeLayout) findViewById(R.id.notice_layout);
        content_layout.setOnClickListener(this);
        Collection_layout.setOnClickListener(this);
        group_layout.setOnClickListener(this);
        notice_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_layout:
                displayView(0);
                break;
            case R.id.Collection_layout:
                displayView(1);
                break;
            case R.id.group_layout:
                displayView(2);
                break;
            case R.id.notice_layout:
                displayView(3);
                break;
            default:
                break;
        }
    }

    private void hideFragments(FragmentTransaction transaction) {

        if (fg1 != null) {
            transaction.hide(fg1);
        }
        if (fg2 != null) {
            transaction.hide(fg2);
        }
        if (fg3 != null) {
            transaction.hide(fg3);
        }
        if (fg4 != null) {
            transaction.hide(fg4);
        }
        if (fg5 != null) {
            transaction.hide(fg5);
        }
    }


    //定义一个重置所有选项的方法iron

    public void clearChioce() {
        content_image.setImageResource(R.drawable.ic_home_normal);
        content_text.setTextColor(gray);
        group_image.setImageResource(R.drawable.ic_group_normal);
        group_text.setTextColor(gray);
        Collection_image.setImageResource(R.drawable.ic_collect_normal);
        Collection_text.setTextColor(gray);
        notice_image.setImageResource(R.drawable.ic_notice_normal);
        notice_text.setTextColor(gray);
    }

    long waitTime = 3000;
    long touchTime = 0;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                Snackbar.make(fab, "再按一次退出", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                touchTime = currentTime;
            } else {
                finish();
            }

            //super.onBackPressed();
        }
        //if (!viewIsAtHome) { //if the current view is not the News fragment
        //    displayView(R.id.nav_camara); //display the News fragment
        //} else {
        //    moveTaskToBack(true);  //If view is in News fragment, exit application
        //}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Intent ite = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(ite);
                return true;
            case R.id.action_search:
                Intent it = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(it);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        /*int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_camara) {
            fragment = new InfosFragment();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        displayView(item.getItemId());
        return true;
    }

    public void displayView(int viewId) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        //重置选项+隐藏所有Fragment
        FragmentTransaction transaction = fManager.beginTransaction();
        //动画效果transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        clearChioce();
        hideFragments(transaction);
        switch (viewId) {
            case R.id.nav_location:
                fab.hide();
                //startActivity(new Intent(MainActivity.this, LocationActivity.class));
                task = new TimerTask() {
                    @Override
                    public void run() {
                        Intent InLocation = new Intent(MainActivity.this, LocationActivity.class);
                        startActivity(InLocation);
                    }
                };
                timer.schedule(task, 300 * 1);
                viewIsAtHome = false;
                break;
            case R.id.nav_friends:
                fab.hide();
                //startActivity(new Intent(MainActivity.this, FriendsActivity.class));
                //页面从右进，从左退出
                //overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);

                task = new TimerTask() {
                    @Override
                    public void run() {
                        Intent InFriends = new Intent(MainActivity.this, FriendsActivity.class);
                        startActivity(InFriends);
                    }
                };
                timer.schedule(task, 300 * 1);
                viewIsAtHome = false;
                break;
            case R.id.nav_photos:
                fab.hide();
                //startActivity(new Intent(MainActivity.this, FriendsActivity.class));
                //页面从右进，从左退出
                //overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);

                task = new TimerTask() {
                    @Override
                    public void run() {
                        final Intent InPhotos = new Intent(MainActivity.this, PhotosActivity.class);
                        startActivity(InPhotos);
                    }
                };
                timer.schedule(task, 300 * 1);
                viewIsAtHome = false;
                break;

            case R.id.nav_task:
                //startActivity(new Intent(MainActivity.this, EventActivity.class));
                task = new TimerTask() {
                    @Override
                    public void run() {
                        Intent InEvent = new Intent(MainActivity.this, EventActivity.class);
                        startActivity(InEvent);
                    }
                };
                timer.schedule(task, 300 * 1);
                viewIsAtHome = true;

                break;

            case R.id.nav_profile:
                fab.hide();
                if (app.isCerted()) {//判断是否认证
                    //Intent i = new Intent(MainActivity.this, PersonalData.class);
                    //startActivity(i);
                    task = new TimerTask() {
                        @Override
                        public void run() {
                            Intent InPersonal = new Intent(MainActivity.this, PersonalData.class);
                            startActivity(InPersonal);
                        }
                    };
                } else {
                    //Intent i = new Intent(MainActivity.this, PersonalData.class);
                    //startActivity(i);
                    task = new TimerTask() {
                        @Override
                        public void run() {
                            Intent InPersonal = new Intent(MainActivity.this, PersonalData.class);
                            startActivity(InPersonal);
                        }
                    };
                }
                timer.schedule(task, 300 * 1);
                break;
            case 0:
                //状态栏颜色
                status.setStatusBarBackgroundColor(getResources().getColor(R.color.barmain));
                fab.show();
                title = "首页";
                clearChioce();
                hideFragments(transaction);
                toolbar.setBackgroundColor(0xFFDA4436);
                content_image.setImageResource(R.drawable.ic_home_pressed);
                content_text.setTextColor(white);
                if (fg1 == null) {
                    fg1 = new InfosFragment();
                    transaction.add(R.id.content, fg1);
                } else {
                    transaction.show(fg1);
                }
                break;
            case 1:
                //状态栏颜色
                status.setStatusBarBackgroundColor(getResources().getColor(R.color.barcoll));
                fab.hide();
                clearChioce();
                hideFragments(transaction);
                toolbar.setBackgroundColor(0xFF01A9F2);
                title = "课程";
                /*Collection_image.setImageResource(R.drawable.ic_collect_pressed);
                Collection_text.setTextColor(white);
                if (fg2 == null) {
                    fg2 = new CollectionFragment();
                    transaction.add(R.id.content, fg2);
                } else {
                    transaction.show(fg2);
                }*/
                break;
            case 2:
                //状态栏颜色
                status.setStatusBarBackgroundColor(getResources().getColor(R.color.subpage));
                fab.hide();
                clearChioce();
                hideFragments(transaction);
                toolbar.setBackgroundColor(0xFF0F9D58);
                title = "社群";
                group_image.setImageResource(R.drawable.ic_group_pressed);
                group_text.setTextColor(white);
                if (fg3 == null) {
                    fg3 = new CommunitiesFragment();
                    transaction.add(R.id.content, fg3);
                } else {
                    transaction.show(fg3);
                }
                break;
            case 3:
                //状态栏颜色
                status.setStatusBarBackgroundColor(getResources().getColor(R.color.barnotice));
                fab.hide();
                clearChioce();
                hideFragments(transaction);
                toolbar.setBackgroundColor(0xFFDA4436);
                title = "通知";
                notice_image.setImageResource(R.drawable.ic_notice_pressed);
                notice_text.setTextColor(white);
                if (!frag) {
                    fg4 = new NotifacationFragment();
                    fg5 = new NotifacationReadFragment();
//                    if(getFragmentManager() instanceof ButtonClickListener)
//                        ((ButtonClickListener)getFragmentManager()).buttonClick();
//                    Bundle bundle = new Bundle();
//                    fg4.setArguments(bundle);
                    transaction.add(R.id.content, fg4);
                    frag = true;
                } else {
                    transaction.show(fg4);
                }
                break;
        }
        transaction.commit();
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();    // FragmentTransaction对fragment进行添加,移除,替换,以及执行其他动作。
            ft.replace(R.id.content_frame, fragment);
            //ft.commit();
            ft.commitAllowingStateLoss();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //Bundle b = data.getExtras();
                //app.sessionId = b.getString("sessionid");
                Log.v("login:", "登录成功" + app.getSessionId());
                displayView(0);
            }
        }
    }

}
