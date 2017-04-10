package me.pjq.androidiothome;

/**
 * Created by i329817(Jianqing.Peng@sap.com) on 08/04/2017.
 */

public class ItemValues {
//           case 34:
//                   System.out.println("湿度：" + toShort(data[32], data[33]) / 10.0 + "%");
    float value;
    String name;
    String unit;

    public ItemValues(float value, String name, String unit) {
        this.value = value;
        this.name = name;
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "ItemValues{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }

    //            case 32:
//                    System.out.println("温度：" + toShort(data[30], data[31]) / 10.0 + "摄氏度");
//            case 30:
//                    System.out.println("甲醛：" + toShort(data[28], data[29]) / 1000.0 + "mg/m³");
//            case 28:
//                    System.out.println("10um颗粒数（每0.1L)：" + toShort(data[26], data[27]));
//            case 26:
//                    System.out.println("5um颗粒数（每0.1L)：" + toShort(data[24], data[25]));
//            case 24:
//                    System.out.println("2.5um颗粒数（每0.1L)：" + toShort(data[22], data[23]));
//            case 22:
//                    System.out.println("1um颗粒数（每0.1L)：" + toShort(data[20], data[21]));
//            case 20:
//                    System.out.println("0.5um颗粒数（每0.1L)：" + toShort(data[18], data[19]));
//            case 18:
//                    System.out.println("0.3um颗粒数（每0.1L)：" + toShort(data[16], data[17]));
//            case 16:
//                    System.out.println("PM10（大气环境下）：" + toShort(data[14], data[15]) + "μg/m³");
//            case 14:
//                    System.out.println("PM5（大气环境下）：" + toShort(data[12], data[13]) + "μg/m³");
//            case 12:
//                    System.out.println("PM2.5（大气环境下）：" + toShort(data[10], data[11]) + "μg/m³");
//            case 10:
//                    System.out.println("PM10（CF=1，标准颗粒物）：" + toShort(data[8], data[9]) + "μg/m³");
//            case 8:
//                    System.out.println("PM5（CF=1，标准颗粒物）：" + toShort(data[6], data[7]) + "μg/m³");
//            case 6:
//                    System.out.println("PM2.5（CF=1，标准颗粒物）：" + toShort(data[4], data[5]) + "μg/m³");
}
