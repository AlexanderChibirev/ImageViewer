package com.example.omega.imageviewer.storage.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.omega.imageviewer.backend.Error;
import com.example.omega.imageviewer.backend.ErrorException;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.models.Text;
import com.example.omega.imageviewer.storage.database.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Alexander Chibirev on 4/24/2018.
 */

public abstract class BaseStorage implements Storage {

    private Set<Callback> mCallbackSet = new CopyOnWriteArraySet<>();
    @NonNull
    protected List<Image> mCurrentImages = new ArrayList<>();

    @Override
    public void addCallback(Callback callback) {
        mCallbackSet.add(callback);
    }

    @Override
    public void removeCallback(Callback callback) {
        mCallbackSet.remove(callback);
    }

    protected boolean isEmptyCallbacks() {
        return mCallbackSet.isEmpty();
    }

    protected void onRequestImagesEvent(RequestEvent requestEvent, List<Image> images) {
        for (Callback callback : mCallbackSet) {
            callback.onRequestImagesEvent(requestEvent, images);
        }
    }

    protected void onSaveImageInDatabaseEvent(Database.RequestSaveEvent requestSaveEvent, Image image) {
        for (Callback callback : mCallbackSet) {
            callback.onSaveImageInDatabaseEvent(requestSaveEvent, image);
        }
    }

    protected void onDeleteImageEvent(RequestEvent requestEvent, int itemPositionDeleted) {
        for (Callback callback : mCallbackSet) {
            callback.onDeleteImageEvent(requestEvent, itemPositionDeleted);
        }
    }

    protected void onRequestImageEvent(RequestEvent requestEvent, Image image) {
        for (Callback callback : mCallbackSet) {
            callback.onRequestImageEvent(requestEvent, image);
        }
    }

    @NonNull
    protected Text getErrorMessage(@Nullable Exception e) {
        if (e instanceof ErrorException) {
            return getErrorMessage(((ErrorException) e).getError());
        }
        return Error.UNKNOWN;
    }

    @NonNull
    protected Text getErrorMessage(@Nullable Error error) {
        if (error != null) {
            return error.getMessage();
        }
        return Error.UNKNOWN;
    }

    @NonNull
    @Override
    public List<Image> getCurrentImages() {
        return mCurrentImages;
    }

}
