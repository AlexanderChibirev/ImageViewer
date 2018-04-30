package com.example.omega.imageviewer.ui.screens.splash;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.ui.dialogs.confirm.ConfirmDialog;
import com.example.omega.imageviewer.ui.screens.base.BaseActivity;
import com.example.omega.imageviewer.ui.screens.main_container.MainActivity;

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
    public void startAnimate(@NonNull final Transition transition) {
        ConstraintSet newConstraintSet = new ConstraintSet();
        newConstraintSet.clone(getApplicationContext(), R.layout.activity_splash_end);
        newConstraintSet.applyTo(mConstraintLayout);
        TransitionManager.beginDelayedTransition(mConstraintLayout, transition);
    }

    @Override
    public void showViewerScreen() {
        startActivity(MainActivity.createIntent(this));
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
                                         ConfirmDialog.OnCancelButtonListener onNegativeListener,
                                         @Nullable ConfirmDialog.OnOkButtonListener onPositiveListener) {
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null) {
            mSplashPresenter.onTokenUpdate(intent.getData());
            setIntent(null);
        } else {
            mConfirmDialogDelegate.showConfirmDialog(message, negativeLabel,
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

    public static Intent createIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }
}
