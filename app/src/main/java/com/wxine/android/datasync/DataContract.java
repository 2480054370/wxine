package com.wxine.android.datasync;

import android.provider.BaseColumns;

/**
 * Created by zhoubing on 15/10/30.
 */
public final class DataContract {

    private DataContract() {}

    public static abstract class Info implements BaseColumns {
        public static final String TABLE_NAME = "info";
        public static final String COLUMN_NAME_INFO_ID = "id";
        public static final String COLUMN_NAME_INFO_USER = "user";
        public static final String COLUMN_NAME_INFO_COMMUNITY = "community";
        public static final String COLUMN_NAME_INFO_COURSE = "course";
        public static final String COLUMN_NAME_INFO_EVENT = "event";
        public static final String COLUMN_NAME_INFO_GOODS = "goods";
        public static final String COLUMN_NAME_INFO_TASK = "task";
        public static final String COLUMN_NAME_INFO_SCOPE = "scope";
        public static final String COLUMN_NAME_INFO_CMCATE = "cmcate";
        public static final String COLUMN_NAME_INFO_NAME = "name";
        public static final String COLUMN_NAME_INFO_TITLE = "title";
        public static final String COLUMN_NAME_INFO_IMAGE = "image";
        public static final String COLUMN_NAME_INFO_TAG = "tag";
        public static final String COLUMN_NAME_INFO_MARKDOWN = "markdown";
        public static final String COLUMN_NAME_INFO_CONTENT = "content";
        public static final String COLUMN_NAME_INFO_CSORT = "csort";
        public static final String COLUMN_NAME_INFO_STATUS = "status";
        public static final String COLUMN_NAME_INFO_ILIKE = "ilike";
        public static final String COLUMN_NAME_INFO_CMCOUNT = "cmcount";
        public static final String COLUMN_NAME_INFO_SHARECOUNT = "sharecount";
        public static final String COLUMN_NAME_INFO_READCOUNT = "readcount";
        public static final String COLUMN_NAME_INFO_DISTRICT = "district";
        public static final String COLUMN_NAME_INFO_ADDRESS = "address";
        public static final String COLUMN_NAME_INFO_LATITUDE = "latitude";
        public static final String COLUMN_NAME_INFO_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_INFO_CTIME = "ctime";
        public static final String COLUMN_NAME_INFO_UTIME = "utime";
        public static final String COLUMN_NAME_INFO_SYNC = "sync";
    }

    /*public static abstract class Community implements BaseColumns {
        public static final String TABLE_NAME = "league";
        public static final String COLUMN_NAME_SPORT_ID = "sport_id";
        public static final String COLUMN_NAME_LEAGUE_NAME = "name";
    }

    public static abstract class Team implements BaseColumns {
        public static final String TABLE_NAME = "team";
        public static final String COLUMN_NAME_LEAGUE_ID = "league_id";
        public static final String COLUMN_NAME_TEAM_NAME = "name";
    }*/

}
