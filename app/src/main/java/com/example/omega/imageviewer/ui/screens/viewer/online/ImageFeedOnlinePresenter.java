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
        implements Storage.Callback {

    @NonNull
    private CloudDrive mCloudDrive; //хотел сделать менеджер общий для клауда и базы данных,
    // но потом что-то передумал, решил пока так оставить, в будущем это легко изменить
    @NonNull
    private Database mDatabase;

    public ImageFeedOnlinePresenter() {
        mCloudDrive = ImageSliderApp.getAppComponent().getCloudDrive();
        mDatabase = ImageSliderApp.getAppComponent().getDatabase();
        mCloudDrive.addCallback(this);
        mDatabase.addCallback(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        int size = mCloudDrive.getCurrentImages().size();
        getViewState().updateImages(mCloudDrive.getCurrentImages());
        if (size >= LIMIT_IMAGES_TO_UPLOAD || size == 0) {
            requestImagesFromCloudDrive(LIMIT_IMAGES_TO_UPLOAD, size);
        }
    }

    private void requestImagesFromCloudDrive(final int limit, final int offSet) {
        mCloudDrive.requestImages(limit, offSet);
    }

    @Override
    public void onRequestImagesEvent(@NonNull Storage.RequestEvent requestEvent, List<Image> images) {
        super.onRequestImagesEvent(requestEvent, images);
        switch (requestEvent) {
            case SUCCESS:
                if (mCloudDrive.getCurrentImages().isEmpty()) {
                    getViewState().showAttentionScreen(R.string.images_empty);
                }
                break;
            case ERROR:
                if (mDatabase.getCurrentImages().isEmpty()) {
                    getViewState().showAttentionScreen(R.string.download_image_failed);
                } else {
                    getViewState().showConfirmScreen(R.string.go_to_offline_mode, R.string.cancel, R.string.yes);
                }
                break;
        }
    }

    @Override
    public void onSaveImageInDatabaseEvent(@NonNull Storage.RequestSaveEvent requestSaveEvent, Image image) {
        super.onSaveImageInDatabaseEvent(requestSaveEvent, image);
        switch (requestSaveEvent) {
            case SUCCESS:
                getViewState().saveImageOnDisk(image);
                break;
        }
    }

    protected void onRefresh() {
        int size = mCloudDrive.getCurrentImages().size();
        requestImagesFromCloudDrive(size + LIMIT_IMAGES_TO_UPLOAD,
                0);
    }

    @Override
    public void onDeleteClicked() {
        mCloudDrive.deleteImage(mCloudDrive.getCurrentImages().get(mItemPositionLongClicked),
                mItemPositionLongClicked);
    }

    protected void onSaveImageClicked() {
        Image image = mCloudDrive.getCurrentImages().get(mItemPositionLongClicked);
        mDatabase.saveImageInDatabase(image);
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
    public void onDestroy() {
        super.onDestroy();
        mCloudDrive.removeCallback(this);
    }

    protected void onOkButtonPressed() {
        getViewState().showOfflineScreen();
    }

}
