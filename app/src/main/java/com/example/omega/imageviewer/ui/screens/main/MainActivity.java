package com.example.omega.imageviewer.ui.screens.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.ui.screens.base.BaseActivity;
import com.example.omega.imageviewer.ui.screens.splash.SplashActivity;
import com.example.omega.imageviewer.ui.screens.viewer.base.BaseImageFeedFragment;
import com.example.omega.imageviewer.ui.screens.viewer.offline.ImageFeedOfflineFragment;
import com.example.omega.imageviewer.ui.screens.viewer.online.ImageFeedOnlineFragment;
import com.omega_r.libs.navigationmenu.ContentMenuLayout;
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Alexander Chibirev on 4/29/2018.
 */

public class MainActivity extends BaseActivity
        implements MainView,
        BaseImageFeedFragment.onChangePageListener {

    private final ImageFeedOnlineFragment mImageFeedOnlineFragment = ImageFeedOnlineFragment.newInstance();
    private final ImageFeedOfflineFragment mImageFeedOfflineFragment = ImageFeedOfflineFragment.newInstance();

    @InjectPresenter
    MainPresenter mMainPresenter;

    @BindView(R.id.layout_container)
    ContentMenuLayout mContainerView;

    public static Intent createIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainerView.setOnProgressMenuChangedListener(this::onProgressMenuChanged);
    }

    @OnClick({R.id.textview_menu_item_online,
            R.id.textview_menu_item_offline,
            R.id.textview_menu_item_logout})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textview_menu_item_online:
                mMainPresenter.onMenuItemMainClicked();
                break;
            case R.id.textview_menu_item_offline:
                mMainPresenter.onMenuItemOfflineClicked();
                break;
            case R.id.textview_menu_item_logout:
                mMainPresenter.onMenuItemLogoutClicked();
                break;
        }
    }

    private void onProgressMenuChanged(float progress) {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.framelayout_fragment_container);
        if (fragment instanceof ScreenMenuBinderFragment) {
            ((ScreenMenuBinderFragment) fragment).setMenuProgress(progress);
        }
    }

    private void showPage(Fragment fragment) {
        showPage(fragment, true);
    }

    private void showPage(Fragment fragment, boolean hideMenu) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.framelayout_fragment_container);
        if (currentFragment == null || !fragment.getClass().getCanonicalName()
                .equals(currentFragment.getClass().getCanonicalName())) {
            updatePage(fragment, hideMenu);
        } else if (hideMenu) {
            mContainerView.hideMenu();
        }
    }

    private void updatePage(Fragment fragment, boolean hideMenu) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.framelayout_fragment_container, fragment)
                .commitAllowingStateLoss();

        if (hideMenu) {
            mContainerView.hideMenu();
        }
    }

    public void showMenu() {
        mContainerView.showMenu();
    }

    @Override
    public void showOnlinePage() {
        showPage(mImageFeedOnlineFragment);
    }

    @Override
    public void showOfflinePage() {
        showPage(mImageFeedOfflineFragment);
    }

    @Override
    public void onChangeOnlinePageClicked() {
        showOnlinePage();
    }

    @Override
    public void onChangeOfflinePageClicked() {
        showOfflinePage();
    }

    @Override
    public void showSplashScreen() {
        OmegaIntentBuilder.from(this)
                .activity(SplashActivity.class)
                .startActivity();
        finish();
    }

}
