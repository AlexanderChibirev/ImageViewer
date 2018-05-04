package com.example.omega.imageviewer.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

@Module(includes = {ContextModule.class})
public class AppModule {

    @Singleton
    @Provides
    Realm provideDb(Application application) {
        Realm.init(application);
        return Realm.getDefaultInstance();
    }

}