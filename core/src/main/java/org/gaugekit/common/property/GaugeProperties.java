package org.gaugekit.common.property;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class GaugeProperties {

    private static final String DEFAULT_PROFILE = "default";

    private static final String GAUGE_PROJECT_ROOT = "GAUGE_PROJECT_ROOT";

    private static final String GAUGE_ENVIRONMENT = "GAUGE_ENVIRONMENT";

    private static final String gauge_data_dir = "gauge_data_dir";

    private static final String gauge_specs_dir = "gauge_specs_dir";

    private static final String gauge_reports_dir = "gauge_reports_dir";

    private static final String overwrite_reports = "overwrite_reports";

    private static final String screenshot_on_failure = "screenshot_on_failure";

    private static final String logs_directory = "logs_directory";

    private static final String enable_multithreading = "enable_multithreading";

    private static final String csv_delimiter = "csv_delimiter";

    private static final String allow_multiline_step = "allow_multiline_step";

    protected GaugeProperties() {
    }

    public static Path GAUGE_PROJECT_ROOT() {
        return getPath(GAUGE_PROJECT_ROOT);
    }

    public static List<String> GAUGE_ENVIRONMENT() {
        return getStringList(GAUGE_ENVIRONMENT, ",");
    }

    public static Path gauge_data_dir() {
        return getProjectRelativePath(gauge_data_dir);
    }

    public static Path gauge_reports_dir() {
        return getProjectRelativePath(gauge_reports_dir);
    }

    public static Path logs_directory() {
        return getProjectRelativePath(logs_directory);
    }

    public static Path gauge_specs_dir() {
        return getProjectRelativePath(gauge_specs_dir);
    }

    public static Boolean overwrite_reports() {
        return getBoolean(overwrite_reports);
    }

    public static Boolean screenshot_on_failure() {
        return getBoolean(screenshot_on_failure);
    }

    public static Boolean enable_multithreading() {
        return getBoolean(enable_multithreading);
    }

    public static String csv_delimiter() {
        return getString(csv_delimiter);
    }

    public static Boolean allow_multiline_step() {
        return getBoolean(allow_multiline_step);
    }

    protected static Path getProjectRelativePath(String property) {
        return getProperty(property)
                .stream()
                .map(value -> GAUGE_PROJECT_ROOT().resolve(value))
                .findFirst()
                .get();
    }

    protected static Path getPath(String property) {
        return getProperty(property)
                .stream()
                .map(value -> Path.of(value))
                .findFirst()
                .get();
    }

    protected static Boolean getBoolean(String property) {
        return getProperty(property)
                .stream()
                .map(value -> Boolean.valueOf(value))
                .findFirst()
                .get();
    }

    protected static Long getLong(String property) {
        return getProperty(property)
                .stream()
                .map(value -> Long.valueOf(value))
                .findFirst()
                .get();
    }

    protected static List<String> getStringList(String property, String delimiter) {
        return getProperty(property)
                .stream()
                .map(value -> Arrays.asList(value.split(delimiter)))
                .findFirst()
                .get();
    }

    protected static String getString(String property) {
        return getProperty(property)
                .get();
    }

    protected static Optional<String> getProperty(String property) {
        String value = System.getenv(property);
        if (value == null) {
            throw new MissingPropertyException(property);
        }
        return Optional.ofNullable(value);
    }

}