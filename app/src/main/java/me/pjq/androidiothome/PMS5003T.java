package me.pjq.androidiothome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i329817(Jianqing.Peng@sap.com) on 18/03/2017.
 */

public class PMS5003T {
    private ItemValues um10;
    private ItemValues um5;
    private ItemValues um2_5;
    private ItemValues um1;
    private ItemValues um05;
    private ItemValues um03;
    private ItemValues pm5;
    private ItemValues pm5CF1;
    private ItemValues pm25;
    private ItemValues pm25CF1;
    private ItemValues pm10;
    private ItemValues pm10CF1;
    private ItemValues humidity;
    private ItemValues temperature;

    PMS5003TStrings pms5003TStrings;

    public void setPms5003TStrings(PMS5003TStrings pms5003TStrings) {
        this.pms5003TStrings = pms5003TStrings;
    }

    public class PMSData {
        /*
         * 0: 42 1: 4d start
		 * 2: 0 3: 24 packet size = 36 byte, infact is it not right
		 * 4: 0 5: 1c pm1.0 cf=1 ug/cm3
		 * 6: 0 7: 29 pm2.5 cf=1
		 * 8: 0 9: 2d pm10 cf=1
		 * 10: 0 11: 18 pm1.0
		 * 12: 0 13: 24 pm2.5
		 * 14: 0 15: 2d pm10
		 * 16: 13 17: cb 0.3um cnt / 0.1L
		 * 18: 5 19: 8b 0.5um cnt / 0.1L
		 * 20: 0 21: fb 1.0um cnt / 0.1L
		 * 22: 0 23: 18 2.5um cnt / 0.1L
		 * 24: 0 25: 6 5.0um cnt / 0.1L
		 * 26: 0 27: 1 10um cnt / 0.1L
		 * 28: 0 29: 38 HCHO ug/m3
		 * 30: 0 31: e1 temp 0.1degree
		 * 32: 1 33: 6 humi % (*0.1)
		 * 34: 0 35: 0 chechksum
		 * 36: 91 37: 0
		 * 38: 5 39: c7
		 */
    }

    public List<ItemValues> parseData(byte[] data) {
        short head = toShort(data[0], data[1]);
        if (head != 0x424d) {
            return null;
        }
        short len = toShort(data[2], data[3]);
        System.out.println("len: " + len);

        List<ItemValues> itemValues = new ArrayList<>();

//        System.out.println("湿度：" + toShort(data[32], data[33]) / 10.0 + "%");
        humidity = new ItemValues((float) (toShort(data[32], data[33]) / 10.0), pms5003TStrings.getHumidity(), "%");
        itemValues.add(humidity);

//        System.out.println("温度：" + toShort(data[30], data[31]) / 10.0 + "摄氏度");
        temperature = new ItemValues((float) (toShort(data[30], data[31]) / 100.0), pms5003TStrings.getTemperature(), " ℃");
        itemValues.add(temperature);

//        System.out.println("10um颗粒数（每0.1L)：" + toShort(data[26], data[27]));
        um10 = new ItemValues((float) (toShort(data[26], data[27]) / 1000.0), pms5003TStrings.getUm10(), "");
        itemValues.add(um10);

//        System.out.println("5um颗粒数（每0.1L)：" + toShort(data[24], data[25]));
        um5 = new ItemValues((float) (toShort(data[24], data[25])), pms5003TStrings.getUm5(), "");
        itemValues.add(um5);

//        System.out.println("2.5um颗粒数（每0.1L)：" + toShort(data[22], data[23]));
        um2_5 = new ItemValues((float) (toShort(data[22], data[23])), pms5003TStrings.getUm2_5(), "");
        itemValues.add(um2_5);

//        System.out.println("1um颗粒数（每0.1L)：" + toShort(data[20], data[21]));
        um1 = new ItemValues((float) (toShort(data[22], data[23])), pms5003TStrings.getUm1(), "");
        itemValues.add(um1);

//        System.out.println("0.5um颗粒数（每0.1L)：" + toShort(data[18], data[19]));
        um05 = new ItemValues((float) (toShort(data[18], data[19])), pms5003TStrings.getUm05(), "");
        itemValues.add(um05);

//        System.out.println("0.3um颗粒数（每0.1L)：" + toShort(data[16], data[17]));
        um03 = new ItemValues((float) (toShort(data[16], data[17])), pms5003TStrings.getUm03(), "");
        itemValues.add(um03);

//        System.out.println("PM10（大气环境下）：" + toShort(data[14], data[15]) + "μg/m³");
        pm10 = new ItemValues((float) (toShort(data[14], data[15])), pms5003TStrings.getPm10(), "μg/m³");
        itemValues.add(pm10);

//        System.out.println("PM5（大气环境下）：" + toShort(data[12], data[13]) + "μg/m³");
        pm5 = new ItemValues((float) (toShort(data[12], data[13])), pms5003TStrings.getPm5(), "μg/m³");
        itemValues.add(pm5);

//        System.out.println("PM2.5（大气环境下）：" + toShort(data[10], data[11]) + "μg/m³");
        pm25 = new ItemValues((float) (toShort(data[10], data[11])), pms5003TStrings.getPm25(), "μg/m³");
        itemValues.add(pm25);

//        System.out.println("PM10（CF=1，标准颗粒物）：" + toShort(data[8], data[9]) + "μg/m³");
        pm10CF1 = new ItemValues((float) (toShort(data[8], data[9])), pms5003TStrings.getPm10CF1(), "μg/m³");
        itemValues.add(pm10CF1);

//        System.out.println("PM5（CF=1，标准颗粒物）：" + toShort(data[6], data[7]) + "μg/m³");
        pm5CF1 = new ItemValues((float) (toShort(data[6], data[7])), pms5003TStrings.getPm5CF1(), "μg/m³");
        itemValues.add(pm5CF1);

//        System.out.println("PM2.5（CF=1，标准颗粒物）：" + toShort(data[4], data[5]) + "μg/m³");
        pm25CF1 = new ItemValues((float) (toShort(data[4], data[5])), pms5003TStrings.getPm25CF1(), "μg/m³");
        itemValues.add(pm25CF1);

        return itemValues;
    }

    public static short toShort(byte high, byte low) {
        return (short) (((high & 0x00FF) << 8) | (0x00FF & low));
    }

    public ItemValues getUm10() {
        return um10;
    }

    public ItemValues getUm5() {
        return um5;
    }

    public ItemValues getUm2_5() {
        return um2_5;
    }

    public ItemValues getUm1() {
        return um1;
    }

    public ItemValues getUm05() {
        return um05;
    }

    public ItemValues getUm03() {
        return um03;
    }

    public ItemValues getPm5() {
        return pm5;
    }

    public ItemValues getPm5CF1() {
        return pm5CF1;
    }

    public ItemValues getPm25() {
        return pm25;
    }

    public ItemValues getPm25CF1() {
        return pm25CF1;
    }

    public ItemValues getPm10() {
        return pm10;
    }

    public ItemValues getPm10CF1() {
        return pm10CF1;
    }

    public ItemValues getHumidity() {
        return humidity;
    }

    public ItemValues getTemperature() {
        return temperature;
    }
}
