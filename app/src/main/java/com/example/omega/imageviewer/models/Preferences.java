package com.example.omega.imageviewer.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

public class Preferences {

    private static final String PREFERENCES_NAME = "settings";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_IS_FIRST_RUN = "isFirstRun";

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

    public void setFirstRun(boolean firstRun) {
        mPreferences.edit().putBoolean(KEY_IS_FIRST_RUN, firstRun).apply();
    }

    public boolean isFirstRun() {
        return mPreferences.getBoolean(KEY_IS_FIRST_RUN, true);
    }

}
