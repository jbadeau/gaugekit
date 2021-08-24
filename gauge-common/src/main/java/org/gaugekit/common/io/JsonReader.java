package org.gaugekit.common.io;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class JsonReader {

    private JsonReader() {
    }
    
    private static final Gson gson = new Gson();

    public static JsonElement jsonAt(File file) throws IOException {
        return gson.fromJson(new FileReader(file), JsonElement.class);
    }

}
