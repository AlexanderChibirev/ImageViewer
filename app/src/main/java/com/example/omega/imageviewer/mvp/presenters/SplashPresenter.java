package com.example.omega.imageviewer.mvp.presenters;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.transition.ChangeBounds;
import android.support.transition.Transition;
import android.view.animation.BounceInterpolator;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.mvp.views.SplashView;
import com.example.omega.imageviewer.tools.TransitionListenerWrapper;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

@InjectViewState
public class SplashPresenter extends BasePresenter<SplashView> {
    private static final long DURATION_ANIMATION = 2 * 1000; //ms
    private static final long POST_DELAYED = 1000; //ms

    public SplashPresenter() {

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnimate();
            }
        }, POST_DELAYED);
    }

    private void startAnimate() {
        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new BounceInterpolator());
        transition.setDuration(DURATION_ANIMATION);
        transition.addListener(createTransitionListener());
        getViewState().startAnimate(transition);
    }

    private TransitionListenerWrapper createTransitionListener() {
        return new TransitionListenerWrapper() {
            @Override
            public void onTransitionEnd(@NonNull Transition transition) {
                getViewState().showViewerScreen();
            }
        };
    }
}
