package com.app.zzj.u_weather.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.zzj.u_weather.API.ApiManager;
import com.app.zzj.u_weather.API.Entity.Weather;
import com.app.zzj.u_weather.R;

/**
 * Created by sedwt on 2016/9/22.
 */
public class WeatherFragment extends Fragment{

    private View mRootView;
    private TextView tv;
    private String city = "北京";

    public WeatherFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_weather, null);
        tv = (TextView) mRootView.findViewById(R.id.weather);
        ApiManager.updateWeather(getActivity(), city, new ApiManager.ApiListerner() {
            @Override
            public void onUpdateError() {

            }

            @Override
            public void onRecieveWeather(Weather weather) {

            }
        });
        return mRootView;
    }
}
