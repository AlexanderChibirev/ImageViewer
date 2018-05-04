package com.example.omega.imageviewer.storage.database;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.storage.base.BaseStorage;

import java.util.List;

/**
 * Created by Alexander Chibirev on 5/4/2018.
 */
public class RoomDatabase extends BaseStorage implements Database {

    private final Context mContext;
    private final ImageDao mImageDao;

    public RoomDatabase(Context context, @NonNull final ImageDao imageDao) {
        mContext = context;
        mImageDao = imageDao;
    }

    @NonNull
    @Override
    public List<Image> getImages() {
        return mImageDao.getImages();
    }

    @Override
    public void deleteImage(@NonNull Image image, final int position) {
        mImageDao.delete(image);
    }

    @Override
    public void saveImage(@NonNull final Image image) {
        mImageDao.insert(image);
    }

    @Override
    public Image getImageByName(@NonNull String name, @NonNull String path) {
        return mImageDao.findImageByName(name, path);
    }

    @Override
    public void requestImages(int limit, int offSet) {

    }

    @Override
    public void requestAllImages() {

    }

}
