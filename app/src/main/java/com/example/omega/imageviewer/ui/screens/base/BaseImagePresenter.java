package com.example.omega.imageviewer.ui.screens.base;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.cloud_drive.CloudDrive;
import com.example.omega.imageviewer.models.Text;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public class BaseImagePresenter<V extends BaseImageView> extends BasePresenter<V>
        implements CloudDrive.Callback {

    @NonNull
    protected CloudDrive mCloudDrive;
    protected int mItemPositionLongClicked;

    public BaseImagePresenter(@NonNull CloudDrive cloudDrive) {
        mCloudDrive = cloudDrive;
        mCloudDrive.addCallback(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mCloudDrive.requestImages(100, mCloudDrive.getImages().size());//TODO remove magic number
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
                //TODO add logic
                break;
            case ERROR:
                //TODO add logic
                break;
        }
    }

    @Override
    public void onChangedStateDeleteImage(CloudDrive.State state, Text message, int itemPositionDeleted) {
        switch (state) {
            case SUCCESS:
                getViewState().deletedImage(itemPositionDeleted);
                break;
            case FINISH:
                //TODO add logic
                break;
            case ERROR:
                //TODO add logic
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCloudDrive.removeCallback(this);
    }

    protected void onFullModeImageClicked() {
        //TODO added logic
    }

    protected void onSaveImageClicked() {
        //TODO added logic save on sd
    }

    protected void onDeleteClicked() {
        mCloudDrive.deleteImage(mItemPositionLongClicked);
        //TODO added logic
    }
}
