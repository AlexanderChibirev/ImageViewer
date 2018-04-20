package com.example.omega.imageviewer.mvp.presenters;

import com.example.omega.imageviewer.mvp.views.BaseImageView;
import com.example.omega.imageviewer.mvp.views.BaseView;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public class BaseImagePresenter<V extends BaseImageView> extends BasePresenter<V> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().updateImages();
    }
}
