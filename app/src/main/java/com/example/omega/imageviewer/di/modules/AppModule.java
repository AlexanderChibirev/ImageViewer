package com.example.omega.imageviewer.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

@Module
public abstract class AppModule {

    @Binds
    abstract Context bindContext(Application application);
}