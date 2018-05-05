package com.example.omega.imageviewer.ui.screens.viewer.offline;

import android.support.annotation.NonNull;

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
public class ImageFeedOfflinePresenter extends BaseImageFeedPresenter<ImageFeedOfflineView> {

    @NonNull
    private Database mDatabase;

    public ImageFeedOfflinePresenter() {
        mDatabase = ImageSliderApp.getAppComponent().getDatabase();
        mDatabase.addCallback(this);
        mDatabase.requestAllImages();
    }

    @Override
    public void onDeleteClicked() {
        mDatabase.deleteImage(mDatabase.getCurrentImages().get(mItemPositionLongClicked), mItemPositionLongClicked);
    }

    @Override
    protected void onRefresh() {
        mDatabase.requestAllImages();
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

    protected void onOkButtonPressed() {
        //TODO added logic in feature
    }

    @Override
    public void onRequestImagesEvent(@NonNull Storage.RequestEvent requestEvent, List<Image> images) {
        super.onRequestImagesEvent(requestEvent, images);
        switch (requestEvent) {
            case SUCCESS:
                if (mDatabase.getCurrentImages().isEmpty()) {
                    getViewState().showConfirmScreen(R.string.go_to_online_mode, R.string.cancel, R.string.yes);
                } else {
                    getViewState().updateImages(mDatabase.getCurrentImages());
                }
                break;
        }
    }

    @Override
    public void onDeleteImageEvent(@NonNull Storage.RequestEvent requestEvent, Image image, int itemPositionDeleted) {
        super.onDeleteImageEvent(requestEvent, image, itemPositionDeleted);
        switch (requestEvent) {
            case SUCCESS:
                getViewState().deleteImageFromDisk(image);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDatabase.removeCallback(this);
    }

}
