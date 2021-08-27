package org.gaugekit.sauron.property;

import org.gaugekit.core.property.GaugeProperties;

import java.nio.file.Path;

public final class SauronProperties extends GaugeProperties {

    private static final String sauron_screenshots_enabled = "sauron_screenshots_enabled";

    private static final String sauron_dir = "sauron_dir";

    private static final String sauron_baseline_dir = "sauron_baseline_dir";

    private static final String sauron_screenshot_dir = "sauron_screenshot_dir";

    private static final String sauron_diff_dir = "sauron_diff_dir";

    private SauronProperties() {
        super();
    }

    public static Boolean sauron_screenshots_enabled() {
        return getBoolean(sauron_screenshots_enabled);
    }

    public static Path sauron_dir() {
        return getProjectRelativePath(sauron_dir);
    }

    public static Path sauron_baseline_dir() {
        return getProjectRelativePath(sauron_baseline_dir);
    }

    public static Path sauron_screenshot_dir() {
        return getProjectRelativePath(sauron_screenshot_dir);
    }

    public static Path sauron_diff_dir() {
        return getProjectRelativePath(sauron_diff_dir);
    }

}