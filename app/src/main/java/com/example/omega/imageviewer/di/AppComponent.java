package com.example.omega.imageviewer.di;


import android.app.Application;

import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.di.modules.AppModule;
import com.example.omega.imageviewer.di.modules.AppServiceModule;
import com.example.omega.imageviewer.di.modules.CloudDriveModule;
import com.example.omega.imageviewer.di.modules.ExecutorModule;
import com.example.omega.imageviewer.di.modules.PreferencesModule;
import com.example.omega.imageviewer.di.modules.UserManagerModule;
import com.example.omega.imageviewer.models.Preferences;
import com.example.omega.imageviewer.models.UserManager;
import com.example.omega.imageviewer.cloud_drive.CloudDrive;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@Singleton
@Component(modules = {
        AppModule.class,
        ExecutorModule.class,
        AppServiceModule.class,
        PreferencesModule.class,
        CloudDriveModule.class,
        UserManagerModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<ImageSliderApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }

    CloudDrive getCloudDrive();

    UserManager getUserManager();

    Preferences getPreferences();
}
