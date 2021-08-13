package org.gaugekit.browser.property;

import org.gaugekit.common.property.DefaultProperties;

import java.io.File;

public final class ProxyProperties {

    private ProxyProperties() {
    }

    public static final boolean isProxyEnabled() {
        return Boolean.parseBoolean(System.getenv("proxy_enabled"));
    }

    public static final boolean isProxyHarRecordingEnabled() {
        return isProxyEnabled() && Boolean.parseBoolean(System.getenv("proxy_har_recording_enabled"));
    }

    public static final File getProxyHarRecordingDir() {
        return new File(DefaultProperties.getProjectRoot(), System.getenv("proxy_har_recording_dir"));
    }

}