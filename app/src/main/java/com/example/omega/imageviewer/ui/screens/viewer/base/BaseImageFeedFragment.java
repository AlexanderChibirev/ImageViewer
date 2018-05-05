package com.example.omega.imageviewer.ui.screens.viewer.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.ui.dialogs.cloud_drive_options.OptionsDialogFragment;
import com.example.omega.imageviewer.ui.dialogs.confirm.ConfirmDialogFragment;
import com.example.omega.imageviewer.ui.screens.main.ScreenMenuBinderFragment;
import com.example.omega.imageviewer.ui.screens.slider.ImageSliderActivity;
import com.example.omega.imageviewer.ui.utils.DialogUtils;
import com.omega_r.libs.omegarecyclerview.OmegaRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

public abstract class BaseImageFeedFragment extends ScreenMenuBinderFragment implements
        BaseImageFeedView,
        ImageFeedAdapter.OnImageClickListener,
        OptionsDialogFragment.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        ConfirmDialogFragment.ConfirmDialogListener {

    protected onChangePageListener mCallback;


    public BaseImageFeedFragment(@NonNull ImageFeedAdapter imageFeedAdapter) {
        mImageFeedAdapter = imageFeedAdapter;
    }

    @BindView(R.id.recyclerview)
    protected OmegaRecyclerView mRecyclerView;

    @BindView(R.id.swiperefreshlayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @NonNull
    protected ImageFeedAdapter mImageFeedAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setAdapter(mImageFeedAdapter);
        mImageFeedAdapter.setOnImageItemClickListener(this);
    }

    @Override
    public void updateImages(@NonNull List<Image> images) {
        mImageFeedAdapter.update(images);
    }

    @Override
    public void hideLoading() {
        if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showOptionsScreen(boolean isOnlineMode) {
        DialogUtils.showOptionsScreen(this, getFragmentManager(), isOnlineMode);
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
    public void notifyItemImage(int itemPositionDeleted) {
        mImageFeedAdapter.notifyItemItem(itemPositionDeleted);
    }

    @Override
    public void onCancelButtonPressed() {
        //nothing
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (onChangePageListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement onOkClickListener");
        }
    }

    public interface onChangePageListener {
        void onChangeOnlinePageClicked();

        void onChangeOfflinePageClicked();
    }

}