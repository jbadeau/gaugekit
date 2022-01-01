package org.gaugekit.core;

import org.gaugekit.core.error.MissingPropertyException;

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

    public static String csvDelimiter() {
        return getString(csv_delimiter);
    }

    public static List<String> gaugeEnvs() {
        return getStringList(GAUGE_ENVIRONMENT.toLowerCase(), ",");
    }

    public static Path projectDir() {
        return getPath(GAUGE_PROJECT_ROOT);
    }

    public static Path dataDir() {
        return getProjectRelativePath(gauge_data_dir);
    }

    public static Path reportsDir() {
        return getProjectRelativePath(gauge_reports_dir);
    }

    public static Path logsDir() {
        return getProjectRelativePath(logs_directory);
    }

    public static Path specsDir() {
        return getProjectRelativePath(gauge_specs_dir);
    }

    public static Boolean overwriteReports() {
        return getBoolean(overwrite_reports);
    }

    public static Boolean screenshotOnFailure() {
        return getBoolean(screenshot_on_failure);
    }

    public static Boolean enableMultithreading() {
        return getBoolean(enable_multithreading);
    }

    public static Boolean allowMultilineStep() {
        return getBoolean(allow_multiline_step);
    }

    protected static Path getProjectRelativePath(String property) {
        return getProperty(property)
                .stream()
                .map(value -> projectDir().resolve(value))
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