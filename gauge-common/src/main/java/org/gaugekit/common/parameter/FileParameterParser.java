package org.gaugekit.common.parameter;

import org.gaugekit.common.io.FileReader;
import com.thoughtworks.gauge.execution.parameters.parsers.base.CustomParameterParser;
import gauge.messages.Spec;

import java.io.File;

public class FileParameterParser extends CustomParameterParser<File> {

    @Override
    protected File customParse(Class clazz, Spec.Parameter parameter) {
        return FileReader.fileAt(parameter.getValue());
    }

    @Override
    public boolean canParse(Class<?> clazz, Spec.Parameter parameter) {
        return clazz == File.class;
    }

}