package org.gaugekit.sauron;

import org.gaugekit.core.GaugeProperties;

import java.nio.file.Path;

public final class SauronProperties extends GaugeProperties {

    private static final String sauron_screenshots_enabled = "sauron_screenshots_enabled";

    private static final String sauron_dir = "sauron_dir";

    private static final String sauron_baseline_dir = "sauron_baseline_dir";

    private static final String sauron_snapshot_dir = "sauron_snapshot_dir";

    private static final String sauron_diff_dir = "sauron_diff_dir";

    private SauronProperties() {
        super();
    }

    public static Boolean screenshotsEnabled() {
        return getBoolean(sauron_screenshots_enabled);
    }

    public static Path sauronDir() {
        return getProjectRelativePath(sauron_dir);
    }

    public static Path baselineDir() {
        return getProjectRelativePath(sauron_baseline_dir);
    }

    public static Path snapshotDir() {
        return getProjectRelativePath(sauron_snapshot_dir);
    }

    public static Path diffDir() {
        return getProjectRelativePath(sauron_diff_dir);
    }

}