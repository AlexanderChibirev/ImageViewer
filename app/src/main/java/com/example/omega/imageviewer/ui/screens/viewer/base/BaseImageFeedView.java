package com.example.omega.imageviewer.ui.screens.viewer.base;

import com.example.omega.imageviewer.ui.screens.base.BaseImageView;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

public interface BaseImageFeedView extends BaseImageView {
    void showImageSliderScreen(int position, boolean isOnlineMode);
}
