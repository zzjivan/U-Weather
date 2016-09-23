package com.app.zzj.u_weather.API.Entity;

/**
 * Created by sedwt on 2016/9/22.
 */
public class Info_Weather {
    private String date;
    private Info_Weather_info info;
    private String week;
    private String nongli;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Info_Weather_info getInfo() {
        return info;
    }

    public void setInfo(Info_Weather_info info) {
        this.info = info;
    }

    public String getNongli() {
        return nongli;
    }

    public void setNongli(String nongli) {
        this.nongli = nongli;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date='" + date + '\'' +
                ", info=" + info +
                ", week='" + week + '\'' +
                ", nongli='" + nongli + '\'' +
                '}';
    }
}
