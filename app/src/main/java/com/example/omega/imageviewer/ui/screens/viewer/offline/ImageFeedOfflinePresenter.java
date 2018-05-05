package com.example.omega.imageviewer.ui.screens.viewer.offline;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.storage.base.Storage;
import com.example.omega.imageviewer.storage.base.StorageManager;
import com.example.omega.imageviewer.storage.database.Database;
import com.example.omega.imageviewer.ui.screens.viewer.base.BaseImageFeedPresenter;

import java.util.List;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

@InjectViewState
public class ImageFeedOfflinePresenter extends BaseImageFeedPresenter<ImageFeedOfflineView>
        implements Database.Callback {


    private final StorageManager mStorageManager;

    public ImageFeedOfflinePresenter() {
        mStorageManager = new StorageManager(false);
        mStorageManager.addCallback(this);
        mStorageManager.requestAllImages();
    }

    @Override
    public void onDeleteClicked() {
        mStorageManager.deleteImage(mStorageManager.getCurrentImages().get(mItemPositionLongClicked), mItemPositionLongClicked);
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

    @Override
    public void onDeleteImageEvent(@NonNull Storage.RequestEvent requestEvent, int itemPositionDeleted) {
        switch (requestEvent) {
            case SUCCESS:
                mStorageManager.getCurrentImages().remove(itemPositionDeleted);
                getViewState().notifyItemImage(itemPositionDeleted);
                break;
            case ERROR:
                getViewState().showAttentionScreen(R.string.delete_image_failed);
                break;
        }
    }

    @Override
    public void onRequestImagesEvent(@NonNull Storage.RequestEvent requestEvent, List<Image> images) {
        switch (requestEvent) {
            case SUCCESS:
                if (mStorageManager.getCurrentImages().isEmpty()) {
                    getViewState().showConfirmScreen(R.string.go_to_online_mode, R.string.cancel, R.string.yes);
                } else {
                    getViewState().updateImages(mStorageManager.getCurrentImages());
                }
                break;
            case FINISH:
                getViewState().hideLoading();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mStorageManager.removeCallback(this);
    }

    @Override
    public void onSaveImageInDatabaseEvent(@NonNull Storage.RequestSaveEvent requestSaveEvent, Image image) {

    }

    @Override
    public void onRequestImageEvent(@NonNull Storage.RequestEvent requestEvent, @Nullable Image image) {

    }

}
