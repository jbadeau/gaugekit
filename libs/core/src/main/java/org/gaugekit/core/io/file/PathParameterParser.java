package org.gaugekit.core.io.file;

import com.thoughtworks.gauge.execution.parameters.parsers.base.CustomParameterParser;
import gauge.messages.Spec;

import java.nio.file.Path;

public class PathParameterParser extends CustomParameterParser<Path> {

    @Override
    protected Path customParse(Class clazz, Spec.Parameter parameter) {
        return PathUtils.resolveProjectPath(parameter.getValue());
    }

    @Override
    public boolean canParse(Class<?> clazz, Spec.Parameter parameter) {
        return clazz == Path.class;
    }

}