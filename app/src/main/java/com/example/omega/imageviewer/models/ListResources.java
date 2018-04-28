package com.example.omega.imageviewer.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Chibirev on 4/27/2018.
 */

public class ListResources<T> implements Serializable {

    @Expose
    @SerializedName("items")
    @NonNull
    private List<T> mResources = new ArrayList<>();

    @NonNull
    public List<T> getResources() {
        return mResources;
    }
}
