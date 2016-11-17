package com.app.zzj.u_weather.Data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.app.zzj.u_weather.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sedwt on 2016/10/13.
 */
public class DataBaseHelper {
    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.app.zzj.u_weather/databases/";
    private static String DB_NAME = "city.db";
    private SQLiteDatabase myDataBase;
    private Context context;


    public DataBaseHelper(Context context) {
        this.context = context;
        try {
            createDataBase();
            openDataBase();
            String sql = "create table if not exists P_city "+
                    "("+
                    "_id integer primary key autoincrement,"+
                    "city_name text,"+
                    "update_time long,"+
                    "current_temperature text,"+
                    "current_humidity text,"+
                    "cloth text,"+
                    "sick text,"+
                    "airconditioner text,"+
                    "washingcar text,"+
                    "sports text,"+
                    "ultraviolet text,"+
                    "pm25 text,"+
                    "lunar text,"+
                    "date text,"+
                    "tomorrow_temperature text,"+
                    "remain1 text,"+
                    "remain2 text,"+
                    "remain3 text"+
                    ")";
            myDataBase.execSQL(sql);
            closeDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized SQLiteDatabase openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        return myDataBase;
    }

    public synchronized void closeDataBase() {
        if(myDataBase != null)
            myDataBase.close();
    }

    private void createDataBase() throws IOException {
        if(!checkDataBase())
            copyDataBase();
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            //database does't exist yet.
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
        File file = new File(DB_PATH);
        if(!file.exists())
            file.mkdir();
        //Open your local db as the input stream
        InputStream myInput = context.getResources().openRawResource(R.raw.china_city_name);
        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;
        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
}
