package org.gaugekit.core.table.screenplay;

import com.thoughtworks.gauge.Table;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Question;

import java.nio.file.Path;
import java.sql.ResultSet;

public class TableQuestions {

    public static Question<Table> tableFromExcel(Path excelFile, String worksheet) {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                ManageTables ability = actor.uses(ManageTables.class);
                return TableReader.tableFromExcel(excelFile, worksheet);
            }
        };
    }

    public static Question<Table> tableFromCsv(Path csvFile) {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                ManageTables ability = actor.uses(ManageTables.class);
                return TableReader.tableFromCsv(csvFile);
            }
        };
    }

    public static Question<Table> tableFromCsv(String csvFile) {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                ManageTables ability = actor.uses(ManageTables.class);
                return TableReader.tableFromCsv(csvFile);
            }
        };
    }

    public static Question<Table> tableFromResultSet(ResultSet resultSet) {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                ManageTables ability = actor.uses(ManageTables.class);
                return TableReader.tableFromResultSet(resultSet);
            }
        };
    }

}