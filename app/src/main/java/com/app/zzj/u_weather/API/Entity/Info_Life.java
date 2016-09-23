package com.app.zzj.u_weather.API.Entity;

/**
 * Created by sedwt on 2016/9/22.
 */
public class Info_Life {

    public String date;
    public Info_Life_info info;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Info_Life_info getInfo() {
        return info;
    }

    public void setInfo(Info_Life_info info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Info_Life{" +
                "date='" + date + '\'' +
                ", info=" + info +
                '}';
    }
}
