package com.example.omega.imageviewer.storage.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.omega.imageviewer.models.Image;

import java.util.List;

/**
 * Created by Alexander Chibirev on 5/4/2018.
 */
public interface Storage {

    void addCallback(Callback callback);

    void removeCallback(Callback callback);

    void deleteImage(@NonNull final Image image, final int position);

    void requestImage(@NonNull String name, @NonNull String path);

    void requestImages(final int limit, final int offSet);

    void requestAllImages();

    List<Image> getCurrentImages();

    interface Callback {
        void onRequestImagesEvent(@NonNull RequestEvent requestEvent, @Nullable List<Image> images);

        void onDeleteImageEvent(@NonNull RequestEvent requestEvent, int itemPositionDeleted);

        void onSaveImageInDatabaseEvent(@NonNull RequestSaveEvent requestSaveEvent, Image image);

        void onRequestImageEvent(@NonNull RequestEvent requestEvent, @Nullable Image image);
    }

    enum RequestEvent {
        SUCCESS,
        ERROR,
        FINISH
    }

    enum RequestSaveEvent {
        SUCCESS,
        REQUIRED,
        ERROR,
        FINISH
    }
}
