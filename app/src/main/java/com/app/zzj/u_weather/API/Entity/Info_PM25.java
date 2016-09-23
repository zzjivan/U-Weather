package com.app.zzj.u_weather.API.Entity;

/**
 * Created by sedwt on 2016/9/22.
 */
public class Info_PM25 {
    private String key;
    private int show_desc;
    private Info_PM25_pm25 pm25;
    private String dateTime;
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Info_PM25_pm25 getPm25() {
        return pm25;
    }

    public void setPm25(Info_PM25_pm25 pm25) {
        this.pm25 = pm25;
    }

    public int getShow_desc() {
        return show_desc;
    }

    public void setShow_desc(int show_desc) {
        this.show_desc = show_desc;
    }

    @Override
    public String toString() {
        return "Info_PM25{" +
                "cityName='" + cityName + '\'' +
                ", key='" + key + '\'' +
                ", show_desc=" + show_desc +
                ", pm25=" + pm25 +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
