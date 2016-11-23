package com.app.zzj.u_weather.weather;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
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
    private final int WEATHER_UPDATE_ERROR = 2;
    private final int WEATHER_NETWORK_ERROR = 3;

    private SwipeRefreshLayout refresh;

    private List<BaseFragment> fragmentList = new ArrayList<BaseFragment>();

    private String city;

    WeatherFragment weatherFragment;
    LifeFragment lifeFragment;

    public CityInfoFragment() {
    }

    @Override
    protected void lazyLoad() {
        Log.d("zzj",city+":lazyLoad");
        updateWeather();
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WEATHER_UPDATE_COMPLETE:
                    refresh.setRefreshing(false);
                    if(msg.obj != null) {
                        Log.d("zzj","weather not null");
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
    public void setInitialSavedState(SavedState state) {
//        super.setInitialSavedState(state);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        city = getArguments().getString("city");
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
            weatherFragment = new WeatherFragment();
            lifeFragment = new LifeFragment();
            fragmentList.add(weatherFragment);
            fragmentList.add(lifeFragment);
            transaction.add(R.id.weatherF, weatherFragment, "weather");
            transaction.add(R.id.weatherF, lifeFragment, "life");
            transaction.commit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //updateWeather();
        View view = inflater.inflate(R.layout.fragment_city_info, null);
        initViews(view);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        getChildFragmentManager().putFragment(outState, "weather", weatherFragment);
//        getChildFragmentManager().putFragment(outState, "life", lifeFragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        Log.d("zzj","onDestroy:"+city);
        super.onDestroy();
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
            public void onResponseError() {
                mHandler.sendEmptyMessage(WEATHER_NETWORK_ERROR);
            }

            @Override
            public void onRecieveWeather(Weather weather) {
                Message msg =  mHandler.obtainMessage();
                msg.obj = weather;
                if(weather.getResult() != null)
                    msg.what = WEATHER_UPDATE_COMPLETE;
                else
                    msg.what = WEATHER_UPDATE_ERROR;
                mHandler.sendMessage(msg);
            }
        });
    }

    public String getCity() {
        return city;
    }
}
