package com.example.omega.imageviewer.app;

import android.app.Application;

import com.example.omega.imageviewer.di.AppComponent;
import com.example.omega.imageviewer.di.DaggerAppComponent;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public class ImageSliderApp extends Application {
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = DaggerAppComponent.builder().build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

}
