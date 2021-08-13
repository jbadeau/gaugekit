package org.gaugekit.common.property;

import java.io.File;

public final class CommonProperties {

    private CommonProperties() {
    }

    public static File getTmpDir() {
        return new File(DefaultProperties.getProjectRoot(), System.getenv("tmp_dir"));
    }

    public static String getTestEnvironment() {
        return System.getenv("test_environment");
    }

    public static Long getTestTimeout() {
        return Long.parseLong(System.getenv("test_timeout"));
    }

}