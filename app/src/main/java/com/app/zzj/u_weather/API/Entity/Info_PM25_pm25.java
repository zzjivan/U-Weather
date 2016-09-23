package com.app.zzj.u_weather.API.Entity;

/**
 * Created by sedwt on 2016/9/23.
 */
public class Info_PM25_pm25 {
    private String curPm;
    private String pm25;
    private String pm10;
    private int level;
    private String quality;
    private String des;

    public String getCurPm() {
        return curPm;
    }

    public void setCurPm(String curPm) {
        this.curPm = curPm;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "Info_PM25_pm25{" +
                "curPm='" + curPm + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", level=" + level +
                ", quality='" + quality + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
