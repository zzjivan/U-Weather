package com.app.zzj.u_weather.API.Entity;

/**
 * Created by sedwt on 2016/9/23.
 */
public class Info_Realtime_weather {
    public String temperature;
    public String humidity;
    public String info;
    public String img;

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Info_Realtime_weather{" +
                "humidity='" + humidity + '\'' +
                ", temperature='" + temperature + '\'' +
                ", info='" + info + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
