package org.gaugekit.sql.screenplay;

import org.gaugekit.core.screenplay.Ability;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExecuteSQLQueries implements Ability {

    private Connection connection;

    private ResultSet lastResultSet;

    public ExecuteSQLQueries(Connection connection) {
            this.connection = connection;
    }

    public static Ability sql(Connection connection) {
        return new ExecuteSQLQueries(connection);
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
    }

}