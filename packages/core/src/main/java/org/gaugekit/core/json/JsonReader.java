package org.gaugekit.core.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.gaugekit.core.file.FileReader;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public final class JsonReader {

    private JsonReader() {
    }
    
    private static final Gson gson = new Gson();

    public static JsonElement jsonAt(String file) {
            return jsonAt(FileReader.fileAt(file));
    }

    public static JsonElement jsonAt(Path file)  {
        try {
            return gson.fromJson(new java.io.FileReader(file.toFile()), JsonElement.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
