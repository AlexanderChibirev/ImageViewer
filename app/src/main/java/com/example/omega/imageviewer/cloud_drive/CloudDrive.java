package com.example.omega.imageviewer.cloud_drive;

import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.models.Text;

import java.util.List;

/**
 * Created by Alexander Chibirev on 4/24/2018.
 */

public interface CloudDrive {

    void addCallback(Callback callback);

    void removeCallback(Callback callback);

    void requestImages(final int limit, final int offSet);

    List<Image> getImages();

    interface Callback { //TODO changed on callback error
        void onChangedStateDownloadImages(DownloadState state, Text message);
    }

    enum DownloadState {
        SUCCESS,
        ERROR,
        FINISH
    }

}
