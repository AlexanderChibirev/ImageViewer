package com.example.omega.imageviewer.di.modules;

import android.content.Context;

import com.example.omega.imageviewer.mvp.models.Preferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

@Module
public class PreferencesModule {

    @Provides
    @Singleton
    public Preferences providePreferences(Context context) {
        return new Preferences(context);
    }
}
