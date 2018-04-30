package com.example.omega.imageviewer.backend.call;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface PeriodicTask {
    long delayMills() default 2000; // 2 sec

    long timeoutMills() default 2 * 60 * 1000; // 2 min
}
