package com.example.omega.imageviewer.ui.screens.base;

import android.os.Bundle;
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
import com.example.omega.imageviewer.tools.NetworkChecker;
import com.example.omega.imageviewer.ui.base_ui_messages.DialogUtils;
import com.example.omega.imageviewer.ui.dialogs.confirm.ConfirmDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public abstract class BaseActivity extends MvpAppCompatActivity implements
        BaseView,
        NetworkChecker.OnConnectivityChangedListener,
        ConfirmDialogFragment.ConfirmDialogListener {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private NetworkChecker mNetworkChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNetworkChecker = new NetworkChecker(this);
    }

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

    @Override
    protected void onResume() {
        super.onResume();
        mNetworkChecker.registerListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNetworkChecker.unregisterListener(this);
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
    public void onConnectivityChanged(boolean availableNow) {
        //TODO go to offline mode if !availableNow and you not offline mode
    }

    @Override
    public void onOkButtonPressed() {
        //nothing
    }

    @Override
    public void onCancelButtonPressed() {
        //nothing
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
