package com.example.omega.imageviewer.storage.database;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.storage.base.Storage;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

public interface Database extends Storage {
    void requestAllImages();
    Image getImageByName(@NonNull String name, @NonNull String path);
}
