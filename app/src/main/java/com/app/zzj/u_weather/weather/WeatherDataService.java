package com.app.zzj.u_weather.weather;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.app.zzj.u_weather.API.ApiManager;
import com.app.zzj.u_weather.API.Entity.Weather;
import com.app.zzj.u_weather.Data.CityProvider;
import com.app.zzj.u_weather.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.PinyinTool;
import aidl.IWeatherDataInterface;

/**
 * Created by sedwt on 2016/11/15.
 */
public class WeatherDataService extends Service {
    private final int WEATHER_UPDATE_COMPLETE = 1;

    private List<String> all_chinese_cities = new ArrayList<String>();
    private Map<String, Weather> cityWeather = new HashMap<String, Weather>();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WEATHER_UPDATE_COMPLETE:
                    Weather weather = (Weather) msg.obj;
                    cityWeather.put(weather.getResult().getData().getRealtime().getCity_name(), weather);
                    //TODO:需要通知UI更新
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
//        showcityList =
//        for(String city : showcityList) {
//            updateWeather(city);
//        }
        if(all_chinese_cities.size() == 0)
            new CityLoder().execute();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private final IWeatherDataInterface.Stub mBinder = new IWeatherDataInterface.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getCurrentTemperature() throws RemoteException {
            return null;
        }

        @Override
        public String getCurrentHumidity() throws RemoteException {
            return null;
        }

        @Override
        public String getCloth() throws RemoteException {
            return null;
        }

        @Override
        public String getSick() throws RemoteException {
            return null;
        }

        @Override
        public String getAirConditioner() throws RemoteException {
            return null;
        }

        @Override
        public String getWashingCar() throws RemoteException {
            return null;
        }

        @Override
        public String getSports() throws RemoteException {
            return null;
        }

        @Override
        public String getUltraviolet() throws RemoteException {
            return null;
        }

        @Override
        public String getPM25() throws RemoteException {
            return null;
        }

        @Override
        public String getLunar() throws RemoteException {
            return null;
        }

        @Override
        public List<String> getAllChineseCities() throws RemoteException {
            return all_chinese_cities;
        }

        @Override
        public void refreshData() throws RemoteException {

        }
    };


    private void updateWeather(String city) {
        ApiManager.updateWeather(this, city, new ApiManager.ApiListerner() {
            @Override
            public void onUpdateError() {
                mHandler.sendEmptyMessage(WEATHER_UPDATE_COMPLETE);
            }

            @Override
            public void onRecieveWeather(Weather weather) {
                Message msg =  mHandler.obtainMessage();
                msg.obj = weather;
                msg.what = WEATHER_UPDATE_COMPLETE;
                mHandler.sendMessage(msg);
            }
        });
    }

    Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String lhs, String rhs) {
            String l = PinyinTool.getAlpha(lhs);
            String r =PinyinTool.getAlpha(rhs);
            int flag = l.compareTo(r);
            return  flag;
        }
    };

    class CityLoder extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            //id + name + postcode + province_id
            Cursor c = getContentResolver().query(CityProvider.CONTENT_URI_CITY, null, null, null, null);
            if(c != null && c.moveToFirst()) {
                do {
                    all_chinese_cities.add(c.getString(0));
                } while(c.moveToNext());
                //按字母排序
                Log.d("zzj","start sort");
                Collections.sort(all_chinese_cities, comparator);
                Log.d("zzj","sort complete");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }
}
