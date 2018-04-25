package com.example.omega.imageviewer.backend;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.backend.api.CloudDriverApi;
import com.example.omega.imageviewer.mvp.models.Image;
import com.example.omega.imageviewer.tools.task.Task;

import java.util.List;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

public class AppService {

    private CloudDriverApi mCloudDriverApi;

    public AppService(CloudDriverApi cloudDriverApi) {
        mCloudDriverApi = cloudDriverApi;
    }

    public Task<List<Image>> requestImages(@NonNull String mediaType, int limit, int offSet) {
        return mCloudDriverApi.requestImages(limit, mediaType, offSet);
    }
}
