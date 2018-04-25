package com.example.omega.imageviewer.mvp.presenters;


import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.mvp.models.Image;
import com.example.omega.imageviewer.mvp.views.ImageViewerView;
import com.example.omega.imageviewer.tools.cloud_drive.CloudDrive;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@InjectViewState
public class ImageViewerPresenter extends BaseImagePresenter<ImageViewerView> {

    public ImageViewerPresenter() {
        super(ImageSliderApp.getAppComponent().getCloudDrive());
    }

    public void onSlideClick(Image image, long position) {
        getViewState().showImageSliderScreen(image, position);
    }

}
