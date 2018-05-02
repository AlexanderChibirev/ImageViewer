package com.example.omega.imageviewer.ui.screens.splash;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.transition.ChangeBounds;
import android.support.transition.Transition;
import android.text.TextUtils;
import android.view.animation.BounceInterpolator;

import com.arellomobile.mvp.InjectViewState;
import com.example.omega.imageviewer.BuildConfig;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.cloud_drive.CloudDrive;
import com.example.omega.imageviewer.models.Preferences;
import com.example.omega.imageviewer.models.UserManager;
import com.example.omega.imageviewer.tools.DefaultTransitionListener;
import com.example.omega.imageviewer.ui.screens.base.BasePresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

@InjectViewState
public class SplashPresenter extends BasePresenter<SplashView> {
    private static final long DURATION_ANIMATION = 2 * 1000; // 2 sec
    private static final long POST_DELAYED = 1000; //ms
    private static final int LIMIT_IMAGES_TO_UPLOAD = 100;
    private static final String GET_TOKEN_PATTERN = "access_token=(.*?)(&|$)";

    @Inject
    CloudDrive mCloudDrive;
    @Inject
    UserManager mUserManager;
    @Inject
    Preferences mPreferences;

    public SplashPresenter() {
        mUserManager = ImageSliderApp.getAppComponent().getUserManager();
        mCloudDrive = ImageSliderApp.getAppComponent().getCloudDrive();
        mPreferences = ImageSliderApp.getAppComponent().getPreferences();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        if (mUserManager.isAuthorized()) {
            requestImages();
        } else {
            showAuthorizationMessage();
        }
    }

    private void startPostDelayedAnimate() {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> getViewState().startAnimate(createTransition()), POST_DELAYED);
    }

    private void showAuthorizationMessage() {
        getViewState().showAuthorizationMessage(R.string.auth_message, R.string.exit, R.string.yes,
                this::onExitPressed, this::onYesPressed);
    }

    private void onYesPressed() {
        getViewState().showWebScreen(BuildConfig.AUTH_URL + BuildConfig.CLIENT_ID);
    }

    private void onExitPressed() {
        //TODO added transfer on offline mode remove finishScreen()
        getViewState().finishScreen();
    }

    private Transition createTransition() {
        return new ChangeBounds()
                .setInterpolator(new BounceInterpolator())
                .setDuration(DURATION_ANIMATION)
                .addListener(createTransitionListener());
    }

    private DefaultTransitionListener createTransitionListener() {
        return new DefaultTransitionListener() {
            @Override
            public void onTransitionEnd(@NonNull Transition transition) {
                getViewState().showFeedScreen();
            }
        };
    }

    public void onTokenUpdate(Uri data) {
        Pattern pattern = Pattern.compile(GET_TOKEN_PATTERN);
        Matcher matcher = pattern.matcher(data.toString());
        if (matcher.find()) {
            final String token = matcher.group(1);
            if (!TextUtils.isEmpty(token)) {
                mPreferences.setToken(token);
            }
        }
        requestImages();
    }

    private void requestImages() {
        mCloudDrive.requestImages(LIMIT_IMAGES_TO_UPLOAD, mCloudDrive.getImages().size());
        startPostDelayedAnimate();
    }

    public void onScreenClosed() {
        showAuthorizationMessage();
    }
}
