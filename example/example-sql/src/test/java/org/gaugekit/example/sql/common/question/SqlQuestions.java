package org.gaugekit.example.sql.common.question;

import com.thoughtworks.gauge.Table;
import org.gaugekit.example.sql.common.ability.SqlAbility;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Question;
import org.gaugekit.table.util.TableReader;

public class SqlQuestions {

    private SqlQuestions() {
    }

    public static Question<Table> results() {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                SqlAbility ability = actor.uses(SqlAbility.class);
                return TableReader.readFromResultSet(ability.getLastResultSet());
            }
        };
    }

}
