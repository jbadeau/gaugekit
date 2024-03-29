package org.gaugekit.example.sql;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import org.gaugekit.core.screenplay.Cast;
import org.gaugekit.core.screenplay.Director;
import org.gaugekit.core.table.screenplay.ManageTables;
import org.gaugekit.example.sql.common.memory.CommonMemories;
import org.gaugekit.sql.screenplay.ExecuteSQLQueries;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;

import java.sql.SQLException;

public class Hooks implements CommonMemories {

    @BeforeScenario
    public void beforeScenario() throws SQLException {
        JdbcDatabaseContainer container = (MySQLContainer) new MySQLContainer()
                .withDatabaseName("test")
                .withInitScript("resources.sql");
        container.start();

        ScenarioDataStore.put(JDBC_CONTAINER, container);

        Cast cast = new Cast();
        cast.actorNamed("John",
                ManageTables.with(),
                ExecuteSQLQueries.sql(container.createConnection("?")));
        Director.setStage(cast);
    }

    @AfterScenario
    public void afterScenario() {
        JdbcDatabaseContainer container = (JdbcDatabaseContainer) ScenarioDataStore.get(JDBC_CONTAINER);
        container.stop();
    }

}
