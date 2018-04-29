package com.example.omega.imageviewer.ui.screens.main_container;

import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.view.View;

import com.example.omega.imageviewer.ui.screens.base.BaseFragment;

/**
 * Created by Alexander Chibirev on 4/29/2018.
 */
public abstract class ScreenMenuBinderFragment extends BaseFragment {

    private DrawerArrowDrawable mDrawerArrowDrawable;

    public abstract String getTitle();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && getContext() != null) {
            actionBar.setTitle(getTitle());
            mDrawerArrowDrawable = new DrawerArrowDrawable(getContext());
            actionBar.setHomeAsUpIndicator(mDrawerArrowDrawable);
        }
    }

    public void setMenuProgress(@FloatRange(from = 0, to = 1) float progress) {
        if (progress == 1.0F) {
            mDrawerArrowDrawable.setVerticalMirror(true);
        } else if (progress == 0.0F) {
            mDrawerArrowDrawable.setVerticalMirror(false);
        }

        mDrawerArrowDrawable.setProgress(progress);
    }

    @Override
    protected void onHomePressed() {
        FragmentActivity activity = getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).showMenu();
        }
    }

}