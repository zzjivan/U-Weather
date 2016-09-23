package com.app.zzj.u_weather.API.Entity;

/**
 * Created by sedwt on 2016/9/22.
 */
public class Info_Realtime {
    public String city_code;
    public String city_name;
    public String date;
    public String time;
    public int week;
    public String moon;
    public long dataUptime;
    public Info_Realtime_weather weather;
    public Info_Realtime_wind wind;

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public Info_Realtime_wind getWind() {
        return wind;
    }

    public void setWind(Info_Realtime_wind wind) {
        this.wind = wind;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public Info_Realtime_weather getWeather() {
        return weather;
    }

    public void setWeather(Info_Realtime_weather weather) {
        this.weather = weather;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoon() {
        return moon;
    }

    public void setMoon(String moon) {
        this.moon = moon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getDataUptime() {
        return dataUptime;
    }

    public void setDataUptime(long dataUptime) {
        this.dataUptime = dataUptime;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    @Override
    public String toString() {
        return "Info_Realtime{" +
                "city_code='" + city_code + '\'' +
                ", city_name='" + city_name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", week=" + week +
                ", moon='" + moon + '\'' +
                ", dataUptime=" + dataUptime +
                ", weather=" + weather +
                ", wind=" + wind +
                '}';
    }
}
