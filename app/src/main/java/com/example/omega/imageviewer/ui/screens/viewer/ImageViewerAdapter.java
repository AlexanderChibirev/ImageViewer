package com.example.omega.imageviewer.ui.screens.viewer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.ui.screens.base.BaseRecyclerImageAdapter;
import com.omega_r.libs.omegarecyclerview.pagination.PaginationAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Alexander Chibirev on 4/16/2018.
 */

public class ImageViewerAdapter extends BaseRecyclerImageAdapter<BaseRecyclerImageAdapter.BaseViewHolder>{
    @Nullable
    private OnImageClickListener mListener;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflateView(parent, R.layout.item_viewer));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.updateImageView(((ViewHolder) holder).imageView, mImages,
                R.drawable.placeholder, R.drawable.error_placeholder, position);
    }

    @Override
    public void update(List<Image> images) {
        super.update(images);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public void setOnImageItemClickListener(@Nullable OnImageClickListener listener) {
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
            checkClick(false, position);
        }

        @Override
        protected void onLongClick(int position) {
            checkClick(true, position);
        }

        private void checkClick(boolean isLongClick, int position) {
            if (position >= 0 && mListener != null) {
                if (isLongClick) mListener.onImageLongClick(position);
                else mListener.onImageClick(position);
            }
        }
    }

    public interface OnImageClickListener {
        void onImageClick(long position);

        void onImageLongClick(int position);
    }
}
