package com.app.zzj.u_weather.weather;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.zzj.u_weather.R;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by sedwt on 2016/10/11.
 */
public class CityChooseAdapter extends BaseAdapter {

    private Context context;
    private List<City> mAllCityList;
    private List<City> mHotCityList;

    private final int VIEW_TYPE = 5;

    public CityChooseAdapter(Context context, List<City> allCityList, List<City> hotCityList) {
        this.context = context;
        this.mAllCityList = allCityList;
        this.mHotCityList = hotCityList;
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
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
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
                if(position >= 1) {
                    holder.tv_city_name.setText(mAllCityList.get(position).getName());
//                    holder.llMain.setOnClickListener();
                    String current = getAlpha(mAllCityList.get(position).getPinyin());
                    String pre = position - 1 >= 0 ? getAlpha(mAllCityList.get(position-1).getPinyin()) : "";
                    if(current.equals(pre)) {
                        holder.tv_alpha.setVisibility(View.GONE);
                    } else {
                        holder.tv_alpha.setText(current);
                        holder.tv_alpha.setVisibility(View.VISIBLE);
                    }
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

    // 获得汉语拼音首字母
    private String getAlpha(String str) {
        if (str == null) {
            return "#";
        }
        if (str.trim().length() == 0) {
            return "#";
        }
        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式，判断首字母是否是英文字母
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        }
        return "#";
    }
}
