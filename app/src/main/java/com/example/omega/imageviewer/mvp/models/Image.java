package com.example.omega.imageviewer.mvp.models;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Alexander Chibirev on 4/16/2018.
 */

public class Image implements Serializable {

    @NonNull
    private String mUrl;

    public Image(@NonNull String url) {
        mUrl = url;
    }

    @NonNull
    public String getUrl() {
        return mUrl;
    }
}
