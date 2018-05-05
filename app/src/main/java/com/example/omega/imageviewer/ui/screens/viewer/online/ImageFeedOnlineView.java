package com.example.omega.imageviewer.ui.screens.viewer.online;

import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.ui.screens.viewer.base.BaseImageFeedView;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public interface ImageFeedOnlineView extends BaseImageFeedView { //for feature

    void saveImageOnDisk(Image image);

}
