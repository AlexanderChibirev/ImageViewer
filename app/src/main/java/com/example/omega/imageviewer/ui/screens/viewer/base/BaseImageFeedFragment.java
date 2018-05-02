package com.example.omega.imageviewer.ui.screens.viewer.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.ui.dialogs.cloud_drive_options.OptionsDialog;
import com.example.omega.imageviewer.ui.dialogs.cloud_drive_options.OptionsDialogDelegate;
import com.example.omega.imageviewer.ui.dialogs.cloud_drive_options.OptionsDialogDelegateImpl;
import com.example.omega.imageviewer.ui.screens.main.ScreenMenuBinderFragment;
import com.example.omega.imageviewer.ui.screens.slider.ImageSliderActivity;
import com.example.omega.imageviewer.ui.screens.viewer.online.ImageFeedOnlineView;
import com.omega_r.libs.omegarecyclerview.OmegaRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

public abstract class BaseImageFeedFragment extends ScreenMenuBinderFragment implements
        ImageFeedOnlineView,
        ImageFeedAdapter.OnImageClickListener,
        OptionsDialog.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    public BaseImageFeedFragment(@NonNull ImageFeedAdapter imageFeedAdapter) {
        mImageFeedAdapter = imageFeedAdapter;
    }

    @BindView(R.id.recyclerview)
    protected OmegaRecyclerView mRecyclerView; //TODo check on correct work when recycler protected

    @BindView(R.id.swiperefreshlayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @NonNull
    protected ImageFeedAdapter mImageFeedAdapter;

    @NonNull
    protected OptionsDialogDelegate mOptionsDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
        mOptionsDialog = new OptionsDialogDelegateImpl(getContext());
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setAdapter(mImageFeedAdapter);
        mImageFeedAdapter.setOnImageItemClickListener(this);
    }

    @Override
    public void updateImages(@NonNull List<Image> images) {
        mImageFeedAdapter.update(images);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mOptionsDialog.onDestroy();
    }

    @Override
    public void hideLoading() {
        if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showOptionsScreen() {
        mOptionsDialog.showOptionsDialog(this);
    }

    @Override
    public void showImageSliderScreen(int position, boolean isOnlineMode) {
        startActivity(ImageSliderActivity.createIntent(getContext(), position, isOnlineMode));
    }

    @Override
    public abstract void onFullModeImageClicked();

    @Override
    public void onSaveImageClicked() {
        //nothing
    }

    @Override
    public abstract void onDeleteClicked();

    @Override
    public void deletedImage(int itemPositionDeleted) {
        mImageFeedAdapter.deleteItem(itemPositionDeleted);
    }

}