package org.gaugekit.assertion.file;

import org.assertj.core.api.AbstractAssert;

import java.io.File;

public class FileAssert extends AbstractAssert<FileAssert, File> {

    public FileAssert(File actual) {
        super(actual, FileAssert.class);
    }

    public static FileAssert assertThat(File actual) {
        return new FileAssert(actual);
    }

}
