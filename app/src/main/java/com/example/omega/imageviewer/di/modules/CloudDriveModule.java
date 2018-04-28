package com.example.omega.imageviewer.di.modules;

import com.example.omega.imageviewer.backend.api.CloudDriverApi;
import com.example.omega.imageviewer.cloud_drive.CloudDrive;
import com.example.omega.imageviewer.cloud_drive.YandexCloudDrive;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander Chibirev on 4/24/2018.
 */

@Module(includes = {ApiModule.class})
public class CloudDriveModule {

    @Provides
    @Singleton
    public CloudDrive provideCloudDrive(CloudDriverApi cloudDriverApi) {
        return new YandexCloudDrive(cloudDriverApi);
    }
}
