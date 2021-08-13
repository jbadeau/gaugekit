package org.gaugekit.example.sql.google;

import static org.gaugekit.table.matcher.TableHasTheSameRowsAs.hasTheSameRowsAs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.gaugekit.common.io.TableReader;
import org.gaugekit.sql.SqlProperties;
import com.thoughtworks.gauge.BeforeSpec;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.datastore.DataStoreFactory;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class GoogleSearchSteps {

    private static Connection conn = null;

    @BeforeSpec
    public void beforeSpec() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class.forName(SqlProperties.getSqlDatabaseDriver()).newInstance();
        Properties properties = new Properties();
        properties.put("create", "true");
        properties.put("user", SqlProperties.getSqlDatabaseUser());
        properties.put("password", "DatabaseProperties.getDatabasePassword()");
        conn = DriverManager.getConnection(SqlProperties.getSqlDatabaseUrl(), properties);
    }

    @Step("goto google")
    public void gotoGoogle() {
    }

    @Step("search for <query>")
    public void searchForQuery(String query) throws SQLException {
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = stmt.executeQuery(new StringBuilder("SELECT * FROM RESOURCE").toString());
        Table targetTable = TableReader.readResultSetTable(resultSet);
        resultSet.close();
        stmt.close();
        DataStoreFactory.getScenarioDataStore().put("targetTable", targetTable);
    }

    @Step("<title> is the first result item")
    public void titleIsTheFirstResultItem(String title) throws SQLException {
        Table table = (Table) DataStoreFactory.getScenarioDataStore().get("targetTable");
        assertThat(table.getTableRows().get(0).getCell("TITLE"), equalTo(title));
    }

    @Step("result matches <file>")
    public void resultMatchesFile(String file) throws SQLException, IOException {
        Table sourceTable = TableReader.readCsvTable(file);
        Table targetTable = (Table) DataStoreFactory.getScenarioDataStore().get("targetTable");
        assertThat(sourceTable, hasTheSameRowsAs(targetTable).inOrder());
    }

}