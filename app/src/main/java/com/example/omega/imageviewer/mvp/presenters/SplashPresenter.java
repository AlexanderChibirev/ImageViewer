package com.example.omega.imageviewer.mvp.presenters;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.transition.ChangeBounds;
import android.support.transition.Transition;
import android.view.animation.BounceInterpolator;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.BuildConfig;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.mvp.models.UserManager;
import com.example.omega.imageviewer.mvp.views.SplashView;
import com.example.omega.imageviewer.tools.TransitionListenerWrapper;
import com.example.omega.imageviewer.tools.cloud_drive.CloudDrive;

import javax.inject.Inject;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

@InjectViewState
public class SplashPresenter extends BasePresenter<SplashView> implements CloudDrive.Callback {
    private static final long DURATION_ANIMATION = 2 * 1000; // 2 sec
    private static final long POST_DELAYED = 1000; //ms
    private static final int LIMIT_IMAGES_TO_UPLOAD = 100;

    public static final String USERNAME = "username";

    @Inject
    CloudDrive mCloudDrive;
    @Inject
    UserManager mUserManager;

    @Inject
    public SplashPresenter() {
        mUserManager = ImageSliderApp.getAppComponent().getUserManager();
        mCloudDrive = ImageSliderApp.getAppComponent().getCloudDrive();
        mCloudDrive.addCallback(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        if (mUserManager.isAuthorized()) {
            mCloudDrive.requestImages(LIMIT_IMAGES_TO_UPLOAD, mCloudDrive.getImages().size());
            final Handler handler = new Handler();
            //for correct animation
            handler.postDelayed(() -> getViewState().startAnimate(createTransition()), POST_DELAYED);
        } else {
            getViewState().showAuthorizationScreen(R.string.auth_message, R.string.exit, R.string.yes,
                    this::onExitPressed, this::onYesPressed);
        }
    }

    private void onYesPressed() {
        getViewState().showWebScreen(BuildConfig.AUTH_URL + BuildConfig.CLIENT_ID);
    }

    private void onExitPressed() {
        getViewState().finishScreen();
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

    public void onTokenUpdate() {

    }
}
