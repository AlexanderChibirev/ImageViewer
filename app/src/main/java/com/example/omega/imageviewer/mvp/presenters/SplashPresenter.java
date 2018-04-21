package com.example.omega.imageviewer.mvp.presenters;

import android.support.transition.ChangeBounds;
import android.view.animation.OvershootInterpolator;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.mvp.views.SplashView;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

@InjectViewState
public class SplashPresenter extends BasePresenter<SplashView> {
    private static final long DURATION_ANIMATED = 2 * 1000; // 2 sec

    public SplashPresenter() {
        startAnimate();
    }

    private void startAnimate() {
        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new OvershootInterpolator());
        transition.setDuration(DURATION_ANIMATED);
        getViewState().startAnimate(transition);
    }
}
