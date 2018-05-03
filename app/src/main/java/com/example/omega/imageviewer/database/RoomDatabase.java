package com.example.omega.imageviewer.database;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.models.Image;

import java.util.List;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

public class RoomDatabase implements Database {
    //возможно нужно соединить интерфейс базы данных и облачного диска в единый интерфейс и еще базовый класс добавить
    //пока не стал этого делать, но если что это все делается быстро через наследование)))

    private final ImageDao mImageDao;

    public RoomDatabase(@NonNull final ImageDao imageDao) {
        mImageDao = imageDao;
    }

    @Override
    public List<Image> getImages() {
        return mImageDao.getImages();
    }

    @Override
    public void saveImage(@NonNull Image image) {
        mImageDao.insert(image);
    }

    @Override
    public void deleteImage(@NonNull Image image) {
        mImageDao.delete(image);
    }

    @Override
    public Image getImageByName(@NonNull String name, @NonNull String path) {
        return mImageDao.findImageByName(name, path);
    }
}
