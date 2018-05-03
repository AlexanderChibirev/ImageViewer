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
                .onError(e -> onDownloadImagesEvent(RequestEvent.ERROR, getErrorMessage(e)))
                .onFinish(() -> onDownloadImagesEvent(RequestEvent.FINISH,
                        Text.from(R.string.request_finish)));
    }

    @Override
    public void deleteImage(int itemPosition) {
        Image image = mImages.get(itemPosition);
        mCloudDriverApi.deleteImage(image.getPath(), true)
                .onResult(r -> onDeleteImageEvent(RequestEvent.SUCCESS,
                        Text.from(R.string.image_success_deleted), itemPosition))
                .onError(e -> onDeleteImageEvent(RequestEvent.ERROR, getErrorMessage(e), itemPosition))
                .onFinish(() -> onDeleteImageEvent(RequestEvent.FINISH,
                        Text.from(R.string.image_success_deleted), itemPosition));
    }

    private void updateImages(@NonNull ListResources<Image> resources) {
        if (!resources.getResources().equals(mImages) || resources.getResources().isEmpty()) {
            mImages.clear();
            mImages.addAll(resources.getResources());
            onDownloadImagesEvent(RequestEvent.SUCCESS, Text.from(R.string.image_success_download));
        }
    }

    @NonNull
    public List<Image> getImages() {
        return mImages;
    }
}
