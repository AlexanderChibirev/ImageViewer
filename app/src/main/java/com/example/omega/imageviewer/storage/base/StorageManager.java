package com.example.omega.imageviewer.storage.base;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.app.ImageSliderApp;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.storage.cloud_drive.CloudDrive;
import com.example.omega.imageviewer.storage.database.Database;

import java.util.List;

/**
 * Created by Alexander Chibirev on 5/5/2018.
 */
public class StorageManager implements CloudDrive, Database { //feature impl cloud and db

    private final Storage mStorage;

    private final CloudDrive mCloudDrive;
    private final Database mDatabase;

    public StorageManager(boolean isOnlineMode) {
        mCloudDrive = ImageSliderApp.getAppComponent().getCloudDrive();
        mDatabase = ImageSliderApp.getAppComponent().getDatabase();
        mStorage = isOnlineMode ? mCloudDrive : mDatabase;
    }

    @Override
    public void requestAllImages() {
        mStorage.requestAllImages();
    }

    @Override
    public void requestImage(@NonNull String name, @NonNull String path) {
        mStorage.requestImage(name, path);
    }

    @Override
    public void deleteImage(@NonNull Image image, int position) {
        mStorage.deleteImage(image, position);
    }

    @Override
    public void addCallback(Storage.Callback callback) {
        mStorage.addCallback(callback);
    }

    @Override
    public void removeCallback(Storage.Callback callback) {
        mStorage.removeCallback(callback);
    }

    @Override
    public void requestImages(int limit, int offSet) {
        mStorage.requestImages(limit, offSet);
    }

    @Override
    public List<Image> getCurrentImages() {
        return mStorage.getCurrentImages();
    }

    //Database
    @Override
    public void saveImageInDatabase(@NonNull Image image) {
        mDatabase.saveImageInDatabase(image);
    }
    //CloudDrive in feature
}
