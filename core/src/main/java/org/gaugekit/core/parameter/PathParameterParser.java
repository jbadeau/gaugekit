package org.gaugekit.core.parameter;

import com.thoughtworks.gauge.execution.parameters.parsers.base.CustomParameterParser;
import gauge.messages.Spec;
import org.gaugekit.core.util.DataUtils;

import java.nio.file.Path;

public class PathParameterParser extends CustomParameterParser<Path> {

    private DataUtils dataUtils;

    public PathParameterParser(DataUtils dataUtils) {
        this.dataUtils = dataUtils;
    }

    @Override
    protected Path customParse(Class clazz, Spec.Parameter parameter) {
        return dataUtils.fileAt(parameter.getValue());
    }

    @Override
    public boolean canParse(Class<?> clazz, Spec.Parameter parameter) {
        return clazz == Path.class;
    }

}