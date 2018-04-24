package com.example.omega.imageviewer.di.modules;

import com.example.omega.imageviewer.tools.cloud_drive.CloudDrive;
import com.example.omega.imageviewer.tools.cloud_drive.YandexCloudDrive;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Alexander Chibirev on 4/24/2018.
 */

@Module(includes = {ApiModule.class})
public abstract class CloudDriveModule {

    @Binds
    @Singleton
    abstract CloudDrive provideCloudDrive(YandexCloudDrive yandexCloudDrive);
}
