package org.gaugekit.table.screenplay;

import com.thoughtworks.gauge.Table;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Question;
import org.gaugekit.table.io.TableReader;

import java.nio.file.Path;
import java.sql.ResultSet;

public interface TableQuestions {

    default Question<Table> tableFromExcel(Path excelFile, String worksheet) {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                TableAbility ability = actor.uses(TableAbility.class);
                return TableReader.tableFromExcel(excelFile, worksheet);
            }
        };
    }

    default Question<Table> tableFromCsv(Path csvFile) {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                TableAbility ability = actor.uses(TableAbility.class);
                return TableReader.tableFromCsv(csvFile);
            }
        };
    }

    default Question<Table> tableFromCsv(String csvFile) {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                TableAbility ability = actor.uses(TableAbility.class);
                return TableReader.tableFromCsv(csvFile);
            }
        };
    }

    default Question<Table> tableFromResultSet(ResultSet resultSet) {
        return new Question<Table>() {
            @Override
            public Table answerAs(Actor actor) {
                TableAbility ability = actor.uses(TableAbility.class);
                return TableReader.tableFromResultSet(resultSet);
            }
        };
    }

}