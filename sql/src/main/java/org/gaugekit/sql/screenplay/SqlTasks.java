package org.gaugekit.sql.screenplay;

import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface SqlTasks {

    default Task query(String query) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                SqlAbility ability = actor.uses(SqlAbility.class);
                try {
                    Statement statement = ability.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ability.setLastResultSet(statement.executeQuery(query));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

}
