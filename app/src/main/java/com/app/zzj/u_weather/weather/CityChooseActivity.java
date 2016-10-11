package com.app.zzj.u_weather.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.TextView;

import com.app.zzj.u_weather.NView.LetterView;
import com.app.zzj.u_weather.R;

public class CityChooseActivity extends AppCompatActivity implements LetterView.OnSlidingListener{

    private final int CHOOSE_CITY  = 1000;

    private EditText et_search_city;
    private TextView tv_toast;
    private LetterView lv_letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); 有时候Manifest中设置好像无效？？
        setContentView(R.layout.activity_city_choose);
        initViews();
        initEvents();
    }

    @Override
    public void sliding(String str) {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("city", et_search_city.getText().toString());
        setResult(CHOOSE_CITY, intent);
        super.onBackPressed();
    }

    private void initViews() {
        et_search_city = (EditText) findViewById(R.id.et_search_city);
        tv_toast = (TextView) findViewById(R.id.tv_toast);
        lv_letter = (LetterView) findViewById(R.id.lv_letter);

    }

    private void initEvents() {
        lv_letter.setOnSlidingListener(this);
        lv_letter.setTextView(tv_toast);
    }
}
