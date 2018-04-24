package com.example.omega.imageviewer.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.mvp.models.Image;
import com.example.omega.imageviewer.mvp.presenters.ImageSliderPresenter;
import com.example.omega.imageviewer.mvp.views.ImageSliderView;
import com.example.omega.imageviewer.ui.adapters.ImageSliderAdapter;
import com.omega_r.libs.omegarecyclerview.viewpager.OmegaPagerRecyclerView;
import com.omega_r.libs.omegarecyclerview.viewpager.default_transformers.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public class ImageSliderActivity extends BaseActivity implements ImageSliderView {
    private static final String EXTRA_IMAGE = "image";
    private static final String EXTRA_IMAGE_POSITION = "image_position";
    private static final long DEFAULT_VALUE = -1;

    @InjectPresenter
    ImageSliderPresenter mImageSliderPresenter;

    @BindView(R.id.recyclerview)
    OmegaPagerRecyclerView mRecyclerView;

    @NonNull
    private ImageSliderAdapter mImageSliderAdapter = new ImageSliderAdapter();

    public static Intent createIntent(Context context, Image image, long position) {
        Intent intent = new Intent(context, ImageSliderActivity.class);
        intent.putExtra(EXTRA_IMAGE, image);
        intent.putExtra(EXTRA_IMAGE_POSITION, position);
        return intent;
    }

    @ProvidePresenter
    ImageSliderPresenter provideImageSliderPresenter() {
        Intent intent = getIntent();
        Image image = (Image) intent.getSerializableExtra(EXTRA_IMAGE);
        long position = intent.getLongExtra(EXTRA_IMAGE_POSITION, DEFAULT_VALUE);
        return new ImageSliderPresenter(image, position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);
        mRecyclerView.setAdapter(mImageSliderAdapter);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        showBackButton();
    }

    @Override
    public void transformImages(final float maxScale, final float minScale) {
        mRecyclerView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(maxScale)
                .setMinScale(minScale)
                .build());
    }

    @Override
    public void setSelection(long position) {
        mRecyclerView.scrollToPosition((int) position);
    }

    @Override
    public void updateImages(@NonNull List<Image> images) {
        mImageSliderAdapter.update(images);
    }
}
