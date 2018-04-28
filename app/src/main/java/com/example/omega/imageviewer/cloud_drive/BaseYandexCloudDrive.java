package com.example.omega.imageviewer.cloud_drive;

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

    protected void onChangedStateDownloadImages(DownloadState downloadState) {
        for (Callback callback : mCallbackSet) {
            callback.onChangedStateDownloadImages(downloadState);
        }
    }
}
