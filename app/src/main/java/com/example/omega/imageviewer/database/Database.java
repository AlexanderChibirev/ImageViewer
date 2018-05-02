package com.example.omega.imageviewer.database;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.models.Image;

import java.util.List;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

public interface Database {

    List<Image> getImages();

    void saveImage(@NonNull Image image);

    void deleteImage(@NonNull Image image);

}
