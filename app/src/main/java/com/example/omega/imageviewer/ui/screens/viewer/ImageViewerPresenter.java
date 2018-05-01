package com.example.omega.imageviewer.ui.screens.viewer;


import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.models.Text;
import com.example.omega.imageviewer.ui.screens.base.BaseImagePresenter;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@InjectViewState
public class ImageViewerPresenter extends BaseImagePresenter<ImageViewerView> {


    public ImageViewerPresenter(boolean isOnlineMode) {
        super(ImageSliderApp.getAppComponent().getCloudDrive(), isOnlineMode);
    }

    public void onImageClick(int position) {
        getViewState().showImageSliderScreen(position, mIsOnlineMode);
    }

    public void onImageLongClick(int position) {
        mItemPositionLongClicked = position;
        getViewState().showCloudDriveOptionsScreen();
    }

    public void onFullModeImageClicked() {
        getViewState().showImageSliderScreen(mItemPositionLongClicked, mIsOnlineMode);
    }

    @Override
    protected void onSaveImageClicked() {
        super.onSaveImageClicked();
        getViewState().showToast(Text.from("в разработке")); //TODO deleted and change on normal logic
    }

    @Override
    protected void onDeleteClicked() {
        super.onDeleteClicked();
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
    }
}
