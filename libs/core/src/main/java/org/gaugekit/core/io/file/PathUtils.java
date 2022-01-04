package org.gaugekit.core.io.file;

import org.aeonbits.owner.ConfigCache;
import org.gaugekit.core.DefaultProperties;

import java.nio.file.Path;

public class PathUtils {

    private static final DefaultProperties DEFAULT_PROPERTIES = ConfigCache.getOrCreate(DefaultProperties.class);

    private PathUtils() {
    }

    public static Path resolveProjectPath(String path) {
        return DEFAULT_PROPERTIES.gauge_data_dir().resolve(path);
    }

}