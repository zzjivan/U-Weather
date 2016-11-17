package com.app.zzj.u_weather.weather;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.zzj.u_weather.Data.CityProvider;
import com.app.zzj.u_weather.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by sedwt on 2016/11/15.
 */
public class CityManagementActivity extends AppCompatActivity {
    private final int MANAGE_CITY  = 1000;

    private ListView lv_cityList;
    private ImageButton ib_city_add;
    private cityAdapter cityAdapter;
    private List<String> cities = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_city_management);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_ab_back_holo_dark_am);

        Cursor c = getContentResolver().query(CityProvider.CONTENT_URI_PRESENT_CITY, new String[]{ CityProvider.PresentCityColumns.CITY_NAME }, null, null, "_id desc");
        if(c != null) {
            while(c.moveToNext()) {
                cities.add(c.getString(c.getColumnIndex(CityProvider.PresentCityColumns.CITY_NAME)));
            }
        }

        lv_cityList = (ListView) findViewById(R.id.lv_list_city);
        cityAdapter = new cityAdapter(cities);
        lv_cityList.setAdapter(cityAdapter);

        ib_city_add = (ImageButton) findViewById(R.id.ib_add);
        ib_city_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityManagementActivity.this, CityChooseActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setResult(MANAGE_CITY);
        finish();
        //super.onBackPressed();
    }

    class cityAdapter extends BaseAdapter {
        private List<String> cities;

        public cityAdapter(List<String> data) {
            cities = data;
        }

        @Override
        public int getCount() {
            return cities.size();
        }

        @Override
        public Object getItem(int position) {
            return cities.toArray()[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if(convertView == null) {
                convertView = LayoutInflater.from(CityManagementActivity.this).inflate(R.layout.city_management_list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.iv_weather = (ImageView) convertView.findViewById(R.id.iv_weather);
                viewHolder.tv_city = (TextView) convertView.findViewById(R.id.tv_city);
                viewHolder.tv_temperature = (TextView) convertView.findViewById(R.id.tv_temperature);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.iv_weather.setBackgroundResource(R.drawable.ic_launcher);
            viewHolder.tv_city.setText((String)getItem(position));
            viewHolder.tv_temperature.setText("15/20");
            return convertView;
        }

        class ViewHolder {
            ImageView iv_weather;
            TextView tv_city;
            TextView tv_temperature;
        }
    }
}
