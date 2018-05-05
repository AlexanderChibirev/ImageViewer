package com.example.omega.imageviewer.storage.database;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.storage.base.BaseStorage;
import com.example.omega.imageviewer.tools.MainThreadExecutor;
import com.example.omega.imageviewer.tools.task.Task;
import com.example.omega.imageviewer.tools.task.TaskExecutor;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by Alexander Chibirev on 5/4/2018.
 */
public class RoomDatabase extends BaseStorage implements Database { //TODO maybe RX java?

    private final ImageDao mImageDao;
    private final TaskExecutor mTaskExecutor;

    public RoomDatabase(@NonNull final ImageDao imageDao) {
        mImageDao = imageDao;
        mTaskExecutor = new TaskExecutor(new MainThreadExecutor(), Executors.newCachedThreadPool());
    }

    @Override
    public void deleteImage(@NonNull Image image, final int position) {
        mTaskExecutor.runTask(createDeleteWorker(image)).onResult(input -> {
            onDeleteImageEvent(input, position);
        }).onError(e -> {
            onDeleteImageEvent(RequestEvent.ERROR, position);
        });
    }

    private Task.Worker<RequestEvent> createDeleteWorker(@NonNull Image image) {
        return () -> {
            mImageDao.delete(image);
            return RequestEvent.SUCCESS;
        };
    }

    @Override
    public void saveImageInDatabase(@NonNull final Image image) {
        mTaskExecutor.runTask(createSaveWorker(image)).onResult(input -> {
            onSaveImageInDatabaseEvent(input, image);
        }).onError(e -> {
            onSaveImageInDatabaseEvent(RequestSaveEvent.ERROR, image);
        });
    }

    private Task.Worker<RequestSaveEvent> createSaveWorker(@NonNull Image image) {
        return () -> {
            Image imageDao = mImageDao.getImage(image.getName(), image.getPath()); //check on required
            if (image.equals(imageDao)) {
                return RequestSaveEvent.REQUIRED;
            }
            mImageDao.insert(image);
            mCurrentImages.add(image);
            return RequestSaveEvent.SUCCESS;
        };
    }

    @Override
    public void requestImage(@NonNull String name, @NonNull String path) {
        Task.Worker<Image> taskWorker = () -> mImageDao.getImage(name, path);
        mTaskExecutor.runTask(taskWorker).onResult(input -> {
            onRequestImageEvent(RequestEvent.SUCCESS, input);
        }).onError(e -> {
            onRequestImageEvent(RequestEvent.ERROR, null);
        });
    }

    @Override
    public void requestAllImages() {
        Task.Worker<List<Image>> taskWorker = mImageDao::getImages;
        mTaskExecutor.runTask(taskWorker).onResult(result -> {
            onRequestImagesEvent(RequestEvent.SUCCESS, result);
        }).onError(e -> {
            onRequestImagesEvent(RequestEvent.ERROR, null);
        });
    }

    @Override
    public void requestImages(int limit, int offSet) {
        //TODO added logic
    }

   @Override
    public void addCallback(Callback callback) {
        if (isEmptyCallbacks()) {
            super.addCallback(callback);
        }
    }
}
