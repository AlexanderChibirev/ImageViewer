package com.example.omega.imageviewer.mvp.presenters;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.transition.ChangeBounds;
import android.support.transition.Transition;
import android.view.animation.BounceInterpolator;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.mvp.views.SplashView;
import com.example.omega.imageviewer.tools.TransitionListenerWrapper;
import com.example.omega.imageviewer.tools.cloud_drive.CloudDrive;

import javax.inject.Inject;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

@InjectViewState
public class SplashPresenter extends BasePresenter<SplashView> implements CloudDrive.Callback {
    private static final long DURATION_ANIMATION = 2 * 1000; //ms
    private static final long POST_DELAYED = 1000; //ms

    @Inject
    CloudDrive mCloudDrive;

    @Inject
    public SplashPresenter() {
        mCloudDrive = ImageSliderApp.getAppComponent().getCloudDrive();
        mCloudDrive.addCallback(this); //transfer callback in ImageSlideActivity
        mCloudDrive.requestImages(100, 0); //TODo remove magic number
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        final Handler handler = new Handler();
        //for correct animation
        handler.postDelayed(() -> getViewState().startAnimate(createTransition()), POST_DELAYED);
    }

    @Override
    public void onChangedStateDownloadImages(CloudDrive.DownloadState state) {
        switch (state) {
            case ERROR:
                //todo added logic
                break;
            case FINISH:
                //todo added logic
                break;
            case SUCCESS:
                //todo added logic
                break;
        }
    }

    private ChangeBounds createTransition() {
        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new BounceInterpolator());
        transition.setDuration(DURATION_ANIMATION);
        transition.addListener(createTransitionListener());
        return transition;
    }

    private TransitionListenerWrapper createTransitionListener() {
        return new TransitionListenerWrapper() {
            @Override
            public void onTransitionEnd(@NonNull Transition transition) {
                getViewState().showViewerScreen();
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCloudDrive != null) mCloudDrive.removeCallback(this);
    }
}
