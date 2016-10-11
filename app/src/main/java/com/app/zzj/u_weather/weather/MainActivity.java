package com.app.zzj.u_weather.weather;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.app.zzj.u_weather.API.ApiManager;
import com.app.zzj.u_weather.API.Entity.Weather;
import com.app.zzj.u_weather.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener {

    private final int WEATHER_UPDATE_COMPLETE = 1;

    private final int CHOOSE_CITY  = 1000;
    private TextView tv_city;
    private SwipeRefreshLayout refresh;

    private String city = "北京";

    private List<BaseFragment> fragmentList = new ArrayList<BaseFragment>();

    public interface WeatherData {
        void onRefreshViews(Weather weather);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WEATHER_UPDATE_COMPLETE:
                    refresh.setRefreshing(false);
                    tv_city.setText(city);
                    if(msg.obj != null) {
                        Weather weather = (Weather) msg.obj;
                        for(BaseFragment fragment: fragmentList)
                            fragment.onRefreshViews(weather);
                    } else {
                        Toast.makeText(MainActivity.this, R.string.error_update_weather, Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_main);
        initViews();
        updateWeather();

        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        WeatherFragment weatherFragment = new WeatherFragment();
        LifeFragment lifeFragment = new LifeFragment();
        fragmentList.add(weatherFragment);
        fragmentList.add(lifeFragment);
        transaction.add(R.id.weatherF, weatherFragment);
        transaction.add(R.id.weatherF, lifeFragment);
        transaction.commit();
    }

    @Override
    public void onRefresh() {
        updateWeather();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case CHOOSE_CITY:
                city = data.getStringExtra("city");
                updateWeather();
                break;
            default:break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_city) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, CityChooseActivity.class);
            startActivityForResult(intent , CHOOSE_CITY);
        }
    }

    private void updateWeather() {
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

    private void initViews() {
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_city.setText(city);
        tv_city.setOnClickListener(this);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        refresh.setOnRefreshListener(this);
        refresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refresh.setProgressViewEndTarget(true, 120);
    }
}
