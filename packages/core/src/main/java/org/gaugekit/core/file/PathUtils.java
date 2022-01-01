package org.gaugekit.core.file;

import org.gaugekit.core.GaugeProperties;

import java.nio.file.Path;

public class PathUtils {

    private PathUtils() {
    }

    public static Path resolveProjectPath(String path) {
        return GaugeProperties.dataDir().resolve(path);
    }

}
