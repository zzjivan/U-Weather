package com.app.zzj.u_weather.weather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.app.zzj.u_weather.Data.CityProvider;
import com.app.zzj.u_weather.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by sedwt on 2016/11/15.
 */
public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_welcome);

        SharedPreferences sp = getSharedPreferences("city", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if(sp.getStringSet("citySet", null) == null) {
            Log.d("zzj", "init citySet");
            Set<String> city = new HashSet<String>();
            city.add("北京");
            editor.putStringSet("citySet", city);
        }
        if(sp.getString("currentCity", null) == null) {
            Log.d("zzj","init currentCity");
            editor.putString("currentCity", "北京");
        }
        editor.commit();

        startService(new Intent(WelcomeActivity.this, WeatherDataService.class));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 5000);
    }

}
