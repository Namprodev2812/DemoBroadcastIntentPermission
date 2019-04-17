package com.asterisk.nam.demobroadcastintentpermission;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SplashScreeen extends AppCompatActivity {

    private final static int CHECK_PER_ID = 69;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkAndRequestPermissions()) {
            goToMain();
        }
    }

    private boolean checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int writePhoneStatePermissions = ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
            int callPhoneStatePermissions = ContextCompat.checkSelfPermission(this, "android.permission.CALL_PHONE");
            List<String> listPermissionsNeeded = new ArrayList();
            if (writePhoneStatePermissions != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }
            if (callPhoneStatePermissions != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add("android.permission.CALL_PHONE");
            }
            if (listPermissionsNeeded.isEmpty()) {
                return true;
            }
            ActivityCompat.requestPermissions(this, (String[]) listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), CHECK_PER_ID);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int permission = 0;
        if (requestCode == CHECK_PER_ID) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED)
                    permission++;
            }
            if (permission == grantResults.length)
                goToMain();
            else {
                finish();
            }
        }
    }

    public void goToMain() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    MainActivity.getProfileIntent(getApplicationContext());
                } finally {
                    finish();
                }
            }
        });
        thread.start();
    }
}
