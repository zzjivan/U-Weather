package com.app.zzj.u_weather.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

/**
 * Created by sedwt on 2016/10/13.
 */
public class CityProvider extends ContentProvider {
    public static final String AUTHORITY = "com.app.zzj.u_weather.Data.CityProvider";
    private static final String TABLE_CITY = "T_city";

    private static final String[] CITY_ONLY = {"id", "name"};

    private DataBaseHelper dataBaseHelper;
    @Override
    public boolean onCreate() {
        dataBaseHelper = new DataBaseHelper(getContext());
        try {
            dataBaseHelper.CreateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataBaseHelper != null ? true : false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dataBaseHelper.openDataBase();
        Cursor c = db.query(TABLE_CITY, projection, selection, null, null, null,sortOrder);
        Log.d("zzj","query");
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
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    public static class CityColumns implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://"+ AUTHORITY +"/"+TABLE_CITY);
        public final static String ID = "id";
        public final static String NAME = "name";
        public final static String POSTCODE = "postcode";
        public final static String PROVINCE_ID = "province_id";
        public static int get_id_column(){return 0;}
        public static int get_name_column(){return 1;}
        public static int get_postcode_column(){return 2;}
        public static int get_province_column(){return 3;}
    }
}
