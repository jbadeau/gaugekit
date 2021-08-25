package org.gaugekit.example.sql;


import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import org.gaugekit.common.io.FileReader;
import org.gaugekit.example.sql.common.ability.IoAbility;
import org.gaugekit.example.sql.common.ability.SqlAbility;
import org.gaugekit.example.sql.common.memory.CommonMemories;
import org.gaugekit.screenplay.Cast;
import org.gaugekit.screenplay.OnStage;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;

public class Hooks implements CommonMemories {

    @BeforeScenario
    public void beforeScenario() {
        JdbcDatabaseContainer container = (MySQLContainer) new MySQLContainer()
                .withDatabaseName("test")
                .withInitScript("resources.sql");
        container.start();

        ScenarioDataStore.put(JDBC_CONTAINER, container);

        Cast cast = new Cast();
        cast.actorNamed("John", SqlAbility.sql(container, "?"), IoAbility.io());
        OnStage.setTheStage(cast);
    }

    @AfterScenario
    public void afterScenario() {
        JdbcDatabaseContainer container = (JdbcDatabaseContainer) ScenarioDataStore.get(JDBC_CONTAINER);
        container.stop();
    }

}
