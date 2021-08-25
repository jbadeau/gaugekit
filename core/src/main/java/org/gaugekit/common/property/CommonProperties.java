package org.gaugekit.common.property;

public final class CommonProperties extends GaugeProperties {

    private CommonProperties() {
        super();
    }

    public static String common_test_environment() {
        return getString("common_test_environment");
    }

}