package org.gaugekit.core.property;


public final class CoreProperties extends GaugeProperties {

    private CoreProperties() {
        super();
    }

    public static String testEnvironment() {
        return getString("common_test_environment");
    }

}