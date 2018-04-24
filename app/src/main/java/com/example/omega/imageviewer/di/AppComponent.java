package com.example.omega.imageviewer.di;


import android.app.Application;

import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.di.modules.AppModule;
import com.example.omega.imageviewer.di.modules.CloudDriveModule;
import com.example.omega.imageviewer.di.modules.ImageSliderModule;
import com.example.omega.imageviewer.di.modules.ImageViewerModule;
import com.example.omega.imageviewer.di.modules.SplashActivityModule;

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
        CloudDriveModule.class,
        SplashActivityModule.class,
        ImageViewerModule.class,
        ImageSliderModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<ImageSliderApp> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }

}
