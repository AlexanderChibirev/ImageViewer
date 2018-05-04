package com.example.omega.imageviewer.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;


import com.example.omega.imageviewer.storage.database.Database;
import com.example.omega.imageviewer.storage.database.ImageDao;
import com.example.omega.imageviewer.storage.database.RoomDatabase;

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
    Database provideDatabase(Context context, @NonNull final ImageDao imageDao) {
        return new RoomDatabase(context, imageDao);
    }

}
