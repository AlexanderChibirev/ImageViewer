package com.example.omega.imageviewer.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

public class ImageLoadingUtils {

    public static void loadImageFromUrl(@NonNull final ImageView imageView,
                                        @NonNull final String imageUrl,
                                        @DrawableRes final int fallbackImage) {
        if (imageUrl.isEmpty()) {
            imageView.setImageResource(fallbackImage);
        } else {
            final Context context = imageView.getContext();
            Picasso.with(context)
                    .load(imageUrl)
                    .placeholder(fallbackImage)
                    .error(fallbackImage)
                    .into(imageView);
        }
    }
}
