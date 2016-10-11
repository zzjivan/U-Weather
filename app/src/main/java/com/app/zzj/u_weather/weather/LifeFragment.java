package com.app.zzj.u_weather.weather;


import android.app.LauncherActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.zzj.u_weather.API.Entity.Weather;
import com.app.zzj.u_weather.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LifeFragment extends BaseFragment implements MainActivity.WeatherData{

    private final int CALENDAR = 0;
    private final int CAR = 1;
    private final int CLOTH = 2;
    private final int COLD = 3;
    private final int SPORT = 4;
    private final int POLLUTE = 5;
    private final int ULTRAVIOLET = 6;

    private View mRootView;
    private GridView grid;

    private GridAdapter mAdapter;
    private List<LifeItem> itemList = new ArrayList<LifeItem>();

    private int[] item_icon_resource = {
            R.drawable.ic_stat_temp_0,
            R.drawable.ic_stat_temp_0,
            R.drawable.ic_stat_temp_0,
            R.drawable.ic_stat_temp_0,
            R.drawable.ic_stat_temp_0,
            R.drawable.ic_stat_temp_0,
            R.drawable.ic_stat_temp_0
    };

    private String[] item_name = {
            "万年历",
            "洗车",
            "穿衣",
            "感冒",
            "运动",
            "污染",
            "紫外线",
    };

    public LifeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i = 0; i < item_name.length; i ++) {
            LifeItem item = new LifeItem(item_name[i], null, item_icon_resource[i]);
            itemList.add(item);
        }
        mAdapter = new GridAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_life, null);
        grid = (GridView) mRootView.findViewById(R.id.grid);
        grid.setAdapter(mAdapter);
        return mRootView;
    }

    @Override
    public void onRefreshViews(Weather weather) {
        itemList.get(CALENDAR).setState(weather.getResult().getData().getRealtime().getMoon());
        itemList.get(CAR).setState(weather.getResult().getData().getLife().getInfo().getXiche().get(0));
        itemList.get(CLOTH).setState(weather.getResult().getData().getLife().getInfo().getChuanyi().get(0));
        itemList.get(COLD).setState(weather.getResult().getData().getLife().getInfo().getGanmao().get(0));
        itemList.get(SPORT).setState(weather.getResult().getData().getLife().getInfo().getYundong().get(0));
        itemList.get(POLLUTE).setState(weather.getResult().getData().getPm25().getPm25().getQuality());
        itemList.get(ULTRAVIOLET).setState(weather.getResult().getData().getLife().getInfo().getZiwaixian().get(0));
        mAdapter.notifyDataSetChanged();
    }

    class GridAdapter extends BaseAdapter {

        private Context context;
        public GridAdapter(Context context) {
            this.context = context;
        }
        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public LifeItem getItem(int position) {
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=  null;
            if(convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.life_info, null);
                holder.icon = (ImageView) convertView.findViewById(R.id.iv_lif_icon);
                holder.name = (TextView) convertView.findViewById(R.id.tv_life_item);
                holder.state = (TextView) convertView.findViewById(R.id.tv_life_state);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            LifeItem item = getItem(position);
            if(item != null) {
                holder.icon.setBackgroundResource(item.resource);
                holder.name.setText(item.name);
                holder.state.setText(item.state);
            }
            return convertView;
        }

    }

    static class ViewHolder {
        ImageView icon;
        TextView name;
        TextView state;
    }

    class LifeItem {
        String name;
        String state;
        int resource;
        public LifeItem(String name, String state, int resource) {
            this.name = name;
            this.state = state;
            this.resource = resource;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

}
