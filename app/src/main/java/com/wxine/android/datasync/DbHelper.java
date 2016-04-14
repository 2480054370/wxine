package com.wxine.android.datasync;

/**
 * Created by zhoubing on 15/10/30.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.wxine.android.model.Info;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "wxine.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS `info` (" +
                    "  `id` varchar(32) NOT NULL," +
                    "  `user` varchar(32) DEFAULT NULL," +
                    "  `community` varchar(32) DEFAULT NULL," +
                    "  `course` varchar(32) DEFAULT NULL," +
                    "  `event` varchar(32) DEFAULT NULL," +
                    "  `goods` varchar(32) DEFAULT NULL," +
                    "  `task` varchar(32) DEFAULT NULL," +
                    "  `scope` varchar(255) NOT NULL," +
                    "  `friend` text," +
                    "  `topic` varchar(255) DEFAULT NULL," +
                    "  `cmcate` varchar(255) DEFAULT NULL," +
                    "  `type` varchar(255) NOT NULL," +
                    "  `name` varchar(255) DEFAULT NULL," +
                    "  `title` varchar(255) DEFAULT NULL," +
                    "  `image` text," +
                    "  `tag` text," +
                    "  `markdown` text," +
                    "  `content` longtext NOT NULL," +
                    "  `csort` int(11) NOT NULL," +
                    "  `status` varchar(255) NOT NULL," +
                    "  `ilike` int(11) NOT NULL," +
                    "  `cmcount` int(11) NOT NULL," +
                    "  `sharecount` int(11) NOT NULL," +
                    "  `readcount` int(11) NOT NULL," +
                    "  `district` varchar(255) DEFAULT NULL," +
                    "  `address` varchar(255) DEFAULT NULL," +
                    "  `latitude` double DEFAULT NULL," +
                    "  `longitude` double DEFAULT NULL," +
                    "  `ctime` datetime NOT NULL," +
                    "  `utime` datetime DEFAULT NULL," +
                    "  `sync` text," +
                    "  PRIMARY KEY (`id`)" +
                    ");";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataContract.Info.TABLE_NAME;

    private final SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onDowngrade(db, oldVersion, newVersion);
    }

    /**
     * Inserts Info into SQLite DB
     * @param value
     */
    public void insertInfo(Info value) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataContract.Info.COLUMN_NAME_INFO_ID, value.getId());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getUser().getId());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getCmcate().getId());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getCommunity().getId());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getCourse().getId());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getEvent().getId());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getGoods().getId());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getTask().getId());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getScope());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getName());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getTitle());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getImage());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getTag());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getMarkdown());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getContent());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getCsort());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getStatus());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getIlike());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getCmcount());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getSharecount());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getReadcount());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getDistrict());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getAddress());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getLatitude());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getLongitude());
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, parser.format(value.getCtime()));
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, parser.format(value.getUtime()));
        values.put(DataContract.Info.COLUMN_NAME_INFO_USER, value.getSync());
        database.insert(DataContract.Info.TABLE_NAME, null, values);
        database.close();
    }

    /**
     * Get list of Infos from SQLite DB as Array List
     * @return
     */
    public ArrayList<HashMap<String, String>> getAllInfos() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM users";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("userId", cursor.getString(0));
                map.put("userName", cursor.getString(1));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }

    /**
     * Compose JSON out of SQLite records
     * @return
     */
    public String composeJSONfromSQLite(){
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM users where udpateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("userId", cursor.getString(0));
                map.put("userName", cursor.getString(1));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return JSON.toJSONString(wordList);
    }

    /**
     * Get Sync status of SQLite
     * @return
     */
    public String getSyncStatus(){
        String msg = null;
        if(this.dbSyncCount() == 0){
            msg = "SQLite and Remote MySQL DBs are in Sync!";
        }else{
            msg = "DB Sync neededn";
        }
        return msg;
    }

    /**
     * Get SQLite records that are yet to be Synced
     * @return
     */
    public int dbSyncCount(){
        int count = 0;
        String selectQuery = "SELECT  * FROM users where udpateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }

    /**
     * Update Sync status against each User ID
     * @param id
     * @param status
     */
    public void updateSyncStatus(String id, String status){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "Update info set udpateStatus = '"+ status +"' where userId="+"'"+ id +"'";
        Log.d("query", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
