package org.gaugekit.json.io;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.gaugekit.core.io.file.PathUtils;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public final class JsonReader {

    private static final Gson gson = new Gson();

    private JsonReader() {
    }

    public static JsonElement read(String file) {
            return read(PathUtils.resolveProjectPath(file));
    }

    public static JsonElement read(Path file)  {
        try {
            return gson.fromJson(new java.io.FileReader(file.toFile()), JsonElement.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
