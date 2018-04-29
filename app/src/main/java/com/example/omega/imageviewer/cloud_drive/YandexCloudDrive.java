package com.example.omega.imageviewer.cloud_drive;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.backend.api.CloudDriverApi;
import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.models.ListResources;
import com.example.omega.imageviewer.models.Text;

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
                .onResult(this::updateImages)
                .onError(this::handleError)
                .onFinish(() -> onChangedStateDownloadImages(DownloadState.FINISH,
                        Text.from(R.string.request_finish)));
    }

    private void updateImages(@NonNull ListResources<Image> resources) {
        mImages.clear();
        mImages.addAll(resources.getResources());
        onChangedStateDownloadImages(DownloadState.SUCCESS, Text.from(R.string.image_success_download));
    }

    private void handleError(Exception error) {
        onChangedStateDownloadImages(DownloadState.ERROR, getErrorMessage(error));
    }

    @NonNull
    public List<Image> getImages() {
        return mImages;
    }
}
