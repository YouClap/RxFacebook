package com.youclap.rxfacebook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.lang.ref.WeakReference;

/**
 * Content provider that does not provider any content.
 * It provides an easy way to initialize this library
 */
public class RxFacebookInitProvider extends ContentProvider {

    private static WeakReference<Context> mContext;

    /**
     * Should only be available package level
     *
     * @return the context of the application
     */
    protected static Context getAppContext() {
        return mContext.get();
    }

    @Override
    public boolean onCreate() {
        mContext = new WeakReference<>(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
