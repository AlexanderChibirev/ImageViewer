package com.example.omega.imageviewer.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.mvp.models.Image;
import com.example.omega.imageviewer.mvp.views.ImageSliderView;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

@InjectViewState
public class ImageSliderPresenter extends BasePresenter<ImageSliderView> {


    public ImageSliderPresenter(Image image, long position) {
        super();
    }
}
