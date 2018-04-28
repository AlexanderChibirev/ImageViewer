package com.example.omega.imageviewer.tools;

import android.support.annotation.NonNull;
import android.support.transition.Transition;

/**
 * Created by Alexander Chibirev on 4/21/2018.
 */

public abstract class DefaultTransitionListener implements Transition.TransitionListener {
    @Override
    public void onTransitionStart(@NonNull Transition transition) {
        //nothing
    }

    @Override
    public void onTransitionEnd(@NonNull Transition transition) {
        //nothing
    }

    @Override
    public void onTransitionCancel(@NonNull Transition transition) {
        //nothing
    }

    @Override
    public void onTransitionPause(@NonNull Transition transition) {
        //nothing
    }

    @Override
    public void onTransitionResume(@NonNull Transition transition) {
        //nothing
    }
}

