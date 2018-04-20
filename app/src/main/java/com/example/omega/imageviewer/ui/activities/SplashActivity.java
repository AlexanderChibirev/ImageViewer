package com.example.omega.imageviewer.ui.activities;

import android.os.Bundle;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.mvp.views.SplashView;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

public class SplashActivity extends BaseActivity implements SplashView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
    }
}
