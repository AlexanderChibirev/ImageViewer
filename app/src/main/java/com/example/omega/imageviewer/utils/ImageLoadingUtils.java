package com.example.omega.imageviewer.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

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

    private Target picassoImageTarget(Context context, final String imageDir, final String imageName) { //TODO FOR TESTING DELETED AFTER WRITE NORMAL CODE
        ContextWrapper cw = new ContextWrapper(context);
        final File directory = cw.getDir(imageDir, Context.MODE_PRIVATE); // path to /data/data/yourapp/app_imageDir
        return new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(() -> {
                    final File myImageFile = new File(directory, imageName); // Create image file
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(myImageFile);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            Objects.requireNonNull(fos).close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.i("image", "image saved to >>>" + myImageFile.getAbsolutePath());

                }).start();
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (placeHolderDrawable != null) {
                }
            }
        };
    }
}
