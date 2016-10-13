package com.app.zzj.u_weather.weather;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.zzj.u_weather.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by sedwt on 2016/10/11.
 */
public class CityChooseAdapter extends BaseAdapter {

    private Context context;
    private List<City> mAllCityList;
    private List<City> mHotCityList;
    private Map<String, Integer> letter2position = new HashMap<String, Integer>();

    private final int VIEW_TYPE = 5;

    public CityChooseAdapter(Context context, List<City> allCityList, List<City> hotCityList) {
        this.context = context;
        this.mAllCityList = allCityList;
        this.mHotCityList = hotCityList;
        letter2position.put("定位",0);
        letter2position.put("最近",1);
        letter2position.put("热门",2);
        letter2position.put("全部",3);
        setData(mAllCityList, mHotCityList);
    }

    public void setData(List<City> allCityList, List<City> hotCityList) {
        this.mAllCityList = allCityList;
        this.mHotCityList = hotCityList;
        for(int i = 0; i < mAllCityList.size(); i ++) {
            String current = mAllCityList.get(i).getAlpha();
            String pre = i - 1 >= 0 ? mAllCityList.get(i - 1).getAlpha() : "";
            if(current.equals(pre)) {
            } else {
                letter2position.put(current, i);
            }
        }
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE;
    }

    @Override
    public int getItemViewType(int position) {
        return position < 4 ? position : 4;
    }

    @Override
    public int getCount() {
        return mAllCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAllCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int type = getItemViewType(position);
        switch (type) {
            case 0:
                convertView = View.inflate(context, R.layout.current_city, null);
                break;
            case 1:
                convertView = View.inflate(context, R.layout.recent_city, null);
                break;
            case 2:
                convertView = View.inflate(context, R.layout.hot_city, null);
                break;
            case 3:
                convertView = View.inflate(context, R.layout.current_city, null);
                break;
            case 4:
                if(convertView == null) {
                    holder = new ViewHolder();
                    convertView = View.inflate(context, R.layout.city_list_item, null);
                    holder.tv_alpha = (TextView) convertView.findViewById(R.id.tv_alpha);
                    holder.llMain = (LinearLayout) convertView.findViewById(R.id.ll_main);
                    holder.tv_city_name = (TextView) convertView.findViewById(R.id.tv_city_name);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                    holder.tv_city_name.setText(mAllCityList.get(position).getName());
//                    holder.llMain.setOnClickListener();
                    String current = mAllCityList.get(position).getAlpha();
                    String pre = position - 1 >= 0 ? mAllCityList.get(position-1).getAlpha() : "";
                    if(current.equals(pre) && position != 4) {
                        holder.tv_alpha.setVisibility(View.GONE);
                    } else {
                        holder.tv_alpha.setText(current);
                        holder.tv_alpha.setVisibility(View.VISIBLE);
                    }
                break;
            default:
                break;
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_alpha;
        TextView tv_city_name;
        LinearLayout llMain;
    }

    public int getPosition(String s) {
        Log.d("zzj","getPosition:"+s+","+letter2position.get(s));
        return letter2position.get(s);
    }
}
