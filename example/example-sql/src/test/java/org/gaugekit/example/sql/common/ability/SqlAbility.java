package org.gaugekit.example.sql.common.ability;

import org.gaugekit.screenplay.Ability;
import org.testcontainers.containers.JdbcDatabaseContainer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlAbility implements Ability {

    JdbcDatabaseContainer container;

    private Connection connection;

    private ResultSet lastResultSet;

    public SqlAbility(JdbcDatabaseContainer container, String queryString) {
        try {
            container = container;
            connection = container.createConnection(queryString);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Ability sql(JdbcDatabaseContainer container, String queryString) {
        return new SqlAbility(container, queryString);
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet getLastResultSet() {
        return lastResultSet;
    }

    public void setLastResultSet(ResultSet lastResultSet) {
        this.lastResultSet = lastResultSet;
    }

    @Override
    public void cleanUp() {
        try {
            connection.close();
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
        container.stop();
    }

}