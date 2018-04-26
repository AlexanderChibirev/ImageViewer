package com.example.omega.imageviewer.mvp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Alexander Chibirev on 4/16/2018.
 */

public class Image implements Serializable {

    @Expose //for excludeFieldsWithoutExposeAnnotation()
    @SerializedName("public_key")
    private String publicKey;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("publicUrl")
    private String publicUrl;

    public String getUrl() {
        return publicUrl;
    }

    public String getName() {
        return name;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
