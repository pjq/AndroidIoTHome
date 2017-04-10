package me.pjq.androidiothome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.things.pio.PeripheralManagerService;
import com.google.android.things.pio.UartDevice;
import com.google.android.things.pio.UartDeviceCallback;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class WeatherMonitorActivity extends AppCompatActivity {
    private static final String TAG = "WeatherMonitorActivity";
    private TextView mContentView;
    private TextView values;
    PeripheralManagerService manager;
    UartDevice uartDevice;
    UartCallback uartCallback;
    PMS5003T pms5003T;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_launcher);

        mContentView = (TextView) findViewById(R.id.info);
        values = (TextView) findViewById(R.id.values);

        mContentView.setText(Utils.getDeviceInfo());

        initUartDevice();
        pms5003T = new PMS5003T();
        pms5003T.setPms5003TStrings(new PMS5003TStrings(getApplicationContext()));
    }

    private void initUartDevice() {
        manager = new PeripheralManagerService();
        uartCallback = new UartCallback();
        List<String> uartDeviceList = getUARTDeviceList();

        if (null != uartDeviceList && uartDeviceList.size() > 0) {
            String uartDeviceName = uartDeviceList.get(0);
            uartDevice = openUART(uartDeviceName);

//            if (null != uartDevice) {
//                try {
//                    configureUartFrame(uartDevice);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

            //readUart(uartDevice);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    private void updateValues(List<ItemValues> itemValues) {
        if (null == itemValues) {
            return;
        }

        values.setText(new Date().toLocaleString() + '\n');
        values.append(new Date().toString() + '\n');
        for (ItemValues itemValue : itemValues) {
            values.append(itemValue.getName() + ": " + itemValue.getValue() + " " + itemValue.getUnit() + '\n');
            Log.i(TAG, itemValue.getName() + ": " + itemValue.getValue() + " " + itemValue.getUnit());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (null != uartDevice) {
            try {
                uartDevice.registerUartDeviceCallback(uartCallback);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (null != uartDevice) {
            uartDevice.unregisterUartDeviceCallback(uartCallback);
        }
    }

    public void configureUartFrame(UartDevice uart) throws IOException {
        // Configure the UART port
        Log.i(TAG, "configureUartFrame");
        uart.setBaudrate(115200);
        uart.setDataSize(8);
        uart.setParity(UartDevice.PARITY_NONE);
        uart.setStopBits(1);
    }

    private List<String> getUARTDeviceList() {
        List<String> uartDeviceList = manager.getUartDeviceList();

        return uartDeviceList;
    }

    private UartDevice openUART(String uartDeviceName) {
        Log.i(TAG, "openUART: " + uartDeviceName);
        UartDevice uartDevice = null;
        try {
            uartDevice = manager.openUartDevice(uartDeviceName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uartDevice;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != uartDevice) {
            try {
                uartDevice.close();
                uartDevice = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        manager = null;
    }

    class UartCallback extends UartDeviceCallback {
        @Override
        public boolean onUartDeviceDataAvailable(UartDevice uart) {
            Log.i(TAG, "onUartDeviceDataAvailable: " + uart);
//            readUartBuffer(uart);
            readUart(uart);

            return true;
        }

        @Override
        public void onUartDeviceError(UartDevice uart, int error) {
            Log.e(TAG, "error: " + error);
        }
    }

    private void readUart(UartDevice uartDevice) {
        Log.i(TAG, "readUart: " + uartDevice);
        int maxCount = 256;

        byte[] buffer = new byte[maxCount];

        try {
            int count = 0;
            while ((count = uartDevice.read(buffer, buffer.length)) > 0) {
                String data = byteToArray(buffer);
                Log.i(TAG, "Read bytes " + count + " " + data);

                updateValues(pms5003T.parseData(buffer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String byteToArray(byte[] data) {
        String result = "";
        for (int i = 0; i < data.length; i++) {
            result += Integer.toHexString((data[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3);
        }
        return result;
    }
}
