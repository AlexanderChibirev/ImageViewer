package com.example.omega.imageviewer.mvp.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

public class Preferences {

    private static final String PREFERENCES_NAME = "settings";
    private static final String KEY_TOKEN = "token";

    private SharedPreferences mPreferences;

    public Preferences(Context context) {
        mPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Nullable
    public String getToken() {
        return mPreferences.getString(KEY_TOKEN, null);
    }

    public void setToken(@Nullable String token) {
        mPreferences.edit().putString(KEY_TOKEN, token).apply();
    }


    public boolean isAuthorized() {
        return getToken() != null;
    }

}
