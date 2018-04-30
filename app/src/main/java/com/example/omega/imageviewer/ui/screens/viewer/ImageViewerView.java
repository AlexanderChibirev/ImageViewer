package com.example.omega.imageviewer.ui.screens.viewer;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.omega.imageviewer.ui.screens.base.BaseImageView;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@StateStrategyType(OneExecutionStateStrategy.class)
public interface ImageViewerView extends BaseImageView {

    void showImageSliderScreen(long position);
}
