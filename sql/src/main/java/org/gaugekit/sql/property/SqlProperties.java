package org.gaugekit.sql.property;

import org.gaugekit.common.property.GaugeProperties;

public final class SqlProperties extends GaugeProperties {

    private static String sql_database_url = "sql_database_url";

    private static String sql_database_driver = "sql_database_driver";

    private static String sql_database_user = "sql_database_user";

    private static String sql_database_password = "sql_database_password";

    private SqlProperties() {
        super();
    }

    public static String sql_database_url() {
        return getString(sql_database_url);
    }

    public static String sql_database_driver() {
        return getString(sql_database_driver);
    }

    public static String sql_database_user() {
        return getString(sql_database_user);
    }

    public static String sql_database_password() {
        return getString(sql_database_password);
    }

}