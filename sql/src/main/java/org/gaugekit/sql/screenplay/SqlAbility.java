package org.gaugekit.sql.screenplay;

import org.gaugekit.screenplay.Ability;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlAbility implements Ability {

    private Connection connection;

    private ResultSet lastResultSet;

    public SqlAbility(Connection connection) {
            this.connection = connection;
    }

    public static Ability sql(Connection connection) {
        return new SqlAbility(connection);
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