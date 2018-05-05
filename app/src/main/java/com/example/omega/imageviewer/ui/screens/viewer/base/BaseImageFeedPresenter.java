package com.example.omega.imageviewer.ui.screens.viewer.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.models.Preferences;
import com.example.omega.imageviewer.storage.base.Storage;
import com.example.omega.imageviewer.ui.screens.base.BasePresenter;

import java.util.List;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public abstract class BaseImageFeedPresenter<V extends BaseImageFeedView> extends BasePresenter<V>
        implements Storage.Callback {

    protected static final int LIMIT_IMAGES_TO_UPLOAD = 50;
    protected int mItemPositionLongClicked;

    public BaseImageFeedPresenter() {
        Preferences preferences = ImageSliderApp.getAppComponent().getPreferences();
        if (preferences.isFirstRun()) {
            getViewState().showAttentionScreen(R.string.helper_for_online_feed);
            preferences.setFirstRun(false);
        }
    }

    protected abstract void onDeleteClicked();

    protected abstract void onImageClick(int position);

    protected abstract void onFullModeImageClicked();

    protected abstract void onRefresh();

    protected void onImageLongClick(int position) {
        mItemPositionLongClicked = position;
    }

    @Override
    public void onRequestImagesEvent(@NonNull Storage.RequestEvent requestEvent, List<Image> images) {
        switch (requestEvent) {
            case SUCCESS:
                getViewState().updateImages(images);
                break;
            case FINISH:
                getViewState().hideLoading();
                break;
        }
    }

    @Override
    public void onSaveImageInDatabaseEvent(@NonNull Storage.RequestSaveEvent requestSaveEvent, Image image) {
        switch (requestSaveEvent) {
            case REQUIRED:
                getViewState().showAttentionScreen(R.string.image_already_exists);
                break;
        }
    }

    @Override
    public void onDeleteImageEvent(@NonNull Storage.RequestEvent requestEvent,Image image, int itemPositionDeleted) {
        switch (requestEvent) {
            case SUCCESS:
                getViewState().notifyItemImage(itemPositionDeleted);
                break;
            case ERROR:
                getViewState().showAttentionScreen(R.string.delete_image_failed);
                break;
        }
    }

    @Override
    public void onRequestImageEvent(@NonNull Storage.RequestEvent requestEvent, @Nullable Image image) {
        //nothing
    }

}
