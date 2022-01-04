package org.gaugekit.core;

import org.aeonbits.owner.Config;

import java.net.URL;
import java.nio.file.Path;

/**
 * gauge.properties is a .properties file that contains all Gauge specific configurations.
 * You can use or change the key value pairs present in this file to configure Gauge across all Gauge projects.
 * This file is located at ~/.gauge/config in macOS and Linux systems and at %APPDATA%\gauge\config in Windows systems.
 * <p>
 * You can also use the gauge config command to change the value of a key in the gauge.properties file.
 */
@Config.Sources({"system:env"})
public interface GaugeProperties extends Config {

    /**
     * Allow Gauge and its plugin updates to be notified to the user.
     */
    Boolean check_updates();

    /**
     * Set to a URL, which acts as plugin repository for Gauge.
     */
    URL gauge_repository_url();

    /**
     * Set to a URL, which acts as a template repository for Gauge.
     */
    URL gauge_templates_url();

    /**
     * Timeout in milliseconds for requests from language runner when connecting to IDE.
     */
    Long ide_request_timeout();

    /**
     * Sets the timeout in milliseconds for Gauge while connecting to plugins (except language runner plugins).
     */
    Long plugin_connection_timeout();

    /**
     * Sets the timeout in milliseconds for a plugin to stop after a kill message has been sent.
     */
    Long plugin_kill_timeout();

    /**
     * Sets the timeout in milliseconds for Gauge while connecting to the language runner.
     */
    Long runner_connection_timeout();

    /**
     * Sets the timeout in milliseconds for requests from the language runner to Gauge.
     * If the size of the Gauge project is too big, Gauge might time out before the language runner returns the response message.
     */
    Long runner_request_timeout();

}