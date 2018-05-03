package com.example.omega.imageviewer.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Image;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Alexander Chibirev on 4/20/2018.
 */

public class ImageLoadingUtils {

    private static final String IMAGE_DIR_NAME = "image_viewer.image_dir";
    @DrawableRes
    private static final int PLACEHOLDER = R.drawable.placeholder;
    @DrawableRes
    private static final int ERROR_PLACEHOLDER = R.drawable.error_placeholder;


    public static void loadImage(@NonNull final ImageView imageView,
                                 @NonNull final Image image, boolean fromDisk) {
        RequestCreator creator = createRequestCreator(imageView, image, fromDisk);
        if (creator != null) {
            creator.fit()
                    .centerInside()
                    .noFade()
                    .placeholder(PLACEHOLDER)
                    .error(ERROR_PLACEHOLDER)
                    .into(imageView);
        } else {
            loadErrorPlaceHolder(imageView);
        }
    }

    public static void saveImageOnDisk(Context context, @NonNull final Image image) {
        Picasso.get()
                .load(image.getPublicUrl())
                .into(picassoImageTarget(context.getApplicationContext(), image.getName()));
    }

    public static boolean deleteImageFromDisk(Context context, @NonNull final String imageName) {
        File myImageFile = createFile(context, imageName);
        return myImageFile.exists() && myImageFile.delete();
    }

    @Nullable
    private static RequestCreator createRequestCreator(@NonNull final ImageView imageView,
                                                       @NonNull final Image image, boolean fromDisk) {
        if (fromDisk) {
            Context context = imageView.getContext().getApplicationContext();
            File myImageFile = createFile(context, image.getName());
            if (myImageFile.exists()) return Picasso.get().load(myImageFile);
        } else {
            return Picasso.get().load(image.getPublicUrl());
        }
        return null;
    }

    private static File createFile(Context context, @NonNull String fileName) {
        ContextWrapper cw = new ContextWrapper(context);
        return new File(cw.getDir(IMAGE_DIR_NAME, Context.MODE_PRIVATE), fileName);
    }

    private static void loadErrorPlaceHolder(@NonNull final ImageView imageView) {
        imageView.setImageResource(ERROR_PLACEHOLDER);
    }

    private static Target picassoImageTarget(Context context, @NonNull final String imageName) {
        return new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(() -> {
                    final File myImageFile = createFile(context, imageName);
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(myImageFile);
                        bitmap.compress(Bitmap.CompressFormat.WEBP, 100, fos);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (fos != null) fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                //nothing
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                //nothing
            }
        };
    }

}
