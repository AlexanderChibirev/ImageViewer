package com.example.omega.imageviewer.ui.screens.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Text;
import com.example.omega.imageviewer.ui.utils.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public abstract class BaseActivity extends MvpAppCompatActivity implements
        BaseView {

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

    protected void setToolbarTitle(final int position, final int dataSize) {
        setToolbarTitle(Text.from(getString(
                R.string.of,
                position + 1,
                dataSize)));
    }

    protected void setToolbarTitle(@StringRes int title) {
        setToolbarTitle(Text.from(title));
    }

    private void setToolbarTitle(Text title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title.getString(getResources()));
        }
    }

    protected void showBackButton() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void showToast(@NonNull Text message) {
        Toast.makeText(this, message.getString(getResources()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAttentionScreen(int message) {
        DialogUtils.showAttentionScreen(getSupportFragmentManager(), getString(message));
    }

    @Override
    public void showConfirmScreen(@StringRes int message,
                                  @StringRes int negativeLabel,
                                  @StringRes int positiveLabel) {
        DialogUtils.showConfirmScreen(getSupportFragmentManager(),
                getString(message),
                getString(negativeLabel),
                getString(positiveLabel));
    }

}
