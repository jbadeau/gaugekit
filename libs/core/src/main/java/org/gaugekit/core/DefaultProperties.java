package org.gaugekit.core;

import org.aeonbits.owner.Config;
import org.gaugekit.core.property.PathConverter;
import org.gaugekit.core.property.ProjectPathConverter;

import java.nio.file.Path;
import java.util.List;

/**
 * default.properties is a .properties file that contains key value pairs.
 * You can use or change the key value pairs present in this file to configure a particular Gauge project.
 * The changed value overrides the default value of a key.
 * This file is located at <project_root>/env/default, where <project_root> is the location at which you have created your Gauge project.
 */
@Config.Sources({"system:env"})
public interface DefaultProperties extends Config {


    /**
     *
     */
    @Separator(",")
    List<String> GAUGE_ENVIRONMENT();

    /**
     *
     */
    @ConverterClass(PathConverter.class)
    Path GAUGE_PROJECT_ROOT();

    /**
     * The path to the gauge reports directory should be either relative to the project directory or an absolute path.
     */
    @ConverterClass(ProjectPathConverter.class)
    Path gauge_reports_dir();

    /**
     * Set as false if gauge reports should not be overwritten when Gauge runs a specification.
     * If set to true, a new, time-stamped directory is created every time when Gauge runs a specification.
     */
    Boolean overwrite_reports();

    /**
     * Set to false to disable screenshots on failure in Gauge reports.
     */
    Boolean screenshot_on_failure();

    /**
     * The path to the Gauge logs directory should be either relative to the project directory or an absolute path.
     */
    @ConverterClass(ProjectPathConverter.class)
    Path logs_directory();

    /**
     * Set to true to use multithreading for parallel execution.
     */
    Boolean enable_multithreading();


    /**
     * The path to the gauge specifications directory. Takes a comma separated list of specification files or directories.
     */
    @Separator(",")
    List<Path> gauge_specs_dir();


    /**
     * CsvDelimiter holds delimiter used to parse csv files.
     * default: `,`.
     */
    String csv_delimiter();

    /**
     * Allows steps to be written in multiline.
     */
    Boolean allow_multiline_step();


    /**
     * The path to the Gauge data directory should be either relative to the project directory or an absolute path.
     */
    @ConverterClass(ProjectPathConverter.class)
    Path gauge_data_dir();

}