package com.example.omega.imageviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Alexander Chibirev on 4/16/2018.
 */

public class Image implements Serializable {

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("path")
    private String path;

    @Expose
    @SerializedName("file")
    private String publicUrl;

    @Expose
    @SerializedName("created")
    private String date;

    public String getUrl() {
        return publicUrl;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getPath() {
        return path;
    }

}
