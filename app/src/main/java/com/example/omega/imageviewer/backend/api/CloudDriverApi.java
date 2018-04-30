package com.example.omega.imageviewer.backend.api;


import android.support.annotation.NonNull;

import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.models.ListResources;
import com.example.omega.imageviewer.tools.task.Task;

import retrofit2.http.DELETE;
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
    Task<ListResources<Image>> requestImages(@Query("limit") final int limit,
                                             @Query("media_type") final String mediaType,
                                             @Query("offset") final int offSet);

    @Headers(X_TOKEN_REQUIRE)
    @DELETE("resources")
    Task<ListResources<Image>> deleteImage(@Query("path") @NonNull final String path,
                                           @Query("permanently") final boolean permanently);

}
