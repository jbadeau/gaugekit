package org.gaugekit.sql;

public final class SqlProperties {

    private SqlProperties() {
    }

    public static String getSqlDatabaseUrl() {
        return System.getenv("sql_database_url");
    }

    public static String getSqlDatabaseDriver() {
        return System.getenv("sql_database_driver");
    }

    public static String getSqlDatabaseUser() {
        return System.getenv("sql_database_user");
    }

    public static String getSqlDatabasePassword() {
        return System.getenv("sql_database_password");
    }

    public static String getSqlSourceDatabaseUrl() {
        return System.getenv("sql_source_database_url");
    }

    public static String getSqlSourceDatabaseDriver() {
        return System.getenv("sql_source_database_driver");
    }

    public static String getSqlSourceDatabaseUser() {
        return System.getenv("sql_source_database_user");
    }

    public static String getSqlSourceDatabasePassword() {
        return System.getenv("sql_source_database_password");
    }

    public static String getSqlTargetDatabaseUrl() {
        return System.getenv("sql_target_database_url");
    }

    public static String getSqlTargetDatabaseDriver() {
        return System.getenv("sql_target_database_driver");
    }

    public static String getSqlTargetDatabaseUser() {
        return System.getenv("sql_target_database_user");
    }

    public static String getSqlTargetDatabasePassword() {
        return System.getenv("sql_target_database_password");
    }

}