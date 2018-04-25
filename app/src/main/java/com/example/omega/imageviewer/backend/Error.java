package com.example.omega.imageviewer.backend;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.mvp.models.Text;
import com.example.omega.imageviewer.tools.type_adapters.TextTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

public class Error {

    public static final Text UNKNOWN = Text.from(R.string.error_unknown);

    private static final Gson sGson = new GsonBuilder()
            .registerTypeAdapter(Text.class, new TextTypeAdapter())
            .create();

    @Expose
    @SerializedName("message")
    private Text mMessage;
    @Expose
    @SerializedName("code")
    private int mCode;

    public Error(String message) {
        mMessage = Text.from(message);
    }

    public Error(@StringRes int message) {
        mMessage = Text.from(message);
    }

    @Nullable
    public static Error createWithJson(String json) {
        ErrorContainer errorContainer = sGson.fromJson(json, ErrorContainer.class);
        return errorContainer.error;
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
        @Expose
        @SerializedName("error")
        private Error error;
    }

}