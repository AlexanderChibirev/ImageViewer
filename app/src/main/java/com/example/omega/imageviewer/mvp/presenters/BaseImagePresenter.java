package com.example.omega.imageviewer.mvp.presenters;

import com.example.omega.imageviewer.mvp.views.BaseImageView;
import com.example.omega.imageviewer.mvp.views.BaseView;
import com.example.omega.imageviewer.tools.cloud_drive.CloudDrive;

import javax.inject.Inject;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public class BaseImagePresenter<V extends BaseImageView> extends BasePresenter<V> {

    @Inject
    CloudDrive mCloudDrive;

    public BaseImagePresenter() {

    }
}
