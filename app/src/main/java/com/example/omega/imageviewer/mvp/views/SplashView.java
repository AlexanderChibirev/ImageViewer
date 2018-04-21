package com.example.omega.imageviewer.mvp.views;

import android.support.annotation.NonNull;
import android.support.transition.ChangeBounds;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

public interface SplashView extends BaseView {
    void startAnimate(@NonNull ChangeBounds transition);
}
