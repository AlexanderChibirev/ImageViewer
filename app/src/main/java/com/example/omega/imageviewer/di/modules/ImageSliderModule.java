package com.example.omega.imageviewer.di.modules;

import com.example.omega.imageviewer.ui.activities.ImageSliderActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Alexander Chibirev on 4/24/2018.
 */

@Module
public abstract class ImageSliderModule {

    @ContributesAndroidInjector
    abstract ImageSliderActivity imageSliderActivity();
}
