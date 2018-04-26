package com.example.omega.imageviewer.mvp.models;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */
public class UserManager {

    private final Preferences mPreferences;

    public UserManager(Preferences preferences) {
        mPreferences = preferences;
    }

    public void logout() {
        mPreferences.setToken(null);
    }

    public boolean isAuthorized() {
        return mPreferences.isAuthorized();
    }
}
