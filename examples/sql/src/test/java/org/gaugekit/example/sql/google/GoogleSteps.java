package org.gaugekit.example.sql.google;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.gaugekit.core.screenplay.Actor;
import static org.gaugekit.sql.screenplay.SqlQuestions.*;
import static org.gaugekit.sql.screenplay.SqlTasks.*;
import static org.gaugekit.core.table.screenplay.TableQuestions.*;

import java.nio.file.Path;

import static org.gaugekit.core.table.assertion.TableAssert.assertThat;

public class GoogleSteps implements GoogleMemories {

    @Step("And <actor> opens <app>")
    public void open(Actor actor, String app) {
    }

    @Step("When <actor> searches for <term>")
    public void search(Actor actor, String term) {
        actor.attemptsTo(query("SELECT * FROM RESOURCE"));
        actor.memorizes(SOURCE_TABLE, tableFromResultSet());
    }

    @Step("Then <actor> verifies that results matches <file>")
    public void verify(Actor actor, Path file) {
        Table sourceTable = actor.recites(SOURCE_TABLE);
        Table targetTable = actor.asksFor(tableFromCsv(file));
        assertThat(sourceTable).hasTheSameRowsAs(targetTable);
    }

}