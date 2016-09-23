package com.app.zzj.u_weather.API.Entity;

/**
 * Created by sedwt on 2016/9/23.
 */
public class Info_Realtime_wind {
    public String direct;
    public String power;
    public String offset;
    public String windspeed;

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Info_Realtime_wind{" +
                "direct='" + direct + '\'' +
                ", power='" + power + '\'' +
                ", offset='" + offset + '\'' +
                ", windspeed='" + windspeed + '\'' +
                '}';
    }
}
