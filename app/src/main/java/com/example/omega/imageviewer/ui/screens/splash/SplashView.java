package com.example.omega.imageviewer.ui.screens.splash;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.transition.Transition;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.omega.imageviewer.ui.dialogs.confirm.ConfirmDialogFragment;
import com.example.omega.imageviewer.ui.screens.base.BaseView;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

public interface SplashView extends BaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void startAnimate(@NonNull Transition transition);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showFeedScreen();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showAuthorizationMessage(@StringRes int message,
                                  @StringRes int negativeLabel,
                                  @StringRes int positiveLabel);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showWebScreen(@NonNull String url);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void finishScreen();

}
