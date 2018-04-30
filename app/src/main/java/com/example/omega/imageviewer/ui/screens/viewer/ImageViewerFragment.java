package com.example.omega.imageviewer.ui.screens.viewer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.ui.dialogs.cloud_drive_options.OptionsDialog;
import com.example.omega.imageviewer.ui.dialogs.cloud_drive_options.OptionsDialogDelegate;
import com.example.omega.imageviewer.ui.dialogs.cloud_drive_options.OptionsDialogDelegateImpl;
import com.example.omega.imageviewer.ui.screens.main_container.ScreenMenuBinderFragment;
import com.example.omega.imageviewer.ui.screens.slider.ImageSliderActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Alexander Chibirev on 4/29/2018.
 */

public class ImageViewerFragment extends ScreenMenuBinderFragment implements
        ImageViewerView,
        ImageViewerAdapter.OnImageClickListener,
        OptionsDialog.OnClickListener {

    @InjectPresenter
    ImageViewerPresenter mImageViewerPresenter;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private final ImageViewerAdapter mImageViewerAdapter = new ImageViewerAdapter();
    private OptionsDialogDelegate mOptionsDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageViewerAdapter.setOnImageItemClickListener(this);
        mRecyclerView.setAdapter(mImageViewerAdapter);
        mOptionsDialog = new OptionsDialogDelegateImpl(getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mOptionsDialog.onDestroy();
    }

    @Override
    public String getTitle() {
        return getString(R.string.app_name);
    }

    @Override
    public void showImageSliderScreen(long position) {
        startActivity(ImageSliderActivity.createIntent(getContext(), position));
    }

    @Override
    public void updateImages(@NonNull List<Image> images) {
        mImageViewerAdapter.update(images);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_image_viewer;
    }

    @Override
    public void onImageClick(long position) {
        mImageViewerPresenter.onImageClick(position);
    }

    @Override
    public void onImageLongClick(long position) {
        mImageViewerPresenter.onImageLongClick(position);
    }

    @Override
    public void showCloudDriveOptionsScreen() {
        mOptionsDialog.showOptionsDialog(this);
    }

    @Override
    public void onFullModeImageClicked() {
        mImageViewerPresenter.onFullModeImageClicked();
    }

    @Override
    public void onSaveImageClicked() {
        mImageViewerPresenter.onSaveImageClicked();
    }

    @Override
    public void onDeleteClicked() {
        mImageViewerPresenter.onDeleteClicked();
    }
}
