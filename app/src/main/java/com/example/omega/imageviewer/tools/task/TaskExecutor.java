package com.example.omega.imageviewer.tools.task;

import android.os.SystemClock;

import com.example.omega.imageviewer.tools.MainThreadExecutor;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

public class TaskExecutor {
    private final MainThreadExecutor mMainThreadExecutor;
    private final ExecutorService mBackgroundThreadService;

    public TaskExecutor(MainThreadExecutor mainThreadExecutor, ExecutorService backgroundThreadService) {
        mMainThreadExecutor = mainThreadExecutor;
        mBackgroundThreadService = backgroundThreadService;
    }

    public void runOnMainThread(Runnable runnable) {
        mMainThreadExecutor.execute(runnable);
    }

    public void runOnBackgroundThread(Runnable runnable) {
        mBackgroundThreadService.execute(runnable);
    }

    public <O> Task<O> runPeriodicTask(long delayInMills, long timeoutInMillis, Task.Worker<O> workTask) {
        Task<O> task = new Task<>(workTask);
        runOnBackgroundThread(() -> {
            long startTime = SystemClock.elapsedRealtime();
            O result;
            do {
                try {
                    if (task.isCanceled()) {
                        runOnMainThread(() -> task.finish(new CancellationException()));
                        return;
                    }

                    if (SystemClock.elapsedRealtime() - startTime >= timeoutInMillis) {
                        runOnMainThread(() -> task.finish(new TimeoutException()));
                        return;
                    }
                    SystemClock.sleep(delayInMills);
                    result = task.doTask();

                } catch (Exception e) {
                    e.printStackTrace();
                    result = null;
                }

            } while (result == null);

            O finalResult = result;
            runOnMainThread(() -> task.finish(finalResult));

        });
        return task;
    }

    public <O> Task<O> runTask(Task.Worker<O> taskWorker) {
        Task<O> task = new Task<>(taskWorker);
        runOnBackgroundThread(() -> {
            try {
                if (task.isCanceled()) {
                    runOnMainThread(() -> task.finish(new CancellationException()));
                    return;
                }
                O result = task.doTask();
                runOnMainThread(() -> task.finish(result));
            } catch (Exception e) {
                runOnMainThread(() -> task.finish(e));
            }
        });
        return task;
    }

}
