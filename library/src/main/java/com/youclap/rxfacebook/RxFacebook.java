package com.youclap.rxfacebook;

import com.youclap.rxfacebook.login.FacebookLoginHandlerActivity;

import android.app.Activity;
import android.content.Intent;

public final class RxFacebook {

    private static Cenas mCenas;

    //    @NonNull
    public static void login(int value, Cenas cenas) {
        FacebookLoginHandlerActivity.start(RxFacebookInitProvider.getAppContext(), value);
        mCenas = cenas;
    }

    //Login stuff

    /**
     * Method called whenever the FacebookLoginHandlerActivity has been created or recreated and is ready to be
     * used.
     */
    //TODO make visibility package
    public static void onActivityReady(Activity activity) {

    }

    /**
     * Method called whenever the FacebookLoginHandlerActivity receives the result from FacebookActivity
     */
    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        int value = data.getIntExtra("resultValue", -1);

        //TODO check value default value

        mCenas.cenas(value);
    }

    public static void onActivityResultFailed(int requestCode, int resultCode, Intent data) {

    }

    /**
     * Method called whenever the FacebookLoginHandlerActivity has been destroyed.
     */
    //TODO make visibility package
    public static void onActivityDestroyed() {
        //TODO clear stuff
        mCenas = null;
    }

    public interface Cenas {

        void cenas(int resultValue);
    }
}
