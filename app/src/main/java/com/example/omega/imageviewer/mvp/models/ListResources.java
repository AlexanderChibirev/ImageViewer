package com.example.omega.imageviewer.mvp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexander Chibirev on 4/27/2018.
 */

public class ListResources<T> implements Serializable {

    @Expose
    @SerializedName("items")
    private List<T> mResources;

    public List<T> getResources() {
        return mResources;
    }
}
