package com.youclap.rxfacebook.login;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This activity acts has a 'middleman' between the RxFacebook class and Facebook's login stuff
 */
public class FacebookLoginHandlerActivity extends Activity implements FacebookCallback<LoginResult> {

    private static final String PERMISSIONS_KEY = "FacebookLoginHandlerActivity:Permissions";

    private static final String LOG_TAG = "FacebookLoginHandler";

    private CallbackManager mCallbackManager;

    public static void start(final Context context, Collection<String> permissions) {
        final Intent intent = new Intent(context, FacebookLoginHandlerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putStringArrayListExtra(PERMISSIONS_KEY, new ArrayList<>(permissions));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        ArrayList<String> permissions = getIntent().getStringArrayListExtra(PERMISSIONS_KEY);

        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager, this);
        LoginManager.getInstance().logInWithReadPermissions(this, permissions);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxFacebookLogin.onActivityDestroyed();
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        RxFacebookLogin.onLoginSuccess(loginResult);
    }

    @Override
    public void onCancel() {
        RxFacebookLogin.onLoginCancel();
    }

    @Override
    public void onError(FacebookException exception) {
        RxFacebookLogin.onError(exception);
    }
}
