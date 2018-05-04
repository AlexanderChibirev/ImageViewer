package com.example.omega.imageviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alexander Chibirev on 4/16/2018.
 */

public class Image extends RealmObject implements Serializable {

    @PrimaryKey
    private long mId;

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

    public Image() {
        super();
    }

    public Image(String name, String path,
                 String publicUrl, String date) {
        super();
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

    public long getId() {
        return mId;
    }

    public void setId(long id) {
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
