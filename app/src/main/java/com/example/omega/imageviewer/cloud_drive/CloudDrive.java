package com.example.omega.imageviewer.cloud_drive;

import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.models.Text;

import java.util.List;

/**
 * Created by Alexander Chibirev on 4/24/2018.
 */

public interface CloudDrive { //now only pictures, in the future and files)

    void addCallback(Callback callback);

    void removeCallback(Callback callback);

    void requestImages(final int limit, final int offSet);

    List<Image> getImages();

    void deleteImage(int itemPosition);

    interface Callback {
        void onChangedStateDownloadImages(State state, Text message);

        void onChangedStateDeleteImage(State state, Text message, int itemPositionDeleted);
    }

    enum State {
        SUCCESS,
        ERROR,
        FINISH
    }
}
