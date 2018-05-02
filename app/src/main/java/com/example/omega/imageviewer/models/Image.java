package com.example.omega.imageviewer.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Alexander Chibirev on 4/16/2018.
 */

@Entity(tableName = "image")
public class Image implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int mId;

    @Expose
    @SerializedName(Field.NAME)
    private String mName;

    @Expose
    @SerializedName(Field.PATH)
    private String mPath;

    @Expose
    @SerializedName(Field.PUBLIC_URL)
    private String mPublicUrl;

    @Expose
    @SerializedName(Field.DATE)
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

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Image) {
            Image image = (Image) obj;
            return image.mPath.equals(mPath);
        }
        return false;
    }


    private interface Field {
        String NAME = "name";
        String PATH = "path";
        String PUBLIC_URL = "file";
        String DATE = "created";
    }

}
