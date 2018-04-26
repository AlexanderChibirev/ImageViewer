package com.example.omega.imageviewer.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.ChangeBounds;
import android.support.transition.TransitionManager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.mvp.presenters.SplashPresenter;
import com.example.omega.imageviewer.mvp.views.SplashView;
import com.example.omega.imageviewer.ui.dialogs.AttentionDialog;
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityResultCallback;

import butterknife.BindView;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

public class SplashActivity extends BaseActivity implements SplashView, ActivityResultCallback {

    private static final int REQUEST_CODE_AUTH = 101; // todo delete

    @InjectPresenter
    SplashPresenter mSplashPresenter;

    @BindView(R.id.splash_start_activity)
    ConstraintLayout mConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_start_activity);
    }

    @UiThread
    @Override
    public void startAnimate(@NonNull final ChangeBounds transition) {
        this.runOnUiThread(() -> {
            ConstraintSet newConstraintSet = new ConstraintSet();
            newConstraintSet.clone(getApplicationContext(), R.layout.splash_end_activity);
            newConstraintSet.applyTo(mConstraintLayout);
            TransitionManager.beginDelayedTransition(mConstraintLayout, transition);
        });
    }

    @Override
    public void showViewerScreen() {
        startActivity(ImageViewerActivity.createIntent(this));
        finish();
    }

    @Override
    public void showWebScreen(@NonNull String url) {
        OmegaIntentBuilder.from(this)
                .web(url)
                .createIntentHandler()
                .chooserTitle(R.string.web_view_title)
                .failCallback(this::webFail)
                .startActivityForResult(this);
    }

    private void webFail(Exception e) { //TODO added dialog or deleted
        finish();
    }

    @Override
    public void showAuthorizationScreen(@StringRes int message,
                                        @StringRes int negativeLabel,
                                        @StringRes int positiveLabel,
                                        AttentionDialog.OnCancelButtonListener onNegativeListener,
                                        @Nullable AttentionDialog.OnOkButtonListener onPositiveListener) {
        if (getIntent() != null && getIntent().getData() != null) {
            mSplashPresenter.onTokenUpdate();
        } else {
            mAttentionDialogDelegate.showConfirmDialog(message, negativeLabel,
                    positiveLabel, onNegativeListener, onPositiveListener, false);
        }

    }

    @Override
    public void finishScreen() {
        finish();
    }

    @Override
    public void onActivityResult(int i, Intent intent) { //TODO added message or deleted??????????????
        if (intent == null) {
            if (getIntent() != null && getIntent().getData() != null) {
                finish();
            }
        }
    }
}
