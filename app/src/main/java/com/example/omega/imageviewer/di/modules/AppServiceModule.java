package com.example.omega.imageviewer.di.modules;

import com.example.omega.imageviewer.backend.AppService;
import com.example.omega.imageviewer.backend.api.CloudDriverApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

@Module(includes = {ApiModule.class})
public class AppServiceModule {

    @Provides
    @Singleton
    AppService provideAppService(CloudDriverApi cloudDriverApi) {
        return new AppService(cloudDriverApi);
    }

}
