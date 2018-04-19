package com.example.omega.imageviewer.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.mvp.models.Image;
import com.example.omega.imageviewer.mvp.presenters.ImageSliderPresenter;
import com.example.omega.imageviewer.mvp.views.ImageSliderView;
import com.example.omega.imageviewer.ui.adapters.ImageSliderAdapter;

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
    RecyclerView mRecyclerView;

    private ImageSliderAdapter mImageSliderAdapter = new ImageSliderAdapter();

    public static Intent createIntent(Context context, Image image, long position) {
        Intent intent = new Intent(context, ImageViewerActivity.class);
        intent.putExtra(EXTRA_IMAGE, image);
        intent.putExtra(EXTRA_IMAGE, position);
        return intent;
    }

    @ProvidePresenter
    ImageSliderPresenter provideImageSliderPresenter() {
        Intent intent = getIntent();
        Image image = intent.getParcelableExtra(EXTRA_IMAGE);
        long position = intent.getLongExtra(EXTRA_IMAGE_POSITION, DEFAULT_VALUE);
        return new ImageSliderPresenter(image, position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_slider_activity);
        showBackButton();
        mRecyclerView.setAdapter(mImageSliderAdapter);
    }

    @Override
    public void updateImages() {
        List<Image> array = new ArrayList<>();
        array.add(new Image(R.drawable.placeholder_error_loading_image));
        array.add(new Image(R.drawable.placeholder_error_loading_image));
        array.add(new Image(R.drawable.placeholder_error_loading_image));
        array.add(new Image(R.drawable.placeholder_error_loading_image));
        array.add(new Image(R.drawable.placeholder_error_loading_image));
        array.add(new Image(R.drawable.placeholder_error_loading_image));
        array.add(new Image(R.drawable.placeholder_error_loading_image));
        array.add(new Image(R.drawable.placeholder_error_loading_image));
        array.add(new Image(R.drawable.placeholder_error_loading_image));
        mImageSliderAdapter.update(array);//TODO changed on correct images
    }
}
