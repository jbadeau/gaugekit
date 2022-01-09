package org.gaugekit.core.io.file;

import org.aeonbits.owner.ConfigCache;
import org.gaugekit.core.DefaultProperties;

import java.nio.file.Path;

public class FileUtils {

    private static final DefaultProperties DEFAULT_PROPERTIES = ConfigCache.getOrCreate(DefaultProperties.class);

    private FileUtils() {
    }

    public static Path resolveDataFile(String path) {
        return DEFAULT_PROPERTIES.gauge_data_dir().resolve(path);
    }

}