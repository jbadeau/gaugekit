package org.gaugekit.template.helper;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public enum DateTimeHelpers implements Helper<Object> {

    now {
        @Override
        public Object apply(Object o, Options options) throws IOException {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(options.hash("format"));
            return now.format(formatter);
        }
    };

}