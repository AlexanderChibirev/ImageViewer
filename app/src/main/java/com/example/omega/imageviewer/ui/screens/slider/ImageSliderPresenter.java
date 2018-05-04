package com.example.omega.imageviewer.ui.screens.slider;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.storage.cloud_drive.CloudDrive;
import com.example.omega.imageviewer.storage.database.Database;
import com.example.omega.imageviewer.ui.screens.base.BasePresenter;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

@InjectViewState
//не стал разделять классы на офлайн, онлайн и базовый слайдер, так как тут не особо
//планирую добавлять логику, если начну, то поменяю так же как и во фрагментах
//можно конечно было еще соеденить классы клауда и базы данных)
//TODO в будущем объеденить в общий класс слауд и базу данных

public class ImageSliderPresenter extends BasePresenter<ImageSliderView> {

    private final static float MAX_SCALE_FOR_IMAGE = 1.0f;
    private final static float MIN_SCALE_FOR_IMAGE = 0.7f;
    private final static int TRANSITION_TIME = 70; //ms

    private final boolean mIsOnlineMode;

    private CloudDrive mCloudDrive;
    private Database mDatabase;

    public ImageSliderPresenter(int position, boolean isOnlineMode) {
        mIsOnlineMode = isOnlineMode;
        getViewState().setAdapter(mIsOnlineMode);
        mCloudDrive = ImageSliderApp.getAppComponent().getCloudDrive();
        mDatabase = ImageSliderApp.getAppComponent().getDatabase();
        getViewState().updateImages(mIsOnlineMode ? mCloudDrive.getImages() : mDatabase.getImages()); //TODO after add database
        getViewState().setSelection(position);
        getViewState().transformImages(MAX_SCALE_FOR_IMAGE, MIN_SCALE_FOR_IMAGE, TRANSITION_TIME);
    }

    public void onPageSelected(int position) {
        getViewState().setToolbarTitle(position, mIsOnlineMode
                ? mCloudDrive.getImages().size()
                : mDatabase.getImages().size()); //TODO after add database
    }

    @Override
    protected void onConnectivityChanged(boolean availableNow) {
        if (mIsOnlineMode) {

        }
        //TODO added logic
    }

}
