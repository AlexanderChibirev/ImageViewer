package com.example.omega.imageviewer.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.mvp.models.Image;
import com.example.omega.imageviewer.mvp.presenters.ImageViewerPresenter;
import com.example.omega.imageviewer.mvp.views.ImageViewerView;
import com.example.omega.imageviewer.ui.adapters.ImageViewerAdapter;

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
    public void showImageSliderScreen(@NonNull Image image, long position) {
        startActivity(ImageSliderActivity.createIntent(this, image, position));
    }

    @Override
    public void onImageClick(@NonNull Image image, long position) {
        mImageViewerPresenter.onSlideClick(image, position);
    }

    @Override
    public void onConnectivityChanged(boolean availableNow) {
        super.onConnectivityChanged(availableNow);
        mImageViewerPresenter.onConnectivityChanged(availableNow);
    }
}
