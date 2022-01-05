package org.gaugekit.sql;

import org.aeonbits.owner.Config;

@Config.Sources({"system:env"})
public interface SqlProperties extends Config {

    String sql_database_url();

   String sql_database_driver();

    String sql_database_user();

    String sql_database_password();

}