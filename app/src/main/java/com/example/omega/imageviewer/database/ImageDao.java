package com.example.omega.imageviewer.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.omega.imageviewer.models.Image;

import java.util.List;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

@Dao
public interface ImageDao extends BaseDao<Image> {

    @Query("SELECT * FROM image")
    List<Image> getImages();

}
