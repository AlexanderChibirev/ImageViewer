package com.example.omega.imageviewer.di;


import com.example.omega.imageviewer.di.modules.PreferencesModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@Singleton
@Component(modules = {PreferencesModule.class})
public interface AppComponent {
}
