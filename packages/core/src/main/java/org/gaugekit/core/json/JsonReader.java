package org.gaugekit.core.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.gaugekit.core.file.FileUtils;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public final class JsonReader {

    private JsonReader() {
    }
    
    private static final Gson gson = new Gson();

    public static JsonElement read(String file) {
            return read(FileUtils.resolveProjectFile(file));
    }

    public static JsonElement read(Path file)  {
        try {
            return gson.fromJson(new java.io.FileReader(file.toFile()), JsonElement.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
