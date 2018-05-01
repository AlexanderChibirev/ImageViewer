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
        void onDownloadImagesEvent(RequestEvent requestEvent, Text message); //TODO changed named fun

        void onDeleteImageEvent(RequestEvent requestEvent, Text message, int itemPositionDeleted); //TODO changed named fun
    }

    enum RequestEvent {
        SUCCESS,
        ERROR,
        FINISH
    }
}
