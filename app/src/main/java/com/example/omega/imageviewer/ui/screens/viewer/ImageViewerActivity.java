package com.example.omega.imageviewer.ui.screens.viewer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.ui.screens.base.BaseActivity;
import com.example.omega.imageviewer.ui.screens.slider.ImageSliderActivity;

import java.util.List;

import butterknife.BindView;

public class ImageViewerActivity extends BaseActivity implements
        ImageViewerView,
        ImageViewerAdapter.OnImageClickListener {

    @InjectPresenter
    ImageViewerPresenter mImageViewerPresenter;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private ImageViewerAdapter mImageViewerAdapter = new ImageViewerAdapter();

    public static Intent createIntent(Context context) {
        return new Intent(context, ImageViewerActivity.class);
    }

    @ProvidePresenter
    ImageViewerPresenter provideImageViewerPresenter() {
        return new ImageViewerPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        mImageViewerAdapter.setOnImageItemClickListener(this);
        mRecyclerView.setAdapter(mImageViewerAdapter);
    }

    @Override
    public void updateImages(@NonNull List<Image> images) {
        mImageViewerAdapter.update(images);
    }

    @Override
    public void showImageSliderScreen(long position) {
        startActivity(ImageSliderActivity.createIntent(this, position));
    }

    @Override
    public void onImageClick(long position) {
        mImageViewerPresenter.onSlideClick(position);
    }

    @Override
    public void onConnectivityChanged(boolean availableNow) {
        super.onConnectivityChanged(availableNow);
        mImageViewerPresenter.onConnectivityChanged(availableNow);
    }
}
