package com.example.omega.imageviewer.cloud_drive;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.backend.api.CloudDriverApi;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.models.ListResources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Chibirev on 4/24/2018.
 */

public class YandexCloudDrive extends BaseYandexCloudDrive {
    private static final String QUERY_MEDIA_TYPE = "image";

    private final CloudDriverApi mCloudDriverApi;

    @NonNull
    private List<Image> mImages = new ArrayList<>();

    public YandexCloudDrive(CloudDriverApi cloudDriverApi) {
        mCloudDriverApi = cloudDriverApi;
    }

    @Override
    public void requestImages(int limit, int offSet) {
        mCloudDriverApi.requestImages(limit, QUERY_MEDIA_TYPE, offSet)
                .onResult(this::addImages)
                .onError(this::handleError)
                .onFinish(() -> onChangedStateDownloadImages(DownloadState.FINISH));
    }

    private void addImages(@NonNull ListResources<Image> resources) {
        mImages.addAll(resources.getResources());
        onChangedStateDownloadImages(DownloadState.SUCCESS);
    }

    private void handleError(Exception e) {
        onChangedStateDownloadImages(DownloadState.ERROR);
    }

    @NonNull
    public List<Image> getImages() {
        return mImages;
    }
}
