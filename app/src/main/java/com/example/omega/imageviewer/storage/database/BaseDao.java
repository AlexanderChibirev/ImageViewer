package com.example.omega.imageviewer.storage.database;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

import com.example.omega.imageviewer.tools.task.Task;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

public interface BaseDao<T> { //for future updates, when you can download all the files

    @Insert
    void insert(T object);

    @Delete
    void delete(T object);

}
