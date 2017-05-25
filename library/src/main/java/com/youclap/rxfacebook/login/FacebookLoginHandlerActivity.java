package com.youclap.rxfacebook.login;

import com.youclap.rxfacebook.RxFacebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * This activity acts has a 'middleman' between the RxFacebook class and Facebook's login stuff
 */
public class FacebookLoginHandlerActivity extends Activity {

    private static final int OPEN_FACEBOOK_REQUEST_CODE = 42;

    private static final String LOG_TAG = "FacebookLoginHandler";

    public static void start(final Context context, int value) {
        final Intent intent = new Intent(context, FacebookLoginHandlerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("value", value);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        int value = getIntent().getIntExtra("value", -1);

        if (value == -1) {
            throw new RuntimeException("invalid value");
        }

        RxFacebook.onActivityReady(this);

        Intent intent = new Intent(this, MultiplierActivity.class);
        intent.putExtra("value", value);
        startActivityForResult(intent, OPEN_FACEBOOK_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == OPEN_FACEBOOK_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                RxFacebook.onActivityResult(requestCode, resultCode, data);
            } else {
                RxFacebook.onActivityResultFailed(requestCode, resultCode, data);
            }
            finish();
        } else {
            //TODO Should we do this?
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxFacebook.onActivityDestroyed();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        RxFacebook.onActivityReady(this);
    }
}
