package com.app.zzj.u_weather.API.Entity;

import java.util.List;

/**
 * Created by sedwt on 2016/9/23.
 */
public class Data {
    private Info_Realtime realtime;
    private Info_Life life;
    private List<Info_Weather> weather;
    private Info_PM25 pm25;
    private String jingqu;
    private String date;
    private String isForeign;

    public Info_Life getLife() {
        return life;
    }

    public void setLife(Info_Life life) {
        this.life = life;
    }

    public Info_PM25 getPm25() {
        return pm25;
    }

    public void setPm25(Info_PM25 pm25) {
        this.pm25 = pm25;
    }

    public Info_Realtime getRealtime() {
        return realtime;
    }

    public void setRealtime(Info_Realtime realtime) {
        this.realtime = realtime;
    }

    public List<Info_Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Info_Weather> weather) {
        this.weather = weather;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(String isForeign) {
        this.isForeign = isForeign;
    }

    public String getJingqu() {
        return jingqu;
    }

    public void setJingqu(String jingqu) {
        this.jingqu = jingqu;
    }

    @Override
    public String toString() {
        return "Data{" +
                "date='" + date + '\'' +
                ", realtime=" + realtime +
                ", life=" + life +
                ", weather=" + weather +
                ", pm25=" + pm25 +
                ", jingqu='" + jingqu + '\'' +
                ", isForeign='" + isForeign + '\'' +
                '}';
    }
}
