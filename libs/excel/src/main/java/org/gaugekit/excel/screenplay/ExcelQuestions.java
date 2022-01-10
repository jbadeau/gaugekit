package org.gaugekit.excel.screenplay;

import com.thoughtworks.gauge.Table;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Question;
import org.gaugekit.core.table.screenplay.ManageTables;
import org.gaugekit.excel.io.ExcelReader;

import java.nio.file.Path;

public class ExcelQuestions {

    public static Question<Table> tableFromExcel(Path file, String worksheet) {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                ManageTables ability = actor.uses(ManageTables.class);
                return ExcelReader.readTable(file, worksheet);
            }
        };
    }

}
