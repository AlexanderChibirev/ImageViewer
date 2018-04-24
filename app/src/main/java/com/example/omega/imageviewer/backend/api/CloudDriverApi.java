package com.example.omega.imageviewer.backend.api;

import com.example.omega.imageviewer.mvp.models.Image;
import com.example.omega.imageviewer.tools.task.Task;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

public interface CloudDriverApi {

    @Headers("Authorization: OAuth ") //TODO ADDED GET TOKEN ?????? HOW WTF Suka bliatb
    @GET("resources/last-uploaded")
    Task<List<Image>> requestImages(@Query("limit") final int limit,
                                    @Query("media_type") final String mediaType,
                                    @Query("offset") final int offSet); //offset from the beginning of the list
}
