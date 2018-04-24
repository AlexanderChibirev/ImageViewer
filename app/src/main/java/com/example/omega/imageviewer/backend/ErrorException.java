package com.example.omega.imageviewer.backend;

import java.io.IOException;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

public class ErrorException extends IOException {

    private final Error mError;

    public ErrorException(Error error) {
        mError = error;
    }

    public Error getError() {
        return mError;
    }

}