package com.example.omega.imageviewer.mvp.views;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.transition.ChangeBounds;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.omega.imageviewer.ui.dialogs.AttentionDialog;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

public interface SplashView extends BaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void startAnimate(@NonNull ChangeBounds transition);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showViewerScreen();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showAuthorizationScreen(@StringRes int message,
                                 @StringRes int negativeLabel,
                                 @StringRes int positiveLabel,
                                 AttentionDialog.OnCancelButtonListener onNegativeListener,
                                 @Nullable AttentionDialog.OnOkButtonListener onPositiveListener);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showWebScreen(@NonNull String url);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void finishScreen();

}
