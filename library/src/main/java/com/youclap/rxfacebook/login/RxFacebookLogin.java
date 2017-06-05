package com.youclap.rxfacebook.login;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.youclap.rxfacebook.RxFacebookInitProvider;

import java.util.Collection;

public final class RxFacebookLogin {

    private static RxFacebookLoginMaybe mLoginObservable;

    public static RxFacebookLoginMaybe logInWithReadPermissions(Collection<String> permissions) {
        FacebookLoginHandlerActivity.start(RxFacebookInitProvider.getAppContext(), permissions);
        mLoginObservable = new RxFacebookLoginMaybe();
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
