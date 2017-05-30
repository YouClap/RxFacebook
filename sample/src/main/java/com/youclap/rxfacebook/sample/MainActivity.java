package com.youclap.rxfacebook.sample;

import com.facebook.CallbackManager;
import com.facebook.login.LoginResult;
import com.youclap.rxfacebook.login.RxFacebookLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        callbackManager = CallbackManager.Factory.create();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                List<String> perm = new ArrayList<>();
                perm.add("email");
                perm.add("public_profile");

                RxFacebookLogin.logInWithReadPermissions(perm)
                        .subscribe(new Consumer<LoginResult>() {
                            @Override
                            public void accept(@NonNull LoginResult loginResult) throws Exception {
                                Log.d(LOG_TAG, "accept " + loginResult.getAccessToken());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                Log.e(LOG_TAG, "error ", throwable);
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                Log.e(LOG_TAG, "onCompleted");
                            }
                        });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
