package com.example.omega.imageviewer.ui.screens.base;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.cloud_drive.CloudDrive;
import com.example.omega.imageviewer.models.Text;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public class BaseImagePresenter<V extends BaseImageView> extends BasePresenter<V>  //TODO changed "if else" on pattern strategy "base interface two classes"
        implements CloudDrive.Callback {

    private static final int LIMIT_IMAGES_TO_UPLOAD = 50;
    protected final boolean mIsOnlineMode;

    @NonNull
    protected CloudDrive mCloudDrive;
    protected int mItemPositionLongClicked;

    public BaseImagePresenter(@NonNull CloudDrive cloudDrive, boolean isOnlineMode) {
        mIsOnlineMode = isOnlineMode;
        mCloudDrive = cloudDrive;
        mCloudDrive.addCallback(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        if (mIsOnlineMode) {
            int size = mCloudDrive.getImages().size();
            if (size >= LIMIT_IMAGES_TO_UPLOAD) {
                requestImagesFromCloudDrive(LIMIT_IMAGES_TO_UPLOAD, size);
            } else {
                getViewState().updateImages(mCloudDrive.getImages());
            }
        } else {
            //TODO added logic loading from data base
        }
    }

    private void requestImagesFromCloudDrive(final int limit, final int offSet) {
        mCloudDrive.requestImages(limit, offSet);
    }

    public void onConnectivityChanged(boolean availableNow) {
        if (!availableNow) showToast(R.string.error_connection);//changed on pop_up
    }

    @Override
    public void onChangedStateDownloadImages(CloudDrive.State state, Text message) {
        switch (state) {
            case SUCCESS:
                getViewState().updateImages(mCloudDrive.getImages());
                break;
            case FINISH:
                if (mCloudDrive.getImages().isEmpty()) {
                    getViewState().showMessage(R.string.images_empty, null);
                }
                getViewState().hideLoading();
                break;
            case ERROR:
                getViewState().showMessage(R.string.download_image_failed, null);
                break;
        }
    }

    @Override
    public void onChangedStateDeleteImage(CloudDrive.State state, Text message,
                                          int itemPositionDeleted) {
        switch (state) {
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

    protected void onSaveImageClicked() {
        //TODO added logic save on sd
    }

    protected void onDeleteClicked() {
        if (mIsOnlineMode) {
            mCloudDrive.deleteImage(mItemPositionLongClicked);
        } else {
            //TODO added logic delete from data base
        }
    }

    protected void onRefresh() {
        if (mIsOnlineMode) {
            int size = mCloudDrive.getImages().size();
            requestImagesFromCloudDrive(size + LIMIT_IMAGES_TO_UPLOAD,
                    0);
        } else {
            //TODO added logic delete from data base
        }
    }
}
