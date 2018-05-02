package com.example.omega.imageviewer.ui.screens.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.utils.ImageLoadingUtils;
import com.omega_r.libs.omegarecyclerview.OmegaRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

public abstract class BaseRecyclerImageAdapter<VH extends BaseRecyclerImageAdapter.BaseViewHolder> extends OmegaRecyclerView.Adapter<VH> {

    @NonNull
    protected List<Image> mImages = new ArrayList<>();
    protected boolean mIsOnlineMode;

    public BaseRecyclerImageAdapter(boolean isOnlineMode) {
        mIsOnlineMode = isOnlineMode;
    }

    protected void update(List<Image> images) {
        mImages = images;
        notifyDataSetChanged();
    }

    protected static View inflateView(ViewGroup parent, @LayoutRes int layout) {
        return LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    }

    public void deleteItem(int itemPositionDeleted) {
        mImages.remove(itemPositionDeleted);
        notifyItemRemoved(itemPositionDeleted);
    }

    public static abstract class BaseViewHolder extends OmegaRecyclerView.ViewHolder {

        protected BaseViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this::onClick);
            itemView.setOnLongClickListener(this::onLongClick);
        }

        protected abstract void onClick(int position);

        protected abstract void onLongClick(int position);

        public void onClick(View view) {
            checkClick(false);
        }

        public boolean onLongClick(View view) {
            checkClick(true);
            return true;
        }

        private void checkClick(boolean isLongClick) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                if (isLongClick) onLongClick(position);
                else onClick(position);
            }
        }

        public void updateImageView(@NonNull ImageView imageView, @NonNull List<Image> images,
                                    int position, boolean isOnlineMode) {
            ImageLoadingUtils.loadImage(
                    imageView,
                    images.get(position), !isOnlineMode);
        }
    }

}
