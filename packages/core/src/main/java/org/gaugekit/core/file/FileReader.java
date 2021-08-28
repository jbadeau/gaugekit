package org.gaugekit.core.file;

import org.gaugekit.core.GaugeProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public final class FileReader {

    private FileReader() {
    }

    public static String contentsOf(String file) {
        try {
            return new String(Files.readAllBytes(fileAt(file)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static  String contentsOf(File file) {
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static  String contentsOf(Path file) {
        try {
            return new String(Files.readAllBytes(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static  Path fileAt(String file) {
        return GaugeProperties.gaugeEnvs()
                .stream()
                .map(env -> GaugeProperties.dataDir().resolve(env).resolve(file))
                .filter(path -> Files.exists(path))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("File '%s' not found", file)));
    }

}