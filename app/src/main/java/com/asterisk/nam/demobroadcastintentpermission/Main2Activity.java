package com.asterisk.nam.demobroadcastintentpermission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent =  getIntent();
        String myString = intent.getStringExtra(MainActivity.KEY_INTENT_NORMAL);
        String myStringBundle = intent.getExtras().getString(MainActivity.KEY_INTENT_BUNDLE);
        Cat mCat = intent.getExtras().getParcelable(MainActivity.KEY_INTENT_BUNDLE_PACERABLE);
        Log.e("datashow",myString +" --- "+myStringBundle+" --- "+mCat.getInfor());
    }
}
