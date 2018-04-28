package com.example.omega.imageviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Alexander Chibirev on 4/16/2018.
 */

public class Image implements Serializable {

    @Expose
    @SerializedName("mName")
    private String mName;

    @Expose
    @SerializedName("mPath")
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

}
