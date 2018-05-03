package com.example.omega.imageviewer.ui.screens.viewer.offline;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.database.Database;
import com.example.omega.imageviewer.ui.screens.viewer.base.BaseImageFeedPresenter;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

@InjectViewState
public class ImageFeedOfflinePresenter extends BaseImageFeedPresenter<ImageFeedOfflineView> {

    private final Database mDatabase;

    public ImageFeedOfflinePresenter() {
        mDatabase = ImageSliderApp.getAppComponent().getDatabase();
        getViewState().updateImages(mDatabase.getImages());
    }

    @Override
    public void onDeleteClicked() {
        mDatabase.deleteImage(mDatabase.getImages().get(mItemPositionLongClicked));
        getViewState().updateImages(mDatabase.getImages()); //TODO deleted after testing
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        getViewState().hideLoading();
    }

    @Override
    protected void onImageClick(int position) {
        super.onImageClick(position);
    }

    @Override
    protected void onImageLongClick(int position) {
        super.onImageLongClick(position);
    }

    @Override
    protected void onFullModeImageClicked() {
        super.onFullModeImageClicked();
    }

    @Override
    protected void onConnectivityChanged(boolean availableNow) {
        //TODO added logic
    }
}
