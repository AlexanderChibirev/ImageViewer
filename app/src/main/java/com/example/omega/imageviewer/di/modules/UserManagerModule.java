package com.example.omega.imageviewer.di.modules;

import com.example.omega.imageviewer.mvp.models.Preferences;
import com.example.omega.imageviewer.mvp.models.UserManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

@Module(includes = {PreferencesModule.class})
public class UserManagerModule {
    @Provides
    @Singleton
    UserManager provideUserManager(Preferences preferences) {
        return new UserManager(preferences);
    }
}
