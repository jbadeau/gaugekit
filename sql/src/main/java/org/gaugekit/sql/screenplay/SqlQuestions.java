package org.gaugekit.sql.screenplay;

import com.thoughtworks.gauge.Table;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Question;
import org.gaugekit.table.io.TableReader;


public interface SqlQuestions {

    default Question<Table> tableFromResultSet() {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                SqlAbility ability = actor.uses(SqlAbility.class);
                return TableReader.tableFromResultSet(ability.getLastResultSet());
            }
        };
    }

}
