package com.example.omega.imageviewer.ui.screens.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.models.Text;
import com.example.omega.imageviewer.utils.ImageLoadingUtils;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface BaseView extends MvpView {

    void showToast(@NonNull Text message);

    void showAttentionScreen(@StringRes int message);

    void showConfirmScreen(@StringRes int message,
                               @StringRes int negativeLabel,
                               @StringRes int positiveLabel);

}
