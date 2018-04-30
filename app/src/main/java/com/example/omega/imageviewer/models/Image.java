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
    private String mName;

    @Expose
    @SerializedName("path")
    private String mPath;

    @Expose
    @SerializedName("file")
    private String mPublicUrl;

    @Expose
    @SerializedName("created")
    private String mDate;

    public Image(String name, String path,
                 String publicUrl, String date) {
        mName = name;
        mPath = path;
        mPublicUrl = publicUrl;
        mDate = date;
    }

    public String getPublicUrl() {
        return mPublicUrl;
    }

    public String getName() {
        return mName;
    }

    public String getDate() {
        return mDate;
    }

    public String getPath() {
        return mPath;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Image) {
            Image image = (Image) obj;
            return image.mPath.equals(mPath);
        }
        return false;
    }
}
