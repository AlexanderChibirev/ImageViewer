package com.example.omega.imageviewer.mvp.views;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.omega.imageviewer.mvp.models.Image;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public interface ImageViewerView extends BaseImageView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showImagePagerScreen(@NonNull Image image, long position);

}
