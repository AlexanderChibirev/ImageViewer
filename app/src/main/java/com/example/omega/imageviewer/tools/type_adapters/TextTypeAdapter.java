package com.example.omega.imageviewer.tools.type_adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.omega.imageviewer.mvp.models.Text;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

public class TextTypeAdapter extends JsonAdapter<Text> {

    @Nullable
    @Override
    public Text fromJson(@NonNull JsonReader reader) throws IOException {
        return Text.from(reader.nextString()); //TODO getAsString
    }

    @Override
    public void toJson(@NonNull JsonWriter writer, @Nullable Text value) throws IOException {
        writer.setIndent(value.getString(null));//TODO add correct logic
    }
}
