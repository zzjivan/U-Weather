package com.app.zzj.u_weather.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;


/**
 * Created by sedwt on 2016/10/13.
 */
public class CityProvider extends ContentProvider {
    public static final String AUTHORITY = "com.app.zzj.u_weather.Data.CityProvider";
    private static final String TABLE_CITY = "T_city"; //中国城市列表
    private static final String TABLE_PRESENT_CITY = "P_city";

    public static final Uri CONTENT_URI_CITY = Uri.parse("content://" + AUTHORITY  + "/" + TABLE_CITY);
    public static final Uri CONTENT_URI_PRESENT_CITY = Uri.parse("content://" + AUTHORITY  + "/" + TABLE_PRESENT_CITY);

    private static final int TABLE_CITY_MSG = 0;
    private static final int TABLE_PRESENT_CITY_MSG = 1;

    private static final String[] CITY_ONLY = {"id", "name"};

    private DataBaseHelper dataBaseHelper;

    private static final UriMatcher URI_MATCHER;
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, TABLE_CITY, TABLE_CITY_MSG);
        URI_MATCHER.addURI(AUTHORITY, TABLE_PRESENT_CITY, TABLE_PRESENT_CITY_MSG);

    }

    @Override
    public boolean onCreate() {
        dataBaseHelper = new DataBaseHelper(getContext());
        return dataBaseHelper != null ? true : false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dataBaseHelper.openDataBase();
        Cursor c;
        String table = "";
        switch(URI_MATCHER.match(uri)) {
            case TABLE_CITY_MSG:
                table = TABLE_CITY;
                break;
            case TABLE_PRESENT_CITY_MSG:
                table = TABLE_PRESENT_CITY;
                break;
            default:
                table = TABLE_PRESENT_CITY;
                break;
        }
         c = db.query(table, projection, selection, null, null, null,sortOrder);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dataBaseHelper.openDataBase();
        String table = "";
        switch(URI_MATCHER.match(uri)) {
            case TABLE_CITY_MSG:
                table = TABLE_CITY;
                break;
            case TABLE_PRESENT_CITY_MSG:
                table = TABLE_PRESENT_CITY;
                break;
            default:
                table = TABLE_PRESENT_CITY;
                break;
        }
        db.insert(table, null, values);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dataBaseHelper.openDataBase();
        String table = "";
        switch(URI_MATCHER.match(uri)) {
            case TABLE_CITY_MSG:
                table = TABLE_CITY;
                break;
            case TABLE_PRESENT_CITY_MSG:
                table = TABLE_PRESENT_CITY;
                break;
            default:
                table = TABLE_PRESENT_CITY;
                break;
        }
        return db.delete(table, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dataBaseHelper.openDataBase();
        String table = "";
        switch(URI_MATCHER.match(uri)) {
            case TABLE_CITY_MSG:
                table = TABLE_CITY;
                break;
            case TABLE_PRESENT_CITY_MSG:
                table = TABLE_PRESENT_CITY;
                break;
            default:
                table = TABLE_PRESENT_CITY;
                break;
        }
        return db.update(table,values,selection,selectionArgs);
    }

    public static class CityColumns implements BaseColumns {
        public final static String ID = "id";
        public final static String NAME = "name";
        public final static String POSTCODE = "postcode";
        public final static String PROVINCE_ID = "province_id";
        public static int get_id_column(){return 0;}
        public static int get_name_column(){return 1;}
        public static int get_postcode_column(){return 2;}
        public static int get_province_column(){return 3;}
    }

    public static class PresentCityColumns implements BaseColumns {
        public final static String CITY_NAME = "city_name";
        public final static String UPDATE_TIME = "update_time";
        public final static String CURRENT_TEMPERATURE = "current_temperature";
        public final static String CURRENT_HUMIDITY = "current_humidity";
        public final static String CLOTH = "cloth";
        public final static String SICK = "sick";
        public final static String AIRCONDITIONER = "airconditioner";
        public final static String WASHINGCAR = "washingcar";
        public final static String SPORTS = "sports";
        public final static String ULTRAVIOLET = "ultraviolet";
        public final static String PM25 = "pm25";
        public final static String LUNAR = "lunar";
        public final static String DATE = "date";
        public final static String TOMORROW_TEMPERATURE = "tomorrow_temperature";
        public final static String REMAIN1 = "remain1";
        public final static String REMAIN2 = "remain2";
        public final static String REMAIN3 = "remain3";



    }
}
