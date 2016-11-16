package com.app.zzj.u_weather.weather;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.app.zzj.u_weather.NView.LetterView;
import com.app.zzj.u_weather.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aidl.IWeatherDataInterface;

public class CityChooseActivity extends AppCompatActivity implements LetterView.OnSlidingListener , AdapterView.OnItemClickListener {

    private final int CHOOSE_CITY  = 1000;

    private EditText et_search_city;
    private TextView tv_toast;
    private LetterView lv_letter;
    private ListView lv_list_city;

    private CityChooseAdapter cityChooseAdapter;

    private List<String> allcityList = new ArrayList<String>();
    private List<String> hotcityList = new ArrayList<String>();

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                Log.d("zzj","onServiceConnected");
                allcityList = IWeatherDataInterface.Stub.asInterface(service).getAllChineseCities();
                cityChooseAdapter = new CityChooseAdapter(CityChooseActivity.this, allcityList, hotcityList);
                lv_list_city.setAdapter(cityChooseAdapter);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); 有时候Manifest中设置好像无效？？
        bindService(new Intent(CityChooseActivity.this, WeatherDataService.class), serviceConnection, Context.BIND_AUTO_CREATE);
        setContentView(R.layout.activity_city_choose);
        initViews();
        initEvents();
    }

    @Override
    public void sliding(String str) {
        lv_list_city.setSelection(cityChooseAdapter.getPosition(str));
    }

    @Override
    public void onBackPressed() {
        setResult(CHOOSE_CITY);
        finish();
        //super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if(serviceConnection != null)
            unbindService(serviceConnection);
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String city = (String)allcityList.toArray()[position];
        SharedPreferences sp = getSharedPreferences("city",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("currentCity", city);

        Set<String> cities = sp.getStringSet("citySet", null);
        cities.add(city);
        editor.putStringSet("citySet", cities);

        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initViews() {
        et_search_city = (EditText) findViewById(R.id.et_search_city);
        tv_toast = (TextView) findViewById(R.id.tv_toast);
        lv_letter = (LetterView) findViewById(R.id.lv_letter);
        lv_list_city = (ListView) findViewById(R.id.lv_list_city);
    }

    private void initEvents() {
        lv_letter.setOnSlidingListener(this);
        lv_letter.setTextView(tv_toast);
        lv_list_city.setOnItemClickListener(this);
    }

}
