package com.app.zzj.u_weather.API.Entity;

/**
 * Created by sedwt on 2016/9/23.
 */
public class Weather {
    private String reason;
    private Result result;
    private String error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    @Override
    public String toString() {
        return "Info{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code='" + error_code + '\'' +
                '}';
    }
}
