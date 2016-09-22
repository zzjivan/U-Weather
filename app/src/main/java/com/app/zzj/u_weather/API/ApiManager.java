package com.app.zzj.u_weather.API;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.app.zzj.u_weather.API.Entity.Info_Life;
import com.app.zzj.u_weather.API.Entity.Weather;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sedwt on 2016/9/22.
 */
public class ApiManager {

    private static String URL = "http://op.juhe.cn/onebox/weather/query?cityname=%1$s&dtype=json&key=f2a64f1e37759dd8c1025e00f2e14b57";
    private final static Gson gson = new Gson();

    public interface ApiListerner {
        public void onUpdateError();
        public void onRecieveWeather(Weather weather);
    }

    public static void updateWeather(Context context, String city, ApiListerner listener) {
        URL = String.format(URL, city);
        new ApiTask(context, URL, listener).execute();
    }

    static class ApiTask extends AsyncTask<Void, Void, Weather> {

        private final String url;
        private final Context context;
        private final ApiListerner listener;

        public ApiTask(Context context, String url, ApiListerner listener) {
            super();
            this.context = context;
            this.url = url;
            this.listener = listener;
        }

        @Override
        protected Weather doInBackground(Void... params) {
            return updateWeatherFromInternet(context, url);
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
        }
    }

    private static Weather updateWeatherFromInternet(Context context, String url ) {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);

        try {
            Response rsp= call.execute();
            if(rsp.isSuccessful()) {
                Info_Life weather = gson.fromJson(rsp.body().string(), Info_Life.class);
                Log.d("zzj",weather.toString());
            } else {
                Log.d("zzj", "error");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}