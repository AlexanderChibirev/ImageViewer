package com.example.omega.imageviewer.ui.screens.base;

import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.omega.imageviewer.mvp.models.Image;
import com.example.omega.imageviewer.utils.ImageLoadingUtils;
import com.omega_r.libs.omegarecyclerview.OmegaRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

public abstract class BaseRecyclerImageAdapter<VH extends BaseRecyclerImageAdapter.BaseViewHolder> extends RecyclerView.Adapter<VH> {

    @NonNull
    protected List<Image> mImages = new ArrayList<>();

    protected void update(List<Image> images) {
        mImages = images;
        notifyDataSetChanged();
    }

    protected static View inflateView(ViewGroup parent, @LayoutRes int layout) {
        return LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    }

    public static abstract class BaseViewHolder extends OmegaRecyclerView.ViewHolder implements View.OnClickListener {

        protected BaseViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                onClick(position);
            }
        }

        protected abstract void onClick(int position);

        public void updateImageView(@NonNull ImageView imageView, @NonNull List<Image> images,
                                    @DrawableRes int placeholderErrorLoadingImage, int position) {
            ImageLoadingUtils.loadImageFromUrl(
                    imageView,
                    images.get(position).getUrl(),
                    placeholderErrorLoadingImage);
        }
    }
}
