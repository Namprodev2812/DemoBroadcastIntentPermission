package com.asterisk.nam.demobroadcastintentpermission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class face2 extends AppCompatActivity {

    public final static String KEY_INTENT_NORMAL = "my_string";
    public final static String KEY_INTENT_BUNDLE = "my_string_bundle";
    public final static String KEY_INTENT_BUNDLE_PACERABLE = "my_string_bundle_pacerable";
    private final static Integer AGE_CAT = 3;
    private final static String DATA_INTENT_NORMAL = "Today, The Weather is very cold";
    private final static String DATA_INTENT_BUNDLE = "Today, my laptop is repaired";
    private static Cat mCat;
    private static String mString;
    private static String mStringBundle;
    private static Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face2);

        getProfileIntent(getIntent());
    }

    public Intent getProfileIntent(Intent intent) {
        mIntent = intent;
        mString = mIntent.getStringExtra(MainActivity.KEY_INTENT_NORMAL);
        mStringBundle = mIntent.getExtras().getString(MainActivity.KEY_INTENT_BUNDLE);
        mCat = mIntent.getExtras().getParcelable(MainActivity.KEY_INTENT_BUNDLE_PACERABLE);
        return mIntent;
    }

    public static void startIntentExplicit(Context context, Cat mCat) {
        Intent intent = new Intent();
        intent.setClass(context, face2.class);
        intent.putExtra(KEY_INTENT_NORMAL, DATA_INTENT_NORMAL);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_INTENT_BUNDLE, DATA_INTENT_BUNDLE);
        bundle.putParcelable(KEY_INTENT_BUNDLE_PACERABLE, mCat);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
