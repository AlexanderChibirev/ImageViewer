package com.example.omega.imageviewer.ui.screens.viewer;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.omega.imageviewer.ui.screens.base.BaseImageView;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public interface ImageViewerView extends BaseImageView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showImageSliderScreen(long position);

}
