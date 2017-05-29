package com.youclap.rxfacebook.login;

import com.facebook.login.LoginResult;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;

public class FacebookLoginObservable extends Maybe<LoginResult> {

    private MaybeObserver<? super LoginResult> mObserver;

    @Override
    protected void subscribeActual(MaybeObserver<? super LoginResult> observer) {
        mObserver = observer;
    }

    void onResult(LoginResult loginResult) {
        mObserver.onSuccess(loginResult);
    }

    void onCanceled() {
        mObserver.onComplete();
    }

    void onError(Throwable throwable) {
        mObserver.onError(throwable);
    }
}
