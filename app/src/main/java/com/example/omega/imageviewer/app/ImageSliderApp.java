package com.example.omega.imageviewer.app;

import com.example.omega.imageviewer.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public class ImageSliderApp extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
