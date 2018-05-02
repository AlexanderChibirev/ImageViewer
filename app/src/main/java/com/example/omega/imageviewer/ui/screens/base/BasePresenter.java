package com.example.omega.imageviewer.ui.screens.base;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpPresenter;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Text;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

public class BasePresenter<T extends BaseView> extends MvpPresenter<T> {
    protected void showToast(@StringRes int message) {
        getViewState().showToast(Text.from(message));
    }

    public void onConnectivityChanged(boolean availableNow) {
        if (!availableNow) showToast(R.string.error_connection);//TODO changed on pop_up
    }
}
