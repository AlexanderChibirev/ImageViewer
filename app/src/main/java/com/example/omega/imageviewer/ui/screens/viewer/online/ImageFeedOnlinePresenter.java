package com.example.omega.imageviewer.ui.screens.viewer.online;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.storage.base.Storage;
import com.example.omega.imageviewer.storage.base.StorageManager;
import com.example.omega.imageviewer.storage.cloud_drive.CloudDrive;
import com.example.omega.imageviewer.ui.screens.viewer.base.BaseImageFeedPresenter;

import java.util.List;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@InjectViewState
public class ImageFeedOnlinePresenter extends BaseImageFeedPresenter<ImageFeedOnlineView>
        implements CloudDrive.Callback {

    private final StorageManager mStorageManager;

    public ImageFeedOnlinePresenter() {
        mStorageManager = new StorageManager(true);
        mStorageManager.addCallback(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        int size = mStorageManager.getCurrentImages().size();
        getViewState().updateImages(mStorageManager.getCurrentImages());
        if (size >= LIMIT_IMAGES_TO_UPLOAD || size == 0) {
            requestImagesFromCloudDrive(LIMIT_IMAGES_TO_UPLOAD, size);
        }
    }

    private void requestImagesFromCloudDrive(final int limit, final int offSet) {
        mStorageManager.requestImages(limit, offSet);
    }

    @Override
    public void onRequestImagesEvent(@NonNull Storage.RequestEvent requestEvent, List<Image> images) {
        switch (requestEvent) {
            case SUCCESS:
                if (mStorageManager.getCurrentImages().isEmpty()) {
                    getViewState().showAttentionScreen(R.string.images_empty);
                }
                break;
            case ERROR:
                getViewState().showAttentionScreen(R.string.download_image_failed);
                break;
        }
    }

    @Override
    public void onDeleteImageEvent(@NonNull Storage.RequestEvent requestEvent, int itemPositionDeleted) {

    }

    @Override
    public void onRequestImageEvent(@NonNull Storage.RequestEvent requestEvent, @Nullable Image image) {

    }

    @Override
    public void onSaveImageInDatabaseEvent(@NonNull Storage.RequestSaveEvent requestSaveEvent, Image image) {
        switch (requestSaveEvent) {
            case SUCCESS:
                getViewState().saveImageOnDisk(image);
                break;
            case REQUIRED:
                getViewState().showAttentionScreen(R.string.image_already_exists);
                break;
        }
    }

    protected void onRefresh() {
        int size = mStorageManager.getCurrentImages().size();
        requestImagesFromCloudDrive(size + LIMIT_IMAGES_TO_UPLOAD,
                0);
    }

    @Override
    public void onDeleteClicked() {
        mStorageManager.deleteImage(mStorageManager.getCurrentImages().get(mItemPositionLongClicked),
                mItemPositionLongClicked);
    }

    protected void onSaveImageClicked() {
        Image image = mStorageManager.getCurrentImages().get(mItemPositionLongClicked);
        mStorageManager.saveImageInDatabase(image);
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

}
