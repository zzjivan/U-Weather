package com.app.zzj.u_weather.API.Entity;

import java.util.List;

/**
 * Created by sedwt on 2016/9/23.
 */
public class Info_Weather_info {
    private List<String> day;
    private List<String> night;

    public List<String> getDay() {
        return day;
    }

    public void setDay(List<String> day) {
        this.day = day;
    }

    public List<String> getNight() {
        return night;
    }

    public void setNight(List<String> night) {
        this.night = night;
    }

    @Override
    public String toString() {
        return "Info_Weather_info{" +
                "day=" + day +
                ", night=" + night +
                '}';
    }
}
