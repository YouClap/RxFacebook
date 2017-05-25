package com.youclap.rxfacebook;

import com.youclap.rxfacebook.login.FacebookLoginHandlerActivity;

import android.app.Activity;
import android.content.Intent;

public final class RxFacebook {

    private static FacebookLoginObservable mLoginObservable;

    //    @NonNull
    public static FacebookLoginObservable login(int value) {
        FacebookLoginHandlerActivity.start(RxFacebookInitProvider.getAppContext(), value);
        mLoginObservable = new FacebookLoginObservable();
        return mLoginObservable;
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

        mLoginObservable.onResult("" + value);
        mLoginObservable = null;
    }

    public static void onActivityResultFailed(int requestCode, int resultCode, Intent data) {
        //TODO not sure if worth to have this method
    }

    /**
     * Method called whenever the FacebookLoginHandlerActivity has been destroyed.
     */
    //TODO make visibility package
    public static void onActivityDestroyed() {
        //TODO clear stuff
        if (mLoginObservable != null) {
            //Should dispose?
        }
    }
}
