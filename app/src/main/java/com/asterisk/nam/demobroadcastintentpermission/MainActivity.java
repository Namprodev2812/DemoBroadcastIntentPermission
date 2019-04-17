package com.asterisk.nam.demobroadcastintentpermission;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String KEY_INTENT_NORMAL = "my_string";
    public final static String KEY_INTENT_BUNDLE = "my_string_bundle";
    public final static String KEY_INTENT_BUNDLE_PACERABLE = "my_string_bundle_pacerable";
    private Button mButtonSendIntentEx, mButtonSendIntentView, mButtonSendIntentMail;
    private Cat mCat;

    public static Intent startIntentImplicitView() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.google.com.vn/"));
        return intent;
    }

    public static Intent startIntentImplicitSend() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "chuaboc");
        intent.putExtra(Intent.EXTRA_SUBJECT, "namprodev");
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        clickButton();
    }

    public void init() {
        mButtonSendIntentEx = findViewById(R.id.button_send_intent_ex);
        mButtonSendIntentView = findViewById(R.id.button_send_intent_view);
        mButtonSendIntentMail = findViewById(R.id.button_send_intent_mail);
    }

    public void clickButton() {
        mButtonSendIntentEx.setOnClickListener(this);
        mButtonSendIntentView.setOnClickListener(this);
        mButtonSendIntentMail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_send_intent_ex:
                mCat = new Cat(getString(R.string.cat_name), 3, getString(R.string.cat_ho));
                face2.startIntentExplicit(getApplicationContext(), mCat);
                break;
            case R.id.button_send_intent_view:
                if (startIntentImplicitView().resolveActivity(getApplicationContext().getPackageManager()) != null)
                    startActivity(startIntentImplicitView());
                break;
            case R.id.button_send_intent_mail:
                if (startIntentImplicitSend().resolveActivity(getApplicationContext().getPackageManager()) != null)
                    startActivity(startIntentImplicitSend());
                break;
        }
    }
}
