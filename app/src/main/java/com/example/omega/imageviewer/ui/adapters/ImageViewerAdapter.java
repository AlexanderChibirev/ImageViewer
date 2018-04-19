package com.example.omega.imageviewer.ui.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.mvp.models.Image;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Alexander Chibirev on 4/16/2018.
 */

public class ImageViewerAdapter extends BaseRecyclerImageAdapter<BaseRecyclerImageAdapter.BaseViewHolder> {
    @Nullable
    protected OnImageClickListener mListener;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflateView(parent, R.layout.item_viewer));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        ((ViewHolder) holder).updateImage(position);
    }

    @Override
    public void update(List<Image> images) {
        super.update(images);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public void setOnImageItemClickListener(OnImageClickListener listener) {
        mListener = listener;
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.imageview)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onClick(int position) {
            if (position >= 0 && mListener != null) {
                mListener.onImageClick(mImages.get(position), position);
            }
        }

        void updateImage(int position) {
            imageView.setBackgroundResource(mImages.get(position).getUrl());
        }
    }

    public interface OnImageClickListener {
        void onImageClick(@NonNull Image image, long position);
    }
}
