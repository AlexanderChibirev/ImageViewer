package com.example.omega.imageviewer.mvp.models;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Alexander Chibirev on 4/16/2018.
 */

public class Image implements Serializable {


    @DrawableRes
    private int mUrl;

    public Image(int url) {
        mUrl = url;
    }

    @DrawableRes
    public int getUrl() {
        return mUrl;
    }
}
