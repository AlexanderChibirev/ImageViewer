package com.example.omega.imageviewer.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

@Module
public abstract class ContextModule {

    @Binds
    abstract Context bindContext(Application application);

}
