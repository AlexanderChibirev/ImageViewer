package com.example.omega.imageviewer.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.ChangeBounds;
import android.support.transition.TransitionManager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.mvp.presenters.SplashPresenter;
import com.example.omega.imageviewer.mvp.views.SplashView;

import butterknife.BindView;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

public class SplashActivity extends BaseActivity implements SplashView {

    @InjectPresenter
    SplashPresenter mSplashPresenter;

    @BindView(R.id.splash_start_activity)
    ConstraintLayout mConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_start_activity);
    }

    @Override
    public void startAnimate(@NonNull ChangeBounds transition) {
        ConstraintSet newConstraintSet = new ConstraintSet();
        newConstraintSet.clone(this, R.layout.splash_end_activity);
        newConstraintSet.applyTo(mConstraintLayout);
        TransitionManager.beginDelayedTransition(mConstraintLayout, transition);
    }
}
