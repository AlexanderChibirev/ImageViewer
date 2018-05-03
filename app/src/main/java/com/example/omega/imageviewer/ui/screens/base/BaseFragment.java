package com.example.omega.imageviewer.ui.screens.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Text;
import com.example.omega.imageviewer.tools.NetworkChecker;
import com.example.omega.imageviewer.ui.utils.DialogUtils;
import com.example.omega.imageviewer.ui.dialogs.attention.AttentionDialogFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Alexander Chibirev on 4/29/2018.
 */

public abstract class BaseFragment extends MvpAppCompatFragment implements
        BaseView,
        NetworkChecker.OnConnectivityChangedListener,
        AttentionDialogFragment.OnOkClickListener {

    @LayoutRes
    protected abstract int getLayoutRes();

    private Unbinder mUnbinder;
    protected NetworkChecker mNetworkChecker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        mNetworkChecker = new NetworkChecker(getContext());
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
            }
            toolbar.setNavigationOnClickListener(v -> onHomePressed());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mNetworkChecker.registerListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mNetworkChecker.unregisterListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    protected void onHomePressed() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    protected void setSupportActionBar(Toolbar toolbar) {
        FragmentActivity activity = getActivity();
        if (activity instanceof AppCompatActivity) {
            ((AppCompatActivity) activity).setSupportActionBar(toolbar);
        }
    }

    @Nullable
    protected ActionBar getSupportActionBar() {
        FragmentActivity activity = getActivity();
        if (activity instanceof AppCompatActivity) {
            return ((AppCompatActivity) activity).getSupportActionBar();
        }
        return null;
    }

    @Override
    public void showToast(@NonNull Text message) {
        Toast.makeText(getContext(), message.getString(getResources()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOkClick() {
        //nothing
    }

    @Override
    public void showAttentionScreen(@StringRes int message) {
        DialogUtils.showAttentionScreen(this,
                getFragmentManager(),
                getString(message));
    }

    @Override
    public void showConfirmScreen(int message, int negativeLabel, int positiveLabel) {
        DialogUtils.showConfirmScreen(this,
                getFragmentManager(),
                getString(message),
                getString(negativeLabel),
                getString(positiveLabel));
    }

    @Override
    public abstract void onConnectivityChanged(boolean availableNow);

}