package com.example.omega.imageviewer.ui.screens.viewer.base;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.ui.screens.base.BaseImageView;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

@StateStrategyType(OneExecutionStateStrategy.class)
public interface BaseImageFeedView extends BaseImageView {

    void showImageSliderScreen(int position, boolean isOnlineMode);

    void showOptionsScreen(boolean isOnlineMode);
}
