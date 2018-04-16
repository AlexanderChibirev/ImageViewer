package com.example.omega.imageviewer.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.mvp.models.Image;
import com.example.omega.imageviewer.mvp.presenters.ImageViewerPresenter;
import com.example.omega.imageviewer.mvp.views.ImageViewerView;
import com.example.omega.imageviewer.ui.adapters.ImageViewerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ImageViewerActivity extends BaseActivity implements ImageViewerView, ImageViewerAdapter.OnImageClickListener {

    @InjectPresenter
    ImageViewerPresenter mImageViewerPresenter;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private ImageViewerAdapter mImageViewerAdapter = new ImageViewerAdapter();

    @ProvidePresenter
    ImageViewerPresenter provideImageViewerPresenter() {
        return new ImageViewerPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView.setAdapter(mImageViewerAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
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
        mImageViewerAdapter.update(array);//TODO changed on correct images
    }

    public void showImagePagerScreen() { //TODO changed correct name full image or buautifules

    }

    @Override
    public void onImageClick(Image image) {
        mImageViewerPresenter.onSlideClick(image);
    }
}
