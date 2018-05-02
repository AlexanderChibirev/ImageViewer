package com.example.omega.imageviewer.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.omega.imageviewer.BuildConfig;
import com.example.omega.imageviewer.database.AppDatabase;
import com.example.omega.imageviewer.models.ImageDao;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

@Module(includes = {ContextModule.class})
public class AppModule {

    @Singleton
    @Provides
    AppDatabase provideDb(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, BuildConfig.DATABASE_NAME)
                .build();
    }

    @Singleton
    @Provides
    ImageDao provideImageDao(AppDatabase appDatabase) {
        return appDatabase.imageDao();
    }

}