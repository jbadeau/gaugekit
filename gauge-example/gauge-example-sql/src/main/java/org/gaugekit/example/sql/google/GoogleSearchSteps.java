package org.gaugekit.example.sql.google;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import org.gaugekit.common.io.TableReader;
import org.testcontainers.containers.MySQLContainer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.gaugekit.table.matcher.TableHasTheSameRowsAs.hasTheSameRowsAs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GoogleSearchSteps {

    private Connection conn;

    private MySQLContainer mysql;

    @BeforeScenario
    public void beforeScenario() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        mysql = (MySQLContainer) new MySQLContainer()
                .withDatabaseName("test")
                .withInitScript("RESOURCE.sql");
        mysql.start();
        conn = mysql.createConnection("?");
    }

    @AfterScenario
    public void afterScenario() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        conn.close();
        mysql.stop();
    }

    @Step("goto google")
    public void gotoGoogle() {
    }

    @Step("search for <query>")
    public void searchForQuery(String query) throws SQLException {
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = stmt.executeQuery(new StringBuilder("SELECT * FROM RESOURCE").toString());
        Table targetTable = TableReader.fromResultSet(resultSet);
        resultSet.close();
        stmt.close();
        ScenarioDataStore.put("targetTable", targetTable);
    }

    @Step("<title> is the first result item")
    public void titleIsTheFirstResultItem(String title) throws SQLException {
        Table table = (Table) ScenarioDataStore.get("targetTable");
        assertThat(table.getTableRows().get(0).getCell("title"), equalTo(title));
    }

    @Step("result matches <file>")
    public void resultMatchesFile(String file) throws SQLException, IOException {
        Table sourceTable = TableReader.fromCsv(file);
        Table targetTable = (Table) ScenarioDataStore.get("targetTable");
        assertThat(sourceTable, hasTheSameRowsAs(targetTable).inOrder());
    }

}