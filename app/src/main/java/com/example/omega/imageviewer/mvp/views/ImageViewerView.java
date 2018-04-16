package com.example.omega.imageviewer.mvp.views;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public interface ImageViewerView extends BaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showImagePagerScreen();

    void updateImages();
}
