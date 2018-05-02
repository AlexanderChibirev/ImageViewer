package com.example.omega.imageviewer.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.omega.imageviewer.models.Image;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

@Database(entities = {Image.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ImageDao imageDao();

}
