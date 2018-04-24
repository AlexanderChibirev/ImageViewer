package com.example.omega.imageviewer.tools;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

public class MainThreadExecutor implements Executor {

    @NonNull
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(@NonNull Runnable command) {
        mHandler.post(command);
    }

}
