package com.example.omega.imageviewer.storage.cloud_drive;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.backend.api.CloudDriverApi;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.models.ListResources;
import com.example.omega.imageviewer.storage.base.BaseStorage;

import java.util.List;

/**
 * Created by Alexander Chibirev on 4/24/2018.
 */

public class YandexCloudDrive extends BaseStorage implements CloudDrive {
    private static final String QUERY_MEDIA_TYPE = "image";

    private final CloudDriverApi mCloudDriverApi;

    public YandexCloudDrive(CloudDriverApi cloudDriverApi) {
        mCloudDriverApi = cloudDriverApi;
    }

    @Override
    public void requestImages(int limit, int offSet) {
        mCloudDriverApi.requestImages(limit, QUERY_MEDIA_TYPE, offSet)
                .onResult(this::updateImages)
                .onError(e -> onRequestImagesEvent(RequestEvent.ERROR, null))
                .onFinish(() -> onRequestImagesEvent(RequestEvent.FINISH, null));
    }

    @Override
    public void deleteImage(@NonNull final Image image, final int itemPosition) {
        mCloudDriverApi.deleteImage(image.getPath(), true)
                .onResult(r -> {
                    mCurrentImages.remove(image);
                    onDeleteImageEvent(RequestEvent.SUCCESS, itemPosition);
                })
                .onError(e -> onDeleteImageEvent(RequestEvent.ERROR, itemPosition))
                .onFinish(() -> onDeleteImageEvent(RequestEvent.FINISH, itemPosition));
    }

    private void updateImages(@NonNull ListResources<Image> resources) {
        List<Image> images = resources.getResources();
        if (!images.equals(mCurrentImages) || images.isEmpty()) {
            mCurrentImages.clear();
            mCurrentImages.addAll(images);
            onRequestImagesEvent(RequestEvent.SUCCESS, images);
        }
    }

    @Override
    public void requestImage(@NonNull String name, @NonNull String path) {
        //TODO added logic in feature
    }

    @Override
    public void requestAllImages() {
        //TODO added logic in feature
    }

}
