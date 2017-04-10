package me.pjq.androidiothome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HomeLauncherActivity extends AppCompatActivity {
    private TextView mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_launcher);

        mContentView = (TextView) findViewById(R.id.info);
        mContentView.setText(Utils.getDeviceInfo());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
