package com.app.zzj.u_weather.weather;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.zzj.u_weather.API.Entity.Weather;
import com.app.zzj.u_weather.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Util.BlurTool;


public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private TextView tv_city;
    private ViewPager vp_main;
    private ImageButton ib_manage_city;

    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Map<String, BaseFragment>> cityMap = new ArrayList<Map<String, BaseFragment>>();

    private String current_city;
    private int current_fragment_index = 0;

    public interface WeatherData {
        void onCityChanged(String city);
        void onRefreshViews(Weather weather);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_main);
        current_city = getSharedPreferences("city", Context.MODE_PRIVATE).getString("currentCity", null);
        initViews();
        initData();
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position){
                CityInfoFragment cityInfoFragment = (CityInfoFragment) cityMap.get(position).values().iterator().next();
                return cityInfoFragment;
            }

            @Override
            public int getCount() {
                return cityMap.size();
            }
        };
        vp_main.setAdapter(fragmentPagerAdapter);

        Bitmap bmp = ((BitmapDrawable) getResources().getDrawable(R.drawable.sun)).getBitmap();
        getWindow().setBackgroundDrawable(new BitmapDrawable(BlurTool.blur(this, bmp)));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("zzj","onNewIntent");
        cityMap.clear();
        current_city = getSharedPreferences("city", Context.MODE_PRIVATE).getString("currentCity", null);
        initViews();
        initData();
        if(fragmentPagerAdapter != null)
            fragmentPagerAdapter.notifyDataSetChanged();
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause() {
        getSharedPreferences("city", Context.MODE_PRIVATE).edit().putString("currentCity", current_city).commit();
        super.onPause();
    }

    @Override
    public void onClick(View v) {
         if(v.getId() == R.id.ib_manage_city) {
            Intent intent = new Intent(MainActivity.this, CityManagementActivity.class);
            startActivity(intent);
        }
    }

    private void initViews() {
        Log.d("zzj","initViews");
        vp_main = (ViewPager) findViewById(R.id.main_pager);
        vp_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                current_fragment_index  = position;
                current_city = (String)cityMap.get(position).keySet().toArray()[0];
                Log.d("zzj","onPageSelected:"+current_city+","+position);
                tv_city.setText(current_city);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("zzj","onPageScrolled");
            }
        });
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_city.setText(current_city);
        ib_manage_city = (ImageButton) findViewById(R.id.ib_manage_city);
        ib_manage_city.setOnClickListener(this);
    }

    private void initData() {
        Set<String> cities = getSharedPreferences("city", Context.MODE_PRIVATE).getStringSet("citySet", null);
        for(String city : cities) {
            Map<String, BaseFragment> map = new HashMap<String, BaseFragment>();
            BaseFragment fragment = new CityInfoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("city", city);
            fragment.setArguments(bundle);
            map.put(current_city, fragment);
            cityMap.add(map);
        }
    }

    private void showCurrentCity() {
        tv_city.setText(current_city);
        vp_main.setCurrentItem(current_fragment_index);
    }
}
