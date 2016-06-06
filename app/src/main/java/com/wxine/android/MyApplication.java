package com.wxine.android;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.wxine.android.model.User;
import com.wxine.android.utils.HttpCookieStore;

import java.io.File;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

/**
 * Created by zhoubing on 15/10/20.
 */
public class MyApplication extends Application {
    public static final String SERVER = "http://10.1.29.117:8080/";
    public static final String ACTION_LOGIN = "login";
    public static final String ACTION_REGIST = "regist";

    private static final String TAG = MyApplication.class.getSimpleName();
    public static SharedPreferences preferences;
    private static String sessionId = null;
    private static User sessionUser = null;
    private static String account = null;
    private static String password = null;
    public static boolean login = true;
    private static boolean certed = false;

    public static void saveUser(User sessionUser, String account, String password) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("sessionId", sessionUser.getSessionid());
        editor.putString("account", account);
        editor.putString("password", password);
        editor.putString("name", sessionUser.getName());
        editor.putString("image", sessionUser.getImage());
        editor.putString("sessionUser", sessionUser.getJson());
        editor.commit();
    }

    public static User getSessionUser() {
        String userjson = preferences.getString("user", "{}");
        sessionUser = JSON.parseObject(userjson, User.class, Feature.AllowISO8601DateFormat);
        return sessionUser;
    }

    public static void setSessionUser(User sessionUser) {
        MyApplication.sessionUser = sessionUser;
    }

    public static String getSessionId() {
        return sessionId;
    }

    public static void setSessionId(String sessionId) {
        MyApplication.sessionId = sessionId;
    }

    public static String getAccount() {
        account = preferences.getString("account", "");
        return account;
    }

    public static void setAccount(String account) {
        MyApplication.account = account;
    }

    public static String getPassword() {
        password = preferences.getString("password", "");
        return password;
    }

    public static void setPassword(String password) {
        MyApplication.password = password;
    }

    public static boolean isLogin() {
        return login;
    }

    public static void setLogin(boolean login) {
        MyApplication.login = login;
    }

    public static boolean isCerted() {
        return certed;
    }

    public static void setCerted(boolean certed) {
        MyApplication.certed = certed;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init_image(getApplicationContext());
        CookieHandler.setDefault(new CookieManager(new HttpCookieStore(this), CookiePolicy.ACCEPT_ALL));

        preferences = getSharedPreferences("profile", Context.MODE_PRIVATE);
        if (!preferences.contains("sessionUser")) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("sessionUser", new User().getJson());
            editor.commit();
            Log.v(TAG, "初始化sessionUser。");
        }

        initImageLoader(getApplicationContext());
    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    public static void init_image(Context context) {
        File cacheDir = StorageUtils.getCacheDirectory(context);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                //.memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                //.diskCacheExtraOptions(480, 800, null)
                //.taskExecutor(...)
                //.taskExecutorForCachedImages(...)
                //.threadPoolSize(3) // default
                //.threadPriority(Thread.NORM_PRIORITY - 2) // default
                //.tasksProcessingOrder(QueueProcessingType.FIFO) // default
                //.denyCacheImageMultipleSizesInMemory()
                //.memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                //.memoryCacheSize(2 * 1024 * 1024)
                //.memoryCacheSizePercentage(13) // default
                //.diskCache(new UnlimitedDiskCache(cacheDir)) // default
                //.diskCacheSize(50 * 1024 * 1024)
                //.diskCacheFileCount(100)
                //.diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                //.imageDownloader(new BaseImageDownloader(context)) // default
                //.imageDecoder(new BaseImageDecoder()) // default
                //.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                //.writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }
}
