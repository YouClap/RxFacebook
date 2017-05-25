package com.youclap.rxfacebook;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;

public class FacebookLoginObservable extends Single<Object> {

    private SingleObserver<? super Object> mObserver;

    public FacebookLoginObservable() {
        //TODO parameters needed
    }

    @Override
    protected void subscribeActual(@NonNull SingleObserver<? super Object> observer) {
        mObserver = observer;
    }

    public void onResult(String string) {
        mObserver.onSuccess(string);
    }
}
