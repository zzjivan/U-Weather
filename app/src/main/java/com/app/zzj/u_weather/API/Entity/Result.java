package com.app.zzj.u_weather.API.Entity;

/**
 * Created by sedwt on 2016/9/23.
 */
public class Result {
    Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                '}';
    }
}
