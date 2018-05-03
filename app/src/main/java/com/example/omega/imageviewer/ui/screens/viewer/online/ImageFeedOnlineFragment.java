package com.example.omega.imageviewer.ui.screens.viewer.online;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.ui.screens.viewer.base.BaseImageFeedFragment;
import com.example.omega.imageviewer.ui.screens.viewer.base.ImageFeedAdapter;
import com.example.omega.imageviewer.utils.ImageLoadingUtils;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by Alexander Chibirev on 4/29/2018.
 */

public class ImageFeedOnlineFragment extends BaseImageFeedFragment implements ImageFeedOnlineView {

    @InjectPresenter
    ImageFeedOnlinePresenter mImageFeedOnlinePresenter;

    public static ImageFeedOnlineFragment newInstance() {
        return new ImageFeedOnlineFragment();
    }

    public ImageFeedOnlineFragment() {
        super(new ImageFeedAdapter(true));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.addOnScrollListener(createOnScrollChangeListener());
    }

    private RecyclerView.OnScrollListener createOnScrollChangeListener() {
        return new RecyclerView.OnScrollListener() { //TODO refactoring
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                boolean hasEnded = newState == SCROLL_STATE_IDLE;
                if (hasEnded) {
                    onRefresh(); //TODO added progress bar for end rv
                }
            }
        };
    }

    @Override
    public String getTitle() {
        return getString(R.string.menu_main_page);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_image_viewer;
    }

    @Override
    public void onImageClick(int position) {
        mImageFeedOnlinePresenter.onImageClick(position);
    }

    @Override
    public void onImageLongClick(int position) {
        mImageFeedOnlinePresenter.onImageLongClick(position);
    }

    @Override
    public void onFullModeImageClicked() {
        mImageFeedOnlinePresenter.onFullModeImageClicked();
    }

    @Override
    public void onSaveImageClicked() {
        mImageFeedOnlinePresenter.onSaveImageClicked();
    }

    @Override
    public void onDeleteClicked() {
        mImageFeedOnlinePresenter.onDeleteClicked();
    }

    @Override
    public void onRefresh() {
        mImageFeedOnlinePresenter.onRefresh();
    }

    @Override
    public void onConnectivityChanged(boolean availableNow) {
        mImageFeedOnlinePresenter.onConnectivityChanged(availableNow);
    }

    @Override
    public void saveImageOnDisk(Image image) {
        Context context = getContext();
        if (context != null) {
            ImageLoadingUtils.saveImageOnDisk(context, image);
        }
    }
}
