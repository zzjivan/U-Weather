package com.app.zzj.u_weather.weather;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.zzj.u_weather.API.Entity.Weather;
import com.app.zzj.u_weather.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements MainActivity.WeatherData{


    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onCityChanged(String city) {

    }

    @Override
    public void onRefreshViews(Weather weather) {

    }
}
