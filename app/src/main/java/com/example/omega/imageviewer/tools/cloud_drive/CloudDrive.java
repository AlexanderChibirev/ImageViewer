package com.example.omega.imageviewer.tools.cloud_drive;

/**
 * Created by Alexander Chibirev on 4/24/2018.
 */

public interface CloudDrive {

    void addCallback(Callback callback);

    void removeCallback(Callback callback);

    void requestImages(final int limit, final int offSet);

    interface Callback {
        void onChangedStateDownloadImages(DownloadState state);
    }

    enum DownloadState {
        SUCCESS,
        ERROR,
        FINISH
    }

}
