package com.example.omega.imageviewer.storage.base;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.models.Image;

import java.util.List;

/**
 * Created by Alexander Chibirev on 5/4/2018.
 */
public interface Storage {

    void addCallback(Callback callback);

    void removeCallback(Callback callback);

    void requestImages(final int limit, final int offSet);

    void deleteImage(@NonNull final Image image, final int position);

    void saveImage(@NonNull final Image image);

    List<Image> getImages();

    interface Callback {
        void onRequestImagesEvent(RequestEvent requestEvent, List<Image> images);

        void onDeleteImageEvent(RequestEvent requestEvent, int itemPositionDeleted);

        void onSaveImageEvent(RequestEvent requestEvent, Image image, boolean isAlreadyExists);
    }

    enum RequestEvent {
        SUCCESS,
        ERROR,
        FINISH
    }
}
