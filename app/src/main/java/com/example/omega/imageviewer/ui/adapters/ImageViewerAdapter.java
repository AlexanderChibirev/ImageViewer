package com.example.omega.imageviewer.ui.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.mvp.models.Image;

import java.util.List;

/**
 * Created by Alexander Chibirev on 4/16/2018.
 */

public class ImageViewerAdapter extends RecyclerView.Adapter<ImageViewerAdapter.ViewHolder> {
    @Nullable
    private OnImageClickListener mListener;
    private List<Image> mImages;

    public ImageViewerAdapter() {
        super();
    }

    public void setOnHistoryItemClickListener(OnImageClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_image, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(mImages.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public void update(List<Image> images) {
        mImages = images;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            if (adapterPosition >= 0 && mListener != null) {
                mListener.onImageClick(mImages.get(adapterPosition));
            }
        }
    }


    public interface OnImageClickListener {
        void onImageClick(Image image);
    }
}
