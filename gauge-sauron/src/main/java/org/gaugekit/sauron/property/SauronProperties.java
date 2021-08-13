package org.gaugekit.sauron.property;

import org.gaugekit.common.io.FileReader;

import java.io.File;

public final class SauronProperties {

    private SauronProperties() {
    }

    public static Boolean isEnabled() {
        return Boolean.getBoolean(System.getenv("sauron_screenshots_enabled"));
    }

    public static File getScreenshotDir() {
        return FileReader.fileAt(System.getenv("sauron_screenshots_dir"));
    }

    public static String getProject() {
        return System.getenv("sauron_screenshots_project");
    }

    public static File getProjectDir() {
        return new File(getScreenshotDir(), getProject());
    }

    public static File getBaselineDir() {
        return new File(getProjectDir(), System.getenv("sauron_creenshots_baseline_dir"));
    }

    public static File getSnapshotDir() {
        return new File(getProjectDir(), System.getenv("sauron_screenshots_snapshot_dir"));
    }

    public static File getDiffDir() {
        return new File(getProjectDir(), System.getenv("sauron_screenshots_diff_dir"));
    }

}