package org.gaugekit.example.sql.common.question;

import com.thoughtworks.gauge.Table;
import org.gaugekit.table.util.TableReader;
import org.gaugekit.example.sql.common.ability.IoAbility;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Question;

import java.nio.file.Path;

public class IoQuestions {

    private IoQuestions() {
    }

    public static Question<Table> fromCsv(Path file) {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                IoAbility ability = actor.uses(IoAbility.class);
                return TableReader.readFromCsv(file);
            }
        };
    }

}
