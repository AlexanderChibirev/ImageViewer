package com.example.omega.imageviewer.cloud_drive;

import com.example.omega.imageviewer.mvp.models.Image;

import java.util.List;

/**
 * Created by Alexander Chibirev on 4/24/2018.
 */

public interface CloudDrive {

    void addCallback(Callback callback);

    void removeCallback(Callback callback);

    void requestImages(final int limit, final int offSet);

    List<Image> getImages();

    interface Callback {
        void onChangedStateDownloadImages(DownloadState state);
    }

    enum DownloadState {
        SUCCESS,
        ERROR,
        FINISH
    }

}
