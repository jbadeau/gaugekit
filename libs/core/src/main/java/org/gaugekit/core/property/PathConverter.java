package org.gaugekit.core.property;

import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;
import java.nio.file.Path;

public class PathConverter  implements Converter<Path> {

    @Override
    public Path convert(Method method, String path) {
        return Path.of(path);
    }

}
