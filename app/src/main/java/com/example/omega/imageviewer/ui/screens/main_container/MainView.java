package com.example.omega.imageviewer.ui.screens.main_container;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.omega.imageviewer.ui.screens.base.BaseView;

/**
 * Created by Alexander Chibirev on 4/29/2018.
 */

public interface MainView extends BaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showMainPage();
}
