package com.example.omega.imageviewer.ui.screens.slider;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.storage.base.StorageManager;
import com.example.omega.imageviewer.ui.screens.base.BasePresenter;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

@InjectViewState
public class ImageSliderPresenter extends BasePresenter<ImageSliderView> {

    private final static float MAX_SCALE_FOR_IMAGE = 1.0f;
    private final static float MIN_SCALE_FOR_IMAGE = 0.7f;
    private final static int TRANSITION_TIME = 70; //ms
    private final StorageManager mStorageManager;

    public ImageSliderPresenter(int position, boolean isOnlineMode) {
        getViewState().setAdapter(isOnlineMode);
        mStorageManager = new StorageManager(isOnlineMode);
        getViewState().updateImages(mStorageManager.getCurrentImages());
        getViewState().setSelection(position);
        getViewState().transformImages(MAX_SCALE_FOR_IMAGE, MIN_SCALE_FOR_IMAGE, TRANSITION_TIME);
    }

    public void onPageSelected(int position) {
        getViewState().setToolbarTitle(position, mStorageManager.getCurrentImages().size());
    }

    @Override
    protected void onConnectivityChanged(boolean availableNow) {
        //TODO added logic
    }

}
