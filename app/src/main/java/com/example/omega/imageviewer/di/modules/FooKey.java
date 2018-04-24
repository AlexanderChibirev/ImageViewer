package com.example.omega.imageviewer.di.modules;

import com.example.omega.imageviewer.tools.cloud_drive.CloudDrive;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * Created by Alexander Chibirev on 4/24/2018.
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
@interface FooKey {
    Class<? extends CloudDrive> value();
}
