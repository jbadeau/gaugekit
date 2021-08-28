package org.gaugekit.sql.screenplay;

import com.thoughtworks.gauge.Table;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Question;
import org.gaugekit.core.table.TableReader;


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
