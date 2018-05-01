package com.example.omega.imageviewer.ui.screens.slider;

import com.example.omega.imageviewer.ui.screens.base.BaseImageView;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

public interface ImageSliderView extends BaseImageView {
    void transformImages(final float maxScale, final float minScale);

    void setSelection(final int position);

    void setToolbarTitle(final int position, final int sizeData);
}
