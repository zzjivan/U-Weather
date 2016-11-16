package com.app.zzj.u_weather.weather;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.zzj.u_weather.API.ApiManager;
import com.app.zzj.u_weather.API.Entity.Weather;
import com.app.zzj.u_weather.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sedwt on 2016/11/8.
 * 主fragment，本身不更新UI，管理其包含的fragment来进行UI显示
 */
public class CityInfoFragment extends BaseFragment  implements SwipeRefreshLayout.OnRefreshListener,MainActivity.WeatherData {

    private final int WEATHER_UPDATE_COMPLETE = 1;

    private SwipeRefreshLayout refresh;

    private List<BaseFragment> fragmentList = new ArrayList<BaseFragment>();

    private String city;

    public CityInfoFragment() {
    }

    @Override
    protected void lazyLoad() {

    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WEATHER_UPDATE_COMPLETE:
                    refresh.setRefreshing(false);
                    if(msg.obj != null) {
                        Weather weather = (Weather) msg.obj;
                        for(BaseFragment fragment: fragmentList)
                            fragment.onRefreshViews(weather);
                    } else {
                        Toast.makeText(getActivity(), R.string.error_update_weather, Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        city = getArguments().getString("city");
        updateWeather();
        FragmentManager fragmentManager = getChildFragmentManager();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_info, null);
        initViews(view);
        return view;
    }

    @Override
    public void onRefresh() {
        updateWeather();
    }

    @Override
    public void onCityChanged(String city) {
        this.city = city;
        updateWeather();
    }

    private void initViews(View view) {
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        refresh.setOnRefreshListener(this);
        refresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refresh.setProgressViewEndTarget(true, 120);
    }

    private void updateWeather() {
        ApiManager.updateWeather(getActivity(), city, new ApiManager.ApiListerner() {
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
}
