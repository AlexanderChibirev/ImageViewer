package com.example.omega.imageviewer.ui.screens.base;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.omega.imageviewer.models.Text;
import com.example.omega.imageviewer.ui.dialogs.attention.AttentionDialog;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface BaseView extends MvpView {

    long DELAY_SHOW_WAITING = 500; // TODO for feature waiting

    void showToast(@NonNull Text message);

    void showErrorMessage(@StringRes int message, AttentionDialog.OnOkClickListener onOkClickListener);

}
