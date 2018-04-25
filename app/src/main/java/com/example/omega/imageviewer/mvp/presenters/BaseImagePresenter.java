package com.example.omega.imageviewer.mvp.presenters;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.mvp.views.BaseImageView;
import com.example.omega.imageviewer.tools.cloud_drive.CloudDrive;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public class BaseImagePresenter<V extends BaseImageView> extends BasePresenter<V> {

    @NonNull
    protected CloudDrive mCloudDrive;

    public BaseImagePresenter(@NonNull CloudDrive cloudDrive) {
        mCloudDrive = cloudDrive;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void onConnectivityChanged(boolean availableNow) {
        if (!availableNow) getViewState().showToast(R.string.error_connection);
    }
}
