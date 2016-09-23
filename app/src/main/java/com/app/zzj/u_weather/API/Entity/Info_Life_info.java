package com.app.zzj.u_weather.API.Entity;

import java.util.List;

/**
 * Created by sedwt on 2016/9/23.
 */
public class Info_Life_info {
    public List<String> chuanyi;
    public List<String> ganmao;
    public List<String> kongtiao;
    public List<String> wuran;
    public List<String> xiche;
    public List<String> yundong;
    public List<String> ziwaixian;

    public List<String> getChuanyi() {
        return chuanyi;
    }

    public void setChuanyi(List<String> chuanyi) {
        this.chuanyi = chuanyi;
    }

    public List<String> getGanmao() {
        return ganmao;
    }

    public void setGanmao(List<String> ganmao) {
        this.ganmao = ganmao;
    }

    public List<String> getKongtiao() {
        return kongtiao;
    }

    public void setKongtiao(List<String> kongtiao) {
        this.kongtiao = kongtiao;
    }

    public List<String> getWuran() {
        return wuran;
    }

    public void setWuran(List<String> wuran) {
        this.wuran = wuran;
    }

    public List<String> getXiche() {
        return xiche;
    }

    public void setXiche(List<String> xiche) {
        this.xiche = xiche;
    }

    public List<String> getYundong() {
        return yundong;
    }

    public void setYundong(List<String> yundong) {
        this.yundong = yundong;
    }

    public List<String> getZiwaixian() {
        return ziwaixian;
    }

    public void setZiwaixian(List<String> ziwaixian) {
        this.ziwaixian = ziwaixian;
    }

    @Override
    public String toString() {
        return "Info_Life_info{" +
                "chuanyi=" + chuanyi +
                ", ganmao=" + ganmao +
                ", kongtiao=" + kongtiao +
                ", wuran=" + wuran +
                ", xiche=" + xiche +
                ", yundong=" + yundong +
                ", ziwaixian=" + ziwaixian +
                '}';
    }
}
