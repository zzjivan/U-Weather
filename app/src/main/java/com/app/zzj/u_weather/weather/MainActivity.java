package com.app.zzj.u_weather.weather;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.zzj.u_weather.API.Entity.Weather;
import com.app.zzj.u_weather.Data.CityProvider;
import com.app.zzj.u_weather.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.BlurTool;


public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private final String DEFAULT_CITY = "北京";
    private final int CITY_LOAD_COMPLETE = 2;

    private final int CHOOSE_CITY  = 1000;
    private TextView tv_city;
    private ViewPager vp_main;
    private ImageButton ib_add_city;

    private ArrayList<City> allcityList = new ArrayList<City>();
    private ArrayList<City> hotcityList = new ArrayList<City>();

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
        current_city = DEFAULT_CITY;
        initViews();
        initData();
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position){
                CityInfoFragment f = (CityInfoFragment) cityMap.get(position).values().iterator().next();
                return f;
            }

            @Override
            public int getCount() {
                return cityMap.size();
            }
        };
        vp_main.setAdapter(fragmentPagerAdapter);

        Bitmap bmp = ((BitmapDrawable) getResources().getDrawable(R.drawable.night_bg)).getBitmap();
        getWindow().setBackgroundDrawable(new BitmapDrawable(BlurTool.blur(this, bmp)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case CHOOSE_CITY:
                current_city = data.getStringExtra("city");
                tv_city.setText(current_city);
                Log.d("zzj","vp_main.getCurrentItem():"+vp_main.getCurrentItem());
                cityMap.get(vp_main.getCurrentItem()).values().iterator().next().onCityChanged(current_city);
                break;
            default:break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_city) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, CityChooseActivity.class);
            intent.putParcelableArrayListExtra("allcity",allcityList );
            intent.putParcelableArrayListExtra("hotcity",hotcityList );
            startActivityForResult(intent , CHOOSE_CITY);
        } else if(v.getId() == R.id.ib_add_city) {
            Log.d("zzj","add");
            Map<String, BaseFragment> map = new HashMap<String, BaseFragment>();
            BaseFragment fragment = new CityInfoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("city", "合肥");
            fragment.setArguments(bundle);
            map.put("合肥", fragment);
            cityMap.add(map);
            fragmentPagerAdapter.notifyDataSetChanged();
        }
    }

    private void initViews() {
        vp_main = (ViewPager) findViewById(R.id.main_pager);
        vp_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_city.setText(current_city);
        tv_city.setOnClickListener(this);
        ib_add_city = (ImageButton) findViewById(R.id.ib_add_city);
        ib_add_city.setOnClickListener(this);
    }

    private void initData() {
        Map<String, BaseFragment> map = new HashMap<String, BaseFragment>();
        BaseFragment fragment = new CityInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("city", current_city);
        fragment.setArguments(bundle);
        map.put(current_city, fragment);
        cityMap.add(map);
        new CityLoder().execute();
    }

    class CityLoder extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            //id + name + postcode + province_id
            Cursor c = getContentResolver().query(CityProvider.CityColumns.CONTENT_URI, null, null, null, null);
            if(c != null && c.moveToFirst()) {
                do {
                    City city = new City(c.getString(0));
                    allcityList.add(city);
                } while(c.moveToNext());
                //按字母排序
                Collections.sort(allcityList, comparator);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }

    Comparator<City> comparator = new Comparator<City>() {
        @Override
        public int compare(City lhs, City rhs) {
            String l = lhs.getAlpha();
            String r = rhs.getAlpha();
            int flag = l.compareTo(r);
            return  flag;
        }
    };
}
