package com.example.omega.imageviewer.ui.screens.base;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.cloud_drive.CloudDrive;
import com.example.omega.imageviewer.models.Text;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public class BaseImagePresenter<V extends BaseImageView> extends BasePresenter<V> {

    @NonNull
    protected CloudDrive mCloudDrive;

    public BaseImagePresenter(@NonNull CloudDrive cloudDrive) {
        mCloudDrive = cloudDrive;
        mCloudDrive.addCallback(this::onChangedStateDownloadImages);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mCloudDrive.requestImages(100, mCloudDrive.getImages().size());
    }

    public void onConnectivityChanged(boolean availableNow) {
        if (!availableNow) showToast(R.string.error_connection);//changed on pop_up
    }

    private void onChangedStateDownloadImages(CloudDrive.DownloadState state, Text message) {
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
    public void onDestroy() {
        super.onDestroy();
        mCloudDrive.removeCallback(this::onChangedStateDownloadImages);
    }
}
