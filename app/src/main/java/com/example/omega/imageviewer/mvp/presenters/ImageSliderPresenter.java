package com.example.omega.imageviewer.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.mvp.models.Image;
import com.example.omega.imageviewer.mvp.views.ImageSliderView;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

@InjectViewState
public class ImageSliderPresenter extends BaseImagePresenter<ImageSliderView> {
    private final static float MAX_SCALE_FOR_IMAGE = 1.0f;
    private final static float MIN_SCALE_FOR_IMAGE = 0.8f;

    public ImageSliderPresenter(Image image, long position) {
        getViewState().setSelection(position);
        getViewState().transformImages(MAX_SCALE_FOR_IMAGE, MIN_SCALE_FOR_IMAGE);
    }
}
