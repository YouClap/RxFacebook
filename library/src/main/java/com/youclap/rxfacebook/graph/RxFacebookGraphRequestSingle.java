package com.youclap.rxfacebook.graph;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONObject;

import android.os.Bundle;
import android.text.TextUtils;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;

public class RxFacebookGraphRequestSingle extends Single<GraphResponse> {

    private final AccessToken mAccessToken;
    private final String mFields;

    private SingleObserver<? super GraphResponse> mObserver;

    RxFacebookGraphRequestSingle(AccessToken accessToken, String... fields) {
        mAccessToken = accessToken;
        mFields = TextUtils.join(",", fields);
    }

    @Override
    protected void subscribeActual(@NonNull SingleObserver<? super GraphResponse> observer) {
        mObserver = observer;

        GraphRequest request = GraphRequest.newMeRequest(mAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                if (response.getError() == null) {
                    mObserver.onSuccess(response);
                } else {
                    mObserver.onError(response.getError().getException());
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", mFields);
        request.setParameters(parameters);
        request.executeAsync();
    }
}


