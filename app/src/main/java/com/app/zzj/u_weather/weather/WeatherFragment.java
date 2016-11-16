package com.app.zzj.u_weather.weather;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.zzj.u_weather.API.ApiManager;
import com.app.zzj.u_weather.API.Entity.Weather;
import com.app.zzj.u_weather.R;

import org.w3c.dom.Text;

/**
 * Created by sedwt on 2016/9/22.
 */
public class WeatherFragment extends BaseFragment implements MainActivity.WeatherData{
    private final String TAG = "WeatherFragment";

    private View mRootView;
    private TextView tv_temp;
    private TextView tv_humidity;

    private String city = "北京";

    private FragmentManager fm;

    public WeatherFragment(){
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getChildFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_weather, null);
        initViews();
        return mRootView;
    }

    @Override
    public void onRefreshViews(Weather weather) {
        RefreshView(weather);
    }

    private void initViews() {
        tv_temp = (TextView) mRootView.findViewById(R.id.tv_temp);
        tv_humidity = (TextView) mRootView.findViewById(R.id.tv_humidity);
    }

    private void RefreshView(Weather weather) {
        tv_temp.setText(weather.getResult().getData().getRealtime().getWeather().getTemperature()+"°");
        tv_humidity.setText(weather.getResult().getData().getRealtime().getWeather().getHumidity());
    }
}
