package com.example.omega.imageviewer.ui.screens.main_container;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.ui.screens.base.BasePresenter;

/**
 * Created by Alexander Chibirev on 4/29/2018.
 */

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {
    public void onMenuMainClicked() {
        getViewState().showMainPage();
    }
}
