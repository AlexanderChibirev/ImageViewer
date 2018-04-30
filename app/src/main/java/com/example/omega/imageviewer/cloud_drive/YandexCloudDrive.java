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
                .onError(e -> onChangedStateDownloadImages(State.ERROR, getErrorMessage(e)))
                .onFinish(() -> onChangedStateDownloadImages(State.FINISH,
                        Text.from(R.string.request_finish)));
    }

    @Override
    public void deleteImage(int itemPosition) {
        Image image = mImages.get(itemPosition);
        mCloudDriverApi.deleteImage(image.getPath(), true)
                .onResult(r -> onChangedStateDeleteImage(State.SUCCESS,
                        Text.from(R.string.image_success_deleted), itemPosition))
                .onError(e -> onChangedStateDeleteImage(State.ERROR, getErrorMessage(e), itemPosition))
                .onFinish(() -> onChangedStateDeleteImage(State.FINISH,
                        Text.from(R.string.image_success_deleted), itemPosition));
    }

    private void updateImages(@NonNull ListResources<Image> resources) {
        if (!resources.getResources().equals(mImages)) {
            mImages.clear();
            mImages.addAll(resources.getResources());
            onChangedStateDownloadImages(State.SUCCESS, Text.from(R.string.image_success_download));
        }
    }

    @NonNull
    public List<Image> getImages() {
        return mImages;
    }
}
