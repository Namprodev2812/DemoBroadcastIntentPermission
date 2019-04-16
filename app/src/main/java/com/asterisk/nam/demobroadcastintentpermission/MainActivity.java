package com.asterisk.nam.demobroadcastintentpermission;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {


    private final static Integer AGE_CAT = 3;
    public final static String KEY_INTENT_NORMAL = "my_string";
    private final static String DATA_INTENT_NORMAL = "Today, The Weather is very cold";
    public final static String KEY_INTENT_BUNDLE = "my_string_bundle";
    private final static String DATA_INTENT_BUNDLE = "Today, my laptop is repaired";
    public final static String KEY_INTENT_BUNDLE_PACERABLE = "my_string_bundle_pacerable";
    private Button mButtonSendIntentEx,mButtonSendIntentView,mButtonSendIntentMail;
    private Cat mCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        clickButton();
    }

    public void init() {
        mButtonSendIntentEx = findViewById(R.id.button_sendintentex);
        mButtonSendIntentView = findViewById(R.id.button_sendintentview);
        mButtonSendIntentMail = findViewById(R.id.button_sendintentmail);
    }

    public void clickButton() {
        mButtonSendIntentEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCat = new Cat(getString(R.string.cat_name),3,getString(R.string.cat_ho));
                startIntentExplicit();
            }
        });
        mButtonSendIntentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntentImplicitView();
            }
        });
        mButtonSendIntentMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntentImplicitSend();
            }
        });
    }

    public void startIntentExplicit(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,Main2Activity.class);
        intent.putExtra(KEY_INTENT_NORMAL,DATA_INTENT_NORMAL);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_INTENT_BUNDLE,DATA_INTENT_BUNDLE);
        bundle.putParcelable(KEY_INTENT_BUNDLE_PACERABLE,mCat);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startIntentImplicitView(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.google.com.vn/"));
        if(intent.resolveActivity(getPackageManager())!= null)
        startActivity(intent);
    }
    public void startIntentImplicitSend(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,"chuaboc");
        intent.putExtra(Intent.EXTRA_SUBJECT,"namprodev");
        if(intent.resolveActivity(getPackageManager())!= null)
            startActivity(intent);
    }
}
