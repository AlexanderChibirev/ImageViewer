package com.example.omega.imageviewer.ui.screens.slider;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.ui.screens.base.BaseRecyclerImageAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Alexander Chibirev on 4/19/2018.
 */

public class ImageSliderAdapter extends BaseRecyclerImageAdapter<BaseRecyclerImageAdapter.BaseViewHolder> {

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflateView(parent, R.layout.item_slider));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.updateImageView(((ViewHolder) holder).imageView, mImages,
                R.drawable.placeholder, R.drawable.error_placeholder, position);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    @Override
    public void update(List<Image> images) {
        super.update(images);
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.imageview)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onClick(int position) {
            //nothing
        }

        @Override
        protected void onLongClick(int position) {
            //nothing or added logic deleted and save maybe... or no... i don't know
        }
    }
}
