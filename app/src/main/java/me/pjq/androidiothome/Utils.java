package me.pjq.androidiothome;

import android.os.Build;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by i329817(Jianqing.Peng@sap.com) on 18/03/2017.
 */

public class Utils {

    public static String getDeviceInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Build.HARDWARE + '\n');
        stringBuilder.append(Build.HOST + '\n');
        stringBuilder.append(Build.DEVICE + '\n');
        stringBuilder.append(Build.MANUFACTURER + '\n');
        stringBuilder.append(Build.MODEL + '\n');
        stringBuilder.append(Build.BRAND + '\n');
        stringBuilder.append(Build.VERSION.SDK_INT + '\n');
        stringBuilder.append(Build.VERSION.BASE_OS + '\n');
        stringBuilder.append(Build.VERSION.RELEASE + '\n');
        stringBuilder.append(Build.VERSION.CODENAME + '\n');
        stringBuilder.append(Build.VERSION.SECURITY_PATCH + '\n');
        stringBuilder.append(Build.VERSION.INCREMENTAL + '\n');
        stringBuilder.append(Build.VERSION.PREVIEW_SDK_INT + '\n');
        List<String> ipList = getLocalIpAddress();
        for (String ip : ipList) {
            stringBuilder.append(ip + '\n');
        }

        return stringBuilder.toString();
    }

    private static List<String> getLocalIpAddress() {
        List<String> ipList = new ArrayList<>();
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ipList.add(inetAddress.getHostAddress().toString());
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("error", ex.toString());
        }

        return ipList;
    }
}
