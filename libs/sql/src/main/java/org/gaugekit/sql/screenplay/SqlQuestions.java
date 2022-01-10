package org.gaugekit.sql.screenplay;

import com.thoughtworks.gauge.Table;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Question;
import org.gaugekit.sql.io.SqlReader;


public class SqlQuestions {

    public static Question<Table> tableFromResultSet() {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                ExecuteSQLQueries ability = actor.uses(ExecuteSQLQueries.class);
                return SqlReader.readTable(ability.getLastResultSet());
            }
        };
    }

}
