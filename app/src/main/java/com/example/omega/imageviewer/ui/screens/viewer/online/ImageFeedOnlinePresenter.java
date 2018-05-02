package com.example.omega.imageviewer.ui.screens.viewer.online;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.cloud_drive.CloudDrive;
import com.example.omega.imageviewer.database.Database;
import com.example.omega.imageviewer.models.Text;
import com.example.omega.imageviewer.ui.screens.viewer.base.BaseImageFeedPresenter;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@InjectViewState
public class ImageFeedOnlinePresenter extends BaseImageFeedPresenter<ImageFeedOnlineView> implements CloudDrive.Callback {

    @NonNull
    private CloudDrive mCloudDrive;
    @NonNull
    private Database mDatabase;


    public ImageFeedOnlinePresenter() {
        mCloudDrive = ImageSliderApp.getAppComponent().getCloudDrive();
        mDatabase = ImageSliderApp.getAppComponent().getDatabase();
        mCloudDrive.addCallback(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        int size = mCloudDrive.getImages().size();
        if (size >= LIMIT_IMAGES_TO_UPLOAD) {
            requestImagesFromCloudDrive(LIMIT_IMAGES_TO_UPLOAD, size);
        } else {
            getViewState().updateImages(mCloudDrive.getImages());
        }
    }

    private void requestImagesFromCloudDrive(final int limit, final int offSet) {
        mCloudDrive.requestImages(limit, offSet);
    }

    @Override
    public void onDownloadImagesEvent(CloudDrive.RequestEvent requestEvent, Text message) {
        switch (requestEvent) {
            case SUCCESS:
                getViewState().updateImages(mCloudDrive.getImages());
                break;
            case FINISH:
                if (mCloudDrive.getImages().isEmpty()) {
                    getViewState().showMessage(R.string.images_empty, null);
                }
                getViewState().hideLoading();
                break;
            case ERROR: //TODO check
                getViewState().showMessage(R.string.download_image_failed, null);
                break;
        }
    }

    @Override
    public void onDeleteImageEvent(CloudDrive.RequestEvent requestEvent, Text message,
                                   int itemPositionDeleted) {
        switch (requestEvent) {
            case SUCCESS:
                getViewState().deletedImage(itemPositionDeleted);
                break;
            case ERROR:
                getViewState().showMessage(R.string.delete_image_failed, null);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCloudDrive.removeCallback(this);
    }

    @Override
    public void onImageLongClick(int position) {
        super.onImageLongClick(position);
    }

    @Override
    public void onDeleteClicked() {
        mCloudDrive.deleteImage(mItemPositionLongClicked);
    }

    protected void onRefresh() {
        super.onRefresh();
        int size = mCloudDrive.getImages().size();
        requestImagesFromCloudDrive(size + LIMIT_IMAGES_TO_UPLOAD,
                0);
    }

    protected void onSaveImageClicked() {
        mDatabase.saveImage(mCloudDrive.getImages().get(mItemPositionLongClicked));
    }

    @Override
    protected void onFullModeImageClicked() {
        super.onFullModeImageClicked();
    }

    @Override
    protected void onImageClick(int position) {
        super.onImageClick(position);
    }

    @Override
    protected void onConnectivityChanged(boolean availableNow) {
        if (!availableNow) getViewState().showMessage(R.string.error_connection, null);
    }
}
