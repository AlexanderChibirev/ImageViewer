package com.example.omega.imageviewer.backend.api;

import com.example.omega.imageviewer.mvp.models.Image;
import com.example.omega.imageviewer.mvp.models.ListResources;
import com.example.omega.imageviewer.tools.task.Task;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.example.omega.imageviewer.backend.Constants.X_TOKEN_REQUIRE;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

public interface CloudDriverApi {

    @Headers(X_TOKEN_REQUIRE)
    @GET("resources/last-uploaded")
    Task<ListResources<Image>> requestImages(
            @Query("limit") final int limit,
            @Query("media_type") final String mediaType,
            @Query("offset") final int offSet);
}
