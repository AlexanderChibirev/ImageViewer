package com.example.omega.imageviewer.ui.screens.base;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.omega.imageviewer.models.Image;

import java.util.List;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

public interface BaseImageView extends BaseView {
    void updateImages(@NonNull List<Image> images);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showCloudDriveOptionsScreen();

    void deletedImage(int itemPositionDeleted);
}
