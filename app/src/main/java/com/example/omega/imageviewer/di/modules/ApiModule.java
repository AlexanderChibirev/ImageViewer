package com.example.omega.imageviewer.di.modules;


import com.example.omega.imageviewer.backend.api.CloudDriverApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@Module(includes = RetrofitModule.class)
public class ApiModule {

    @Provides
    @Singleton
    public CloudDriverApi provideCloudDriverApi(Retrofit retrofit) {
        return retrofit.create(CloudDriverApi.class);
    }

}
