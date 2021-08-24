package org.gaugekit.common.parameter;

import com.thoughtworks.gauge.execution.parameters.parsers.base.CustomParameterParser;
import gauge.messages.Spec;
import org.gaugekit.common.io.FileReader;

import java.io.File;
import java.nio.file.Path;

public class PathParameterParser extends CustomParameterParser<Path> {

    @Override
    protected Path customParse(Class clazz, Spec.Parameter parameter) {
        return FileReader.fileAt(parameter.getValue());
    }

    @Override
    public boolean canParse(Class<?> clazz, Spec.Parameter parameter) {
        return clazz == Path.class;
    }

}