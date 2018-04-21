package com.example.omega.imageviewer.mvp.views;

import android.support.annotation.NonNull;
import android.support.transition.ChangeBounds;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

public interface SplashView extends BaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void startAnimate(@NonNull ChangeBounds transition);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showViewerScreen();
}
