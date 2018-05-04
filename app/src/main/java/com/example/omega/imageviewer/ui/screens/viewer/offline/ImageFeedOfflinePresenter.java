package com.example.omega.imageviewer.ui.screens.viewer.offline;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.storage.base.Storage;
import com.example.omega.imageviewer.storage.database.Database;
import com.example.omega.imageviewer.ui.screens.viewer.base.BaseImageFeedPresenter;

import java.util.List;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

@InjectViewState
public class ImageFeedOfflinePresenter extends BaseImageFeedPresenter<ImageFeedOfflineView>
        implements Database.Callback {

    private final Database mDatabase;

    public ImageFeedOfflinePresenter() {
        mDatabase = ImageSliderApp.getAppComponent().getDatabase();
        mDatabase.addCallback(this);
        setImages(mDatabase.getImages());
        mDatabase.requestImages(LIMIT_IMAGES_TO_UPLOAD, mDatabase.getImages().size());
    }

    @Override
    public void onDeleteClicked() {
        mDatabase.deleteImage(mDatabase.getImages().get(mItemPositionLongClicked), mItemPositionLongClicked);
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
    public void onRequestImagesEvent(Storage.RequestEvent requestEvent, List<Image> images) {
        switch (requestEvent) {
            case SUCCESS:
                updateImages(images);
                if (images.isEmpty()) {
                    getViewState().showConfirmScreen(R.string.go_to_online_mode, R.string.cancel, R.string.yes);
                } else {
                    getViewState().updateImages(images);
                }
                break;
        }
    }

    @Override
    public void onDeleteImageEvent(Storage.RequestEvent requestEvent, int itemPositionDeleted) {
        mDatabase.getImages().remove(itemPositionDeleted);
        getViewState().deletedImage(itemPositionDeleted);
    }

    @Override
    public void onSaveImageEvent(Storage.RequestEvent requestEvent, Image image, boolean isAlreadyExists) {
        if (isAlreadyExists) {
            getViewState().showAttentionScreen(R.string.image_already_exists);
        }
    }
}
