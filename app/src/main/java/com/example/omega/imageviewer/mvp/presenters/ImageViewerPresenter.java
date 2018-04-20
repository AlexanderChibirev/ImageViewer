package com.example.omega.imageviewer.mvp.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.mvp.models.Image;
import com.example.omega.imageviewer.mvp.views.ImageViewerView;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@InjectViewState
public class ImageViewerPresenter extends BaseImagePresenter<ImageViewerView> {

    public void onSlideClick(Image image, long position) {
        getViewState().showImageSliderScreen(image, position);
    }
}
