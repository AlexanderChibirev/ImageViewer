package com.example.omega.imageviewer.ui.screens.viewer.offline;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.ui.screens.viewer.base.BaseImageFeedView;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

public interface ImageFeedOfflineView extends BaseImageFeedView {

    void deleteImageFromDisk(Image image);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showOnlineScreen();

}
