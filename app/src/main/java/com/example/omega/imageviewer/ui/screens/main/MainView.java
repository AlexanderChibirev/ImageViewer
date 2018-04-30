package com.example.omega.imageviewer.ui.screens.main;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.omega.imageviewer.ui.screens.base.BaseView;

/**
 * Created by Alexander Chibirev on 4/29/2018.
 */

@StateStrategyType(OneExecutionStateStrategy.class)
public interface MainView extends BaseView {

    void showMainPage();

    void showOfflinePage();

    void showSplashScreen();
}
