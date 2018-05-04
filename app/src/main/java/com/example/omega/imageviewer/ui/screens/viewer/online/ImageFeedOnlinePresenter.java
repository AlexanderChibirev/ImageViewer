package com.example.omega.imageviewer.ui.screens.viewer.online;


import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.storage.base.Storage;
import com.example.omega.imageviewer.storage.cloud_drive.CloudDrive;
import com.example.omega.imageviewer.storage.database.Database;
import com.example.omega.imageviewer.ui.screens.viewer.base.BaseImageFeedPresenter;

import java.util.List;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@InjectViewState
public class ImageFeedOnlinePresenter extends BaseImageFeedPresenter<ImageFeedOnlineView>
        implements CloudDrive.Callback {

    @NonNull
    private CloudDrive mCloudDrive;
    @NonNull
    private Database mDatabase;

    public ImageFeedOnlinePresenter() {
        mCloudDrive = ImageSliderApp.getAppComponent().getCloudDrive();
        setImages(mCloudDrive.getImages());
        mDatabase = ImageSliderApp.getAppComponent().getDatabase();
        mCloudDrive.addCallback(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        int size = mCloudDrive.getImages().size();
        getViewState().updateImages(mCloudDrive.getImages());
        if (size >= LIMIT_IMAGES_TO_UPLOAD || size == 0) {
            requestImagesFromCloudDrive(LIMIT_IMAGES_TO_UPLOAD, size);
        }
    }

    private void requestImagesFromCloudDrive(final int limit, final int offSet) {
        mCloudDrive.requestImages(limit, offSet);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCloudDrive.removeCallback(this);
    }

    @Override
    public void onDeleteClicked() {
        mCloudDrive.deleteImage(mCloudDrive.getImages().get(mItemPositionLongClicked),
                mItemPositionLongClicked);
    }

    protected void onRefresh() {
        int size = mCloudDrive.getImages().size();
        requestImagesFromCloudDrive(size + LIMIT_IMAGES_TO_UPLOAD,
                0);
    }

    protected void onSaveImageClicked() {
        Image image = mCloudDrive.getImages().get(mItemPositionLongClicked);
        mDatabase.saveImage(image);
    }

    @Override
    protected void onImageLongClick(int position) {
        super.onImageLongClick(position);
        getViewState().showOptionsScreen(true);
    }

    @Override
    protected void onImageClick(int position) {
        getViewState().showImageSliderScreen(position, true);
    }

    @Override
    protected void onFullModeImageClicked() {
        getViewState().showImageSliderScreen(mItemPositionLongClicked, true);
    }

    @Override
    protected void onConnectivityChanged(boolean availableNow) {
        if (!availableNow) getViewState().showAttentionScreen(R.string.error_connection);
    }

    @Override
    public void onRequestImagesEvent(Storage.RequestEvent requestEvent, List<Image> images) {//todo maybe in base class???
        switch (requestEvent) {
            case SUCCESS:
                getViewState().updateImages(images);
                if (images.isEmpty()) {
                    getViewState().showAttentionScreen(R.string.images_empty);
                }
                break;
            case FINISH:
                getViewState().hideLoading();
                break;
            case ERROR:
                getViewState().showAttentionScreen(R.string.download_image_failed);
                break;
        }
    }

    @Override
    public void onDeleteImageEvent(Storage.RequestEvent requestEvent, int itemPositionDeleted) {
        switch (requestEvent) {
            case SUCCESS:
                mCloudDrive.getImages().remove(itemPositionDeleted);
                getViewState().deletedImage(itemPositionDeleted);
                break;
            case ERROR:
                getViewState().showAttentionScreen(R.string.delete_image_failed);
                break;
        }
    }

    @Override
    public void onSaveImageEvent(Storage.RequestEvent requestEvent, Image image, boolean isAlreadyExists) {

    }

}
