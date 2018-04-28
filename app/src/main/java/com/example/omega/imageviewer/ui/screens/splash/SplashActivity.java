package com.example.omega.imageviewer.ui.screens.splash;

import android.content.Intent;
import android.net.Uri;
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
import com.example.omega.imageviewer.ui.dialogs.AttentionDialog;
import com.example.omega.imageviewer.ui.screens.base.BaseActivity;
import com.example.omega.imageviewer.ui.screens.viewer.ImageViewerActivity;

import butterknife.BindView;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

public class SplashActivity extends BaseActivity implements SplashView {

    private static final int REQUEST_CODE_AUTH = 101;

    @InjectPresenter
    SplashPresenter mSplashPresenter;

    @BindView(R.id.layout_splash_start)
    ConstraintLayout mConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_start);
    }

    @UiThread
    @Override
    public void startAnimate(@NonNull final ChangeBounds transition) {
        this.runOnUiThread(() -> {
            ConstraintSet newConstraintSet = new ConstraintSet();
            newConstraintSet.clone(getApplicationContext(), R.layout.activity_splash_end);
            newConstraintSet.applyTo(mConstraintLayout);
            TransitionManager.beginDelayedTransition(mConstraintLayout, transition);
        });
    }

    @Override
    public void showViewerScreen() {
        startActivity(ImageViewerActivity.createIntent(this));
        finishScreen();
    }

    @Override
    public void showWebScreen(@NonNull String url) {
        startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse(url)), REQUEST_CODE_AUTH);
    }

    @Override
    public void showAuthorizationMessage(@StringRes int message,
                                         @StringRes int negativeLabel,
                                         @StringRes int positiveLabel,
                                         AttentionDialog.OnCancelButtonListener onNegativeListener,
                                         @Nullable AttentionDialog.OnOkButtonListener onPositiveListener) {
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null) {
            mSplashPresenter.onTokenUpdate(intent.getData());
            setIntent(null);
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
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_CANCELED) {
            switch (requestCode) {
                case REQUEST_CODE_AUTH:
                    mSplashPresenter.onScreenClosed();
                    break;
                default:
                    super.onActivityResult(resultCode, resultCode, intent);
            }
        }
    }
}
