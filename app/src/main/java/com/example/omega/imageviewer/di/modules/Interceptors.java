package com.example.omega.imageviewer.di.modules;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface Interceptors {

    Type value();

    enum Type {
        BASE, NETWORK // for all logs
    }

}
