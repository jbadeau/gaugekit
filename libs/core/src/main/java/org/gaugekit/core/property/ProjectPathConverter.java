package org.gaugekit.core.property;

import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.Converter;
import org.gaugekit.core.DefaultProperties;

import java.lang.reflect.Method;
import java.nio.file.Path;

public class ProjectPathConverter implements Converter<Path> {

    private static final DefaultProperties DEFAULT_PROPERTIES = ConfigCache.getOrCreate(DefaultProperties.class);

    @Override
    public Path convert(Method method, String path) {
        return DEFAULT_PROPERTIES.GAUGE_PROJECT_ROOT().resolve(path);
    }

}
