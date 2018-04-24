package com.example.omega.imageviewer.di.modules;

import com.example.omega.imageviewer.backend.AppService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

@Module(includes = {ApiModule.class})
public class AppModule {

    @Provides
    @Singleton
    AppService provideAppService() {
        return new AppService();
    }
}
