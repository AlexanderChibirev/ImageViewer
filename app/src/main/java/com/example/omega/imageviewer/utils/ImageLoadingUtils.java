package com.example.omega.imageviewer.utils;

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
                                        @DrawableRes final int placeholder,
                                        @DrawableRes final int errorPlaceholder) {
        if (imageUrl.isEmpty()) {
            Picasso.get().load(errorPlaceholder).into(imageView);
        } else {
            Picasso.get()
                    .load(imageUrl)
                    .fit()
                    .centerInside()
                    .placeholder(placeholder)
                    .error(errorPlaceholder)
                    .into(imageView);

        }
    }

}
