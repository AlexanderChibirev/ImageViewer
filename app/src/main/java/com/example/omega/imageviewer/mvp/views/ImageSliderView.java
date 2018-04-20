package com.example.omega.imageviewer.mvp.views;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

public interface ImageSliderView extends BaseImageView {
    void transformImages(final float maxScale, final float minScale);

    void setSelection(long position);
}
