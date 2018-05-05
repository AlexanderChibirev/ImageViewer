package com.example.omega.imageviewer.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.models.Image;

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
    private static final RequestOptions sRequestOptions = new RequestOptions();
    private static final int QUALITY_COMPRESS = 100;

    public static boolean deleteImageFromDisk(Context context, @NonNull final Image image) {
        File file = createFile(context, image.getName());
        return file.exists() && file.delete();
    }

    public static void loadWithGlideFromUrl(@NonNull ImageView imageView, @NonNull String PublicUrl) {
        setRequestOptions();
        Glide.with(imageView.getContext())
                .load(PublicUrl)
                .apply(sRequestOptions)
                .into(imageView);
    }

    private static void setRequestOptions() {
        //sRequestOptions.diskCacheStrategy(DiskCacheStrategy.NONE); TODO in feature add?
        sRequestOptions.placeholder(PLACEHOLDER);
        sRequestOptions.error(ERROR_PLACEHOLDER);
        sRequestOptions.fallback(ERROR_PLACEHOLDER);
    }

    public static void downLoadAndSaveImageOnPhone(Context context, Image image) {
        Glide.with(context)
                .asBitmap()
                .load(image.getPublicUrl())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        downLoadAndSaveImageOnPhone(context, resource, image.getName());
                    }
                });
    }

    private static void downLoadAndSaveImageOnPhone(Context context, Bitmap bitmap, String fileName) {
        File file = createFile(context, fileName);
        if (!file.exists()) {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.WEBP, QUALITY_COMPRESS, fos);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.flush();
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static File createFile(Context context, @NonNull String fileName) {
        ContextWrapper cw = new ContextWrapper(context);
        return new File(cw.getDir(IMAGE_DIR_NAME, Context.MODE_PRIVATE), fileName);
    }

}
