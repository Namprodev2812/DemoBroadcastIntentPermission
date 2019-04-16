package com.asterisk.nam.demobroadcastintentpermission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Switch;

public class WifiBroadcastReceiver extends BroadcastReceiver {

    Switch mSwitch;
    WifiManager wifiManager;

    public WifiBroadcastReceiver(Switch mSwitch) {
        this.mSwitch = mSwitch;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        feelWifi(context);
    }

    public void feelWifi(Context context) {
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            mSwitch.setChecked(true);
            mSwitch.setText("Wifi is on");
        } else {
            mSwitch.setChecked(false);
            mSwitch.setText("Wifi is off");
        }
    }
}
