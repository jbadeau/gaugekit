package org.gaugekit.common.property;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DefaultProperties {

    private final static String GAUGE_ENVIRONMENT = "GAUGE_ENVIRONMENT";
    private final static String DEFAULT_PROFILE = "default";

    private DefaultProperties() {
    }

    public static File getProjectRoot() {
        return new File(System.getenv("GAUGE_PROJECT_ROOT"));
    }

    public static List<String> getEnvironments() {
        String env = System.getenv(GAUGE_ENVIRONMENT);
        if (env == null) {
            env = System.getenv(GAUGE_ENVIRONMENT.toLowerCase());
        }
        else if (env.equals(DEFAULT_PROFILE)) {
            return Collections.singletonList(env);
        }

        List<String> envs = new ArrayList(Arrays.asList(env.split(",")));

        if (envs.contains(DEFAULT_PROFILE)) {
            return envs;
        }
        envs.add(DEFAULT_PROFILE);
        return envs;
    }

    public static File getDataDir() {
        return new File(DefaultProperties.getProjectRoot(), System.getenv("gauge_data_dir"));
    }

}