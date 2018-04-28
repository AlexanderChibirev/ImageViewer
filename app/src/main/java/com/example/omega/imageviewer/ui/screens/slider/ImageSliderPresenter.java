package com.example.omega.imageviewer.ui.screens.slider;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.ui.screens.base.BaseImagePresenter;

import javax.inject.Inject;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

@InjectViewState
public class ImageSliderPresenter extends BaseImagePresenter<ImageSliderView> {
    private final static float MAX_SCALE_FOR_IMAGE = 1.0f;
    private final static float MIN_SCALE_FOR_IMAGE = 0.8f;

    @Inject
    public ImageSliderPresenter(long position) {
        super(ImageSliderApp.getAppComponent().getCloudDrive());
        getViewState().setSelection(position);
        getViewState().transformImages(MAX_SCALE_FOR_IMAGE, MIN_SCALE_FOR_IMAGE);
    }
}
