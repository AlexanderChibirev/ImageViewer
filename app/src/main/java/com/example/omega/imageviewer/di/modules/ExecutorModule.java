package com.example.omega.imageviewer.di.modules;

import com.example.omega.imageviewer.tools.MainThreadExecutor;
import com.example.omega.imageviewer.tools.task.TaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

@Module
public class ExecutorModule {

    @Provides
    @Singleton
    MainThreadExecutor provideMainThreadExecutor() {
        return new MainThreadExecutor();
    }

    @Provides
    @Singleton
    ExecutorService provideExecutorService() {
        return Executors.newCachedThreadPool();
    }

    @Provides
    @Singleton
    TaskExecutor provideTaskExecutor(MainThreadExecutor mainThreadExecutor, ExecutorService executorService) {
        return new TaskExecutor(mainThreadExecutor, executorService);
    }

}