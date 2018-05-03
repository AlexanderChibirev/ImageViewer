package com.example.omega.imageviewer.ui.screens.main;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.models.Text;
import com.example.omega.imageviewer.models.UserManager;
import com.example.omega.imageviewer.ui.screens.base.BasePresenter;

/**
 * Created by Alexander Chibirev on 4/29/2018.
 */

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    private final UserManager mUserManager;

    public MainPresenter() {
        mUserManager = ImageSliderApp.getAppComponent().getUserManager();
    }

    public void onMenuItemMainClicked() {
        getViewState().showMainPage();
    }

    public void onMenuItemOfflineClicked() {
        getViewState().showOfflinePage();
    }

    public void onMenuItemLogoutClicked() {
        mUserManager.logout();
        getViewState().showSplashScreen();
    }

}
