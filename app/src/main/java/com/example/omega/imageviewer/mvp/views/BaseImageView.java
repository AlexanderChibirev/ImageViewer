package com.example.omega.imageviewer.mvp.views;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.mvp.models.Image;

import java.util.List;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

public interface BaseImageView extends BaseView {
    void updateImages(@NonNull List<Image> images);
}
