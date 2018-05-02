package com.example.omega.imageviewer.ui.screens.slider;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.cloud_drive.CloudDrive;
import com.example.omega.imageviewer.ui.screens.base.BasePresenter;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

@InjectViewState
//не стал разделять классы на офлайн, онлайн и базовый слайдер, так как тут не особо планирую добавлять логику, если начну, то поменяю так же как и во фрагментах
public class ImageSliderFeedPresenter extends BasePresenter<ImageSliderView> {
    private final static float MAX_SCALE_FOR_IMAGE = 1.0f;
    private final static float MIN_SCALE_FOR_IMAGE = 0.7f;
    private final static int TRANSITION_TIME = 70; //ms

    private final boolean mIsOnlineMode;
    private CloudDrive mCloudDrive;

    public ImageSliderFeedPresenter(int position, boolean isOnlineMode) {
        mIsOnlineMode = isOnlineMode;
        if (mIsOnlineMode) {
            mCloudDrive = ImageSliderApp.getAppComponent().getCloudDrive();
        } else {
            //TODO added from db
        }
        getViewState().setAdapter(mIsOnlineMode);
        getViewState().updateImages(mCloudDrive.getImages());
        getViewState().setSelection(position);
        onPageSelected(position);
        getViewState().transformImages(MAX_SCALE_FOR_IMAGE, MIN_SCALE_FOR_IMAGE, TRANSITION_TIME);
    }

    protected void onPageSelected(int position) {
        if (mIsOnlineMode) {
            getViewState().setToolbarTitle(position, mCloudDrive.getImages().size());
        } else {
            //TODO added get size from db
        }
    }
}
