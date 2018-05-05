package com.example.omega.imageviewer.storage.database;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.storage.base.Storage;
import com.example.omega.imageviewer.tools.task.Task;

import java.util.List;

/**
 * Created by Alexander Chibirev on 5/2/2018.
 */

public interface Database extends Storage {

    void saveImageInDatabase(@NonNull final Image image);

}
