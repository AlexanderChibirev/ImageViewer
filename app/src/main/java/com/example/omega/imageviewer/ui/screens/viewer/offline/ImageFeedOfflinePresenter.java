package com.example.omega.imageviewer.ui.screens.viewer.offline;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.R;
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
        if (mDatabase.getImages().isEmpty()) {
            getViewState().showConfirmScreen(R.string.go_to_online_mode, R.string.cancel, R.string.yes);
        } else {
            getViewState().updateImages(mDatabase.getImages());
        }
    }

    @Override
    public void onDeleteClicked() {
        mDatabase.deleteImage(mDatabase.getImages().get(mItemPositionLongClicked));
        getViewState().updateImages(mDatabase.getImages()); //TODO deleted after testing
    }

    @Override
    protected void onRefresh() {
        getViewState().hideLoading();
    }

    @Override
    protected void onImageLongClick(int position) {
        super.onImageLongClick(position);
        getViewState().showOptionsScreen(false);
    }

    @Override
    protected void onImageClick(int position) {
        getViewState().showImageSliderScreen(position, false);
    }

    @Override
    protected void onFullModeImageClicked() {
        getViewState().showImageSliderScreen(mItemPositionLongClicked, false);
    }

    @Override
    protected void onConnectivityChanged(boolean availableNow) {
        //TODO added logic
    }

    protected void onOkButtonPressed() {

    }
}
