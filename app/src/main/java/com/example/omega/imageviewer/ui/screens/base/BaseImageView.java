package com.example.omega.imageviewer.ui.screens.base;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.models.Image;

import java.util.List;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

public interface BaseImageView extends BaseView {
    void updateImages(@NonNull List<Image> images);

    void deletedImage(int itemPositionDeleted);

    void hideSwipeLoading();
}
