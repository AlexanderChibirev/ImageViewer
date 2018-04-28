package com.example.omega.imageviewer.ui.screens.viewer;


import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.ui.screens.base.BaseImagePresenter;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@InjectViewState
public class ImageViewerPresenter extends BaseImagePresenter<ImageViewerView> {

    public ImageViewerPresenter() {
        super(ImageSliderApp.getAppComponent().getCloudDrive());
    }

    public void onSlideClick(long position) {
        getViewState().showImageSliderScreen(position);
    }

}
