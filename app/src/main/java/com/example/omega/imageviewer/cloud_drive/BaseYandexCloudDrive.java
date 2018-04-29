package com.example.omega.imageviewer.cloud_drive;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.backend.Error;
import com.example.omega.imageviewer.backend.ErrorException;
import com.example.omega.imageviewer.models.Text;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Alexander Chibirev on 4/24/2018.
 */

public abstract class BaseYandexCloudDrive implements CloudDrive {
    private Set<Callback> mCallbackSet = new CopyOnWriteArraySet<>();

    @Override
    public void addCallback(Callback callback) {
        mCallbackSet.add(callback);
    }

    @Override
    public void removeCallback(Callback callback) {
        mCallbackSet.remove(callback);
    }

    protected boolean isEmptyCallbacks() {
        return mCallbackSet.isEmpty();
    }

    protected void onChangedStateDownloadImages(DownloadState downloadState, Text message) {
        for (Callback callback : mCallbackSet) {
            callback.onChangedStateDownloadImages(downloadState, message);
        }
    }

    @NonNull
    protected Text getErrorMessage(@Nullable Exception e) {
        if (e instanceof ErrorException) {
            return getErrorMessage(((ErrorException) e).getError());
        }
        return Error.UNKNOWN;
    }

    @NonNull
    protected Text getErrorMessage(@Nullable Error error) {
        if (error != null) {
            return error.getMessage();
        }
        return Error.UNKNOWN;
    }
}
