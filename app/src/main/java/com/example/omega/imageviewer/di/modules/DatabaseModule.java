package com.example.omega.imageviewer.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.omega.imageviewer.storage.database.Database;
import com.example.omega.imageviewer.storage.database.RealmDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

@Module(includes = ContextModule.class)
public class DatabaseModule {

    @Singleton
    @Provides
    Database provideDatabase(@NonNull final Realm realm, Context context) {
        return new RealmDatabase(realm, context);
    }

}
