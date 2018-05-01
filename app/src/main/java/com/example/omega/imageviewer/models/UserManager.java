package com.example.omega.imageviewer.models;

import android.support.annotation.NonNull;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */
public class UserManager {

    @NonNull
    private final Preferences mPreferences;

    public UserManager(@NonNull Preferences preferences) {
        mPreferences = preferences;
    }

    public void logout() {
        mPreferences.setToken(null);
        mPreferences.setFirstRun(true);
    }

    public boolean isAuthorized() {
        return mPreferences.isAuthorized();
    }
}
