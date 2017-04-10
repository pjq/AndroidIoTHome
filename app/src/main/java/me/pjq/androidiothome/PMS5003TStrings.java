package me.pjq.androidiothome;

import android.content.Context;

/**
 * Created by i329817(Jianqing.Peng@sap.com) on 09/04/2017.
 */

public class PMS5003TStrings {
    private String um10;
    private String um5;
    private String um2_5;
    private String um1;
    private String um05;
    private String um03;
    private String pm5;
    private String pm5CF1;
    private String pm25;
    private String pm25CF1;
    private String pm10;
    private String pm10CF1;
    private String humidity;
    private String temperature;

    public PMS5003TStrings(Context context) {
        um10 = context.getString(R.string.pms5003t_um10);
        um5 = context.getString(R.string.pms5003t_um5);
        um2_5 = context.getString(R.string.pms5003t_um2_5);
        um1 = context.getString(R.string.pms5003t_um1);
        um05 = context.getString(R.string.pms5003t_um05);
        um03 = context.getString(R.string.pms5003t_um03);
        pm5 = context.getString(R.string.pms5003t_pm5);
        pm5CF1 = context.getString(R.string.pms5003t_pm5CF1);
        pm25 = context.getString(R.string.pms5003t_pm25);
        pm25CF1 = context.getString(R.string.pms5003t_pm25CF1);
        pm10 = context.getString(R.string.pms5003t_pm10);
        pm10CF1 = context.getString(R.string.pms5003t_pm10CF1);
        humidity = context.getString(R.string.pms5003t_humidity);
        temperature = context.getString(R.string.pms5003t_temperature);

    }

    public String getUm10() {
        return um10;
    }

    public String getUm5() {
        return um5;
    }

    public String getUm2_5() {
        return um2_5;
    }

    public String getUm1() {
        return um1;
    }

    public String getUm05() {
        return um05;
    }

    public String getUm03() {
        return um03;
    }

    public String getPm5() {
        return pm5;
    }

    public String getPm5CF1() {
        return pm5CF1;
    }

    public String getPm25() {
        return pm25;
    }

    public String getPm25CF1() {
        return pm25CF1;
    }

    public String getPm10() {
        return pm10;
    }

    public String getPm10CF1() {
        return pm10CF1;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getTemperature() {
        return temperature;
    }
}
