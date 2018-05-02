package com.example.omega.imageviewer.tools.type_adapters;

import com.example.omega.imageviewer.models.Text;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Alexander Chibirev on 4/27/2018.
 */

public class TextTypeAdapter implements JsonSerializer<Text>, JsonDeserializer<Text> { //or JsonAdapter<Text>

    @Override
    public Text deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Text.from(json.getAsString());
    }

    @Override
    public JsonElement serialize(Text src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src.getString(null));
    }

}
