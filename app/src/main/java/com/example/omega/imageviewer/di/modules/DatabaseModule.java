package com.example.omega.imageviewer.di.modules;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.database.Database;
import com.example.omega.imageviewer.database.RoomDatabase;
import com.example.omega.imageviewer.database.ImageDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    Database provideDatabase(@NonNull final ImageDao imageDao) {
        return new RoomDatabase(imageDao);
    }

}
