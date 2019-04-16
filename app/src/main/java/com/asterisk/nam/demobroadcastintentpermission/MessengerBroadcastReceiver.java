package com.asterisk.nam.demobroadcastintentpermission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MessengerBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(MainActivity.MYMESSENGER)) {
            Toast.makeText(context, "" + intent.getExtras().getString(MainActivity.KEY_MYMESSENGER), Toast.LENGTH_SHORT).show();
        }
    }
}
