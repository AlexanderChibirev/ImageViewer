package com.example.omega.imageviewer.mvp.views;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface BaseView extends MvpView {

    long DELAY_SHOW_WAITING = 500;

    void showToast(@StringRes int message);

    void showToast(@NonNull String message);
}
