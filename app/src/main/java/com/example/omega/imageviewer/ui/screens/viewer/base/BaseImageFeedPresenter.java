package com.example.omega.imageviewer.ui.screens.viewer.base;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.models.Preferences;
import com.example.omega.imageviewer.ui.screens.base.BasePresenter;
import com.example.omega.imageviewer.ui.screens.viewer.base.BaseImageFeedView;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public class BaseImageFeedPresenter<V extends BaseImageFeedView> extends BasePresenter<V> {
    protected static final int LIMIT_IMAGES_TO_UPLOAD = 50;

    protected int mItemPositionLongClicked;

    public BaseImageFeedPresenter() {
        Preferences preferences = ImageSliderApp.getAppComponent().getPreferences();
        if (preferences.isFirstRun()) {
            getViewState().showMessage(R.string.helper_for_online_feed, null);
            preferences.setFirstRun(false);
        }
    }

    protected void onRefresh() {
        //TODO added logic
    }

    protected void onImageLongClick(int position) {
        mItemPositionLongClicked = position;
    }

}
