package com.example.omega.imageviewer.ui.screens.viewer.offline;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.ui.dialogs.confirm.ConfirmDialogFragment;
import com.example.omega.imageviewer.ui.screens.viewer.base.BaseImageFeedFragment;
import com.example.omega.imageviewer.ui.screens.viewer.base.ImageFeedAdapter;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

public class ImageFeedOfflineFragment extends BaseImageFeedFragment implements
        ImageFeedOfflineView,
        ConfirmDialogFragment.ConfirmDialogListener {

    @InjectPresenter
    ImageFeedOfflinePresenter mImageFeedOfflinePresenter;

    public static ImageFeedOfflineFragment newInstance() {
        return new ImageFeedOfflineFragment();
    }

    public ImageFeedOfflineFragment() {
        super(new ImageFeedAdapter(false));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageFeedAdapter.setOnImageItemClickListener(this);
        mRecyclerView.setAdapter(mImageFeedAdapter);
    }

    @Override
    public String getTitle() {
        return getString(R.string.menu_offline_page);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_image_viewer;
    }

    @Override
    public void onRefresh() {
        mImageFeedOfflinePresenter.onRefresh();
    }

    @Override
    public void onImageClick(int position) {
        mImageFeedOfflinePresenter.onImageClick(position);
    }

    @Override
    public void onImageLongClick(int position) {
        mImageFeedOfflinePresenter.onImageLongClick(position);
    }

    @Override
    public void onFullModeImageClicked() {
        mImageFeedOfflinePresenter.onFullModeImageClicked();
    }

    @Override
    public void onDeleteClicked() {
        mImageFeedOfflinePresenter.onDeleteClicked();
    }

    @Override
    public void onConnectivityChanged(boolean availableNow) {
        mImageFeedOfflinePresenter.onConnectivityChanged(availableNow);
    }

    @Override
    public void onOkButtonPressed() {
        mImageFeedOfflinePresenter.onOkButtonPressed(); //TODO  added logic
    }

    @Override
    public void onCancelButtonPressed() {
        //nothing
    }
}