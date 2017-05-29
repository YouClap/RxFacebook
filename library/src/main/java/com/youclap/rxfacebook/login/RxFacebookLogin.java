package com.youclap.rxfacebook.login;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.youclap.rxfacebook.RxFacebookInitProvider;

import java.util.Collection;

/**
 * Created by renatoalmeida on 29/05/2017.
 */

public final class RxFacebookLogin {

    private static FacebookLoginObservable mLoginObservable;

    public static FacebookLoginObservable logInWithReadPermissions(Collection<String> permissions) {
        FacebookLoginHandlerActivity.start(RxFacebookInitProvider.getAppContext(), permissions);
        mLoginObservable = new FacebookLoginObservable();
        return mLoginObservable;
    }

    /**
     * Method called whenever the FacebookLoginHandlerActivity has been destroyed.
     */
    static void onActivityDestroyed() {
        //TODO should we do anything else?
        mLoginObservable = null;
    }

    // Facebook SDK methods
    static void onLoginSuccess(LoginResult loginResult) {
        mLoginObservable.onResult(loginResult);
    }

    static void onLoginCancel() {
        mLoginObservable.onCanceled();
    }

    static void onError(FacebookException exception) {
        mLoginObservable.onError(exception);
    }
}
