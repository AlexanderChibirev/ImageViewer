package com.example.omega.imageviewer.ui.screens.slider;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.storage.cloud_drive.CloudDrive;
import com.example.omega.imageviewer.storage.database.Database;
import com.example.omega.imageviewer.ui.screens.base.BasePresenter;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

@InjectViewState
public class ImageSliderPresenter extends BasePresenter<ImageSliderView> {

    private final static float MAX_SCALE_FOR_IMAGE = 1.0f;
    private final static float MIN_SCALE_FOR_IMAGE = 0.7f;
    private final static int TRANSITION_TIME = 70; //ms

    @NonNull
    private CloudDrive mCloudDrive;
    @NonNull
    private Database mDatabase;
    private final boolean mIsOnlineMode;


    public ImageSliderPresenter(int position, boolean isOnlineMode) {
        getViewState().setAdapter(isOnlineMode);
        mIsOnlineMode = isOnlineMode;
        mCloudDrive = ImageSliderApp.getAppComponent().getCloudDrive();
        mDatabase = ImageSliderApp.getAppComponent().getDatabase();
        getViewState().updateImages(mIsOnlineMode ? mCloudDrive.getCurrentImages() : mDatabase.getCurrentImages());
        getViewState().setSelection(position);
        getViewState().transformImages(MAX_SCALE_FOR_IMAGE, MIN_SCALE_FOR_IMAGE, TRANSITION_TIME);
    }

    public void onPageSelected(int position) {
        getViewState().setToolbarTitle(position, mIsOnlineMode
                ? mCloudDrive.getCurrentImages().size()
                : mDatabase.getCurrentImages().size());
    }

}
