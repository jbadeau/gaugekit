package org.gaugekit.core.file;

import org.gaugekit.core.GaugeProperties;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    private FileUtils() {
    }

    public static Path resolveProjectFile(String file) {
        return GaugeProperties.gaugeEnvs()
                .stream()
                .map(env -> GaugeProperties.dataDir().resolve(env).resolve(file))
                .filter(path -> Files.exists(path))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("File '%s' not found", file)));
    }

}
