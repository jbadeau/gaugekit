package org.gaugekit.sql.screenplay;

import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlTasks {

    public static Task query(String query) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                ExecuteSQLQueries ability = actor.uses(ExecuteSQLQueries.class);
                try {
                    Connection connection = ability.getConnection();
                    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ability.setLastResultSet(statement.executeQuery(query));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

}
