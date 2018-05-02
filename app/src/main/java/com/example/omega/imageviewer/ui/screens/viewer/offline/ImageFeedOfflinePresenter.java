package com.example.omega.imageviewer.ui.screens.viewer.offline;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.ui.screens.viewer.base.BaseImageFeedPresenter;
import com.example.omega.imageviewer.ui.screens.viewer.base.ImageFeedOfflineView;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

@InjectViewState
public class ImageFeedOfflinePresenter extends BaseImageFeedPresenter<ImageFeedOfflineView> {

    @Override
    protected void onRefresh() {
        super.onRefresh();
        getViewState().hideLoading(); //TODO deleted after image loader from db
    }
}
