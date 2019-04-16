package com.asterisk.nam.demobroadcastintentpermission;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    public final static String MYMESSENGER = "hellonam";
    public final static String WIFI_ON = "Wifi is on";
    public final static String WIFI_OFF = "Wifi is off";
    public final static String BODY_MYMESSENGER = "Today, i feel so happy";
    public final static String KEY_MYMESSENGER = "Today, i feel so happy";
    private Switch mSwitchWifi;
    private WifiManager mWifiManager;
    private WifiBroadcastReceiver mBroadcastReceiver;
    private Button mButtonSend;
    private MessengerBroadcastReceiver mMessengerBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setChangeSwitch();
        clickButton();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initBroadcastReceiverLocal();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopBroadcastListenerLocal();
    }

    public void init() {
        mSwitchWifi = findViewById(R.id.switch_open_off_wifi);
        mWifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mButtonSend = findViewById(R.id.button_sendbroadcast);
    }

    public void setChangeSwitch() {

        mSwitchWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mWifiManager.setWifiEnabled(true);
                    mSwitchWifi.setText(WIFI_ON);
                } else {
                    mWifiManager.setWifiEnabled(false);
                    mSwitchWifi.setText(WIFI_OFF);
                }
            }
        });

        if (mWifiManager.isWifiEnabled()) {
            mSwitchWifi.setChecked(true);
            mSwitchWifi.setText(WIFI_ON);
        } else {
            mSwitchWifi.setChecked(false);
            mSwitchWifi.setText(WIFI_OFF);
        }
    }

    public void initBroadcastReceiverLocal(){
        mBroadcastReceiver =  new WifiBroadcastReceiver(mSwitchWifi);
        mMessengerBroadcastReceiver =  new MessengerBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(MYMESSENGER);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessengerBroadcastReceiver,intentFilter);
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver,intentFilter);
    }
    public void stopBroadcastListenerLocal(){
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessengerBroadcastReceiver);
    }
    public void sendBroadcastLocal(){
        Intent intent = new Intent();
        intent.setAction(MYMESSENGER);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MYMESSENGER,BODY_MYMESSENGER);
        intent.putExtras(bundle);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }

    public void clickButton() {
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcastLocal();
            }
        });
    }
}
