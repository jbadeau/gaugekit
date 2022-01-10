package org.gaugekit.csv.screenplay;

import com.thoughtworks.gauge.Table;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Question;
import org.gaugekit.core.table.screenplay.ManageTables;
import org.gaugekit.csv.io.CsvReader;

import java.nio.file.Path;

public class CsvQuestions {

    private CsvQuestions() {
    }

    public static Question<Table> tableFromCsv(Path file) {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                ManageTables ability = actor.uses(ManageTables.class);
                return CsvReader.readTable(file);
            }
        };
    }


}