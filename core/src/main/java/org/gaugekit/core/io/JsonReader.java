package org.gaugekit.core.io;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public final class JsonReader {

    private JsonReader() {
    }
    
    private static final Gson gson = new Gson();

    public static JsonElement jsonAt(String file) throws IOException {
        return jsonAt(org.gaugekit.core.io.FileReader.fileAt(file));
    }

    public static JsonElement jsonAt(Path file) throws IOException {
        return gson.fromJson(new FileReader(file.toFile()), JsonElement.class);
    }

}
