package com.example.omega.imageviewer.ui.screens.slider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.ui.screens.base.BaseActivity;
import com.omega_r.libs.omegarecyclerview.viewpager.OmegaPagerRecyclerView;
import com.omega_r.libs.omegarecyclerview.viewpager.default_transformers.ScaleTransformer;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

public class ImageSliderActivity extends BaseActivity implements ImageSliderView {

    private static final String EXTRA_IMAGE_POSITION = "image_position";
    private static final String EXTRA_IS_ONLINE_MODE = "is_online_mode";
    private static final int DEFAULT_VALUE = -1;

    @InjectPresenter
    ImageSliderPresenter mImageSliderPresenter;

    @BindView(R.id.recyclerview)
    OmegaPagerRecyclerView mRecyclerView;

    @NonNull
    private ImageSliderAdapter mImageSliderAdapter = new ImageSliderAdapter();

    public static Intent createIntent(Context context, int position, boolean isOnlineMode) {
        Intent intent = new Intent(context, ImageSliderActivity.class);
        intent.putExtra(EXTRA_IMAGE_POSITION, position);
        intent.putExtra(EXTRA_IS_ONLINE_MODE, isOnlineMode);
        return intent;
    }

    @ProvidePresenter
    ImageSliderPresenter provideImageSliderPresenter() {
        Intent intent = getIntent();
        int position = intent.getIntExtra(EXTRA_IMAGE_POSITION, DEFAULT_VALUE);
        boolean isOnlineMode = intent.getBooleanExtra(EXTRA_IS_ONLINE_MODE, false);
        return new ImageSliderPresenter(position, isOnlineMode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);
        mRecyclerView.setAdapter(mImageSliderAdapter);
        mRecyclerView.setViewPagerOnPageChangeListener(createSimpleOnPageChangeListener());
    }

    private ViewPager.SimpleOnPageChangeListener createSimpleOnPageChangeListener() {
        return new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mImageSliderPresenter.onPageSelected(position);
            }
        };
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
    public void setToolbarTitle(int position, int sizeData) {
        super.setToolbarTitle(position, sizeData);
    }

    @Override
    public void setSelection(final int position) {
        mRecyclerView.scrollToPosition(position);
    }

    @Override
    public void updateImages(@NonNull List<Image> images) {
        mImageSliderAdapter.update(images);
    }

    @Override
    public void onConnectivityChanged(boolean availableNow) {
        super.onConnectivityChanged(availableNow);
        mImageSliderPresenter.onConnectivityChanged(availableNow);
    }

    @Override
    public void deletedImage(int itemPositionDeleted) {
        mImageSliderAdapter.deleteItem(itemPositionDeleted);
    }

    @Override
    public void hideLoading() {
        //nothing
    }
}
