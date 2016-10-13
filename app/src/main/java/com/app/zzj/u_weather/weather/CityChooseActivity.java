package com.app.zzj.u_weather.weather;

import android.content.Intent;
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
import java.util.List;

public class CityChooseActivity extends AppCompatActivity implements LetterView.OnSlidingListener , AdapterView.OnItemClickListener {

    private final int CHOOSE_CITY  = 1000;

    private EditText et_search_city;
    private TextView tv_toast;
    private LetterView lv_letter;
    private ListView lv_list_city;

    private CityChooseAdapter cityChooseAdapter;

    private List<City> allcityList = new ArrayList<City>();
    private List<City> hotcityList = new ArrayList<City>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); 有时候Manifest中设置好像无效？？
        setContentView(R.layout.activity_city_choose);
        allcityList = getIntent().getParcelableArrayListExtra("allcity");
        hotcityList = getIntent().getParcelableArrayListExtra("hotcity");
        cityChooseAdapter = new CityChooseAdapter(this, allcityList, hotcityList);
        initViews();
        initEvents();
    }

    @Override
    public void sliding(String str) {
        lv_list_city.setSelection(cityChooseAdapter.getPosition(str));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("city", et_search_city.getText().toString());
        setResult(CHOOSE_CITY, intent);
        super.onBackPressed();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("city", allcityList.get(position).getName());
        setResult(CHOOSE_CITY, intent);
        finish();
    }

    private void initViews() {
        et_search_city = (EditText) findViewById(R.id.et_search_city);
        tv_toast = (TextView) findViewById(R.id.tv_toast);
        lv_letter = (LetterView) findViewById(R.id.lv_letter);
        lv_list_city = (ListView) findViewById(R.id.lv_list_city);
        lv_list_city.setAdapter(cityChooseAdapter);
    }

    private void initEvents() {
        lv_letter.setOnSlidingListener(this);
        lv_letter.setTextView(tv_toast);
        lv_list_city.setOnItemClickListener(this);
    }

}
