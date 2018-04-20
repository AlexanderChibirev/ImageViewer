package com.example.omega.imageviewer.ui.activities;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.mvp.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public abstract class BaseActivity extends MvpAppCompatActivity implements BaseView {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        afterSetContentView();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        afterSetContentView();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        afterSetContentView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

    protected void afterSetContentView() {
        ButterKnife.bind(this);
        initToolbar();
    }

    protected void initToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    protected void showBackButton() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void showToast(int message) {
        showToast(getString(message));
    }

    @Override
    public void showToast(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
