package org.gaugekit.example.sql.google;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.gaugekit.example.sql.common.question.IoQuestions;
import org.gaugekit.example.sql.common.question.SqlQuestions;
import org.gaugekit.example.sql.common.task.SqlTasks;
import org.gaugekit.screenplay.Actor;

import java.nio.file.Path;

import static org.gaugekit.table.assertion.TableAssert.*;

public class GoogleSteps implements GoogleMemories {

    @Step("And <actor> opens <app>")
    public void open(Actor actor, String app) {
    }

    @Step("When <actor> searches for <term>")
    public void search(Actor actor, String term) {
        actor.attemptsTo(SqlTasks.query(String.format("SELECT * FROM RESOURCE")));
        actor.memorizes(SOURCE_TABLE, SqlQuestions.results());
    }

    @Step("Then <actor> verifies that results matches <file>")
    public void verify(Actor actor, Path file) {
        Table sourceTable = actor.recites(SOURCE_TABLE);
        Table targetTable = actor.asksFor(IoQuestions.fromCsv(file));
        assertThat(sourceTable).hasTheSameRowsAs(targetTable);
    }

}