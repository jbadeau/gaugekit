package org.gaugekit.common.io;

import org.gaugekit.common.property.GaugeProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileReader {

    private FileReader() {
    }

    public static String contentOf(String file) {
        try {
            return new String(Files.readAllBytes(fileAt(file)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String contentOf(File file) {
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String contentOf(Path file) {
        try {
            return new String(Files.readAllBytes(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Path fileAt(String file) {
        return GaugeProperties.GAUGE_ENVIRONMENT()
                .stream()
                .map(env -> GaugeProperties.gauge_data_dir().resolve(env).resolve(file))
                .filter(path -> Files.exists(path))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("File '%s' not found", file)));
    }

}