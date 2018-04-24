package com.example.omega.imageviewer.backend;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.mvp.models.Text;
import com.example.omega.imageviewer.tools.type_adapters.TextTypeAdapter;
import com.squareup.moshi.Json;
import com.squareup.moshi.Moshi;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

public class Error {
    public static final Text UNKNOWN = Text.from(R.string.error_unknown);

    private static final Moshi sMoshi = new Moshi.Builder()
            .add(Text.class, new TextTypeAdapter()).build();

    @Json(name = "message")
    private transient Text mMessage;

    @Json(name = "code")
    private transient int mCode;


    public Error(String message) {
        mMessage = Text.from(message);
    }

    public Error(@StringRes int message) {
        mMessage = Text.from(message);
    }

    @Nullable
    public static Error createWithJson(String json) {
        //ErrorContainer errorContainer = sMoshi.fromJson(json, ErrorContainer.class); //TODO changed on normal logic
        //return errorContainer.error;
        return null;
    }

    public String getMessage(Resources resources) {
        return mMessage.getString(resources);
    }

    public Text getMessage() {
        return mMessage == null || mMessage.isEmpty() ? UNKNOWN : mMessage;
    }

    public int getCode() {
        return mCode;
    }

    private class ErrorContainer {
        @Json(name = "error")
        private transient Error error;
    }


}
