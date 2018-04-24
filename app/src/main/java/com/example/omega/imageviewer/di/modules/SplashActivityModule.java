package com.example.omega.imageviewer.di.modules;

import com.example.omega.imageviewer.ui.activities.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Alexander Chibirev on 4/24/2018.
 */

@Module
public abstract class SplashActivityModule {
    @ContributesAndroidInjector
    abstract SplashActivity splashActivity();
}
