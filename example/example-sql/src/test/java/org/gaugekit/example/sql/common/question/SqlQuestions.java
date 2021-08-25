package org.gaugekit.example.sql.common.question;

import com.thoughtworks.gauge.Table;
import org.gaugekit.common.io.TableReader;
import org.gaugekit.example.sql.common.ability.SqlAbility;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Question;

public class SqlQuestions {

    private SqlQuestions() {
    }

    public static Question<Table> results() {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                SqlAbility ability = actor.uses(SqlAbility.class);
                return TableReader.fromResultSet(ability.getLastResultSet());
            }
        };
    }

}
