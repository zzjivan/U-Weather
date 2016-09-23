package com.app.zzj.u_weather.weather;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
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
public class WeatherFragment extends Fragment implements OnRefreshListener {

    private View mRootView;
    private SwipeRefreshLayout refresh;
    private String city = "北京";

    public WeatherFragment(){
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    refresh.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_weather, null);
        refresh = (SwipeRefreshLayout) mRootView.findViewById(R.id.refresh);
        refresh.setOnRefreshListener(this);
        refresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refresh.setProgressViewEndTarget(true, 100);

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

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }
}
