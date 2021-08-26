package org.gaugekit.table.diff;

import com.thoughtworks.gauge.Table;
import org.junit.jupiter.api.Test;

public class TablePrinterTest {

    private Table table() {
        return new TableBuilder("name", "email", "code")
                .addRow("Aslak", "aslak@email.com", "123")
                .addRow("Joe", "joe@email.com", "234")
                .addRow("Bryan", "bryan@email.org", "456")
                .addRow("Ni", "ni@email.com", "654")
                .build();
    }

    private Table otherTableWithDeletedAndInserted() {
        return new TableBuilder("name", "email", "code")
                .addRow("Aslak", "aslak@email.com", "123")
                .addRow("Doe", "joe@email.com", "234")
                .addRow("Foo", "schnickens@email.net", "789")
                .addRow("Bryan", "bryan@email.org", "456")
                .build();
    }

    @Test
    public void shouldFindDifferences() {
        TableDiffer tableDiffer = new TableDiffer(table(), otherTableWithDeletedAndInserted());
        TableDiff dataTableDiff = tableDiffer.calculateUnorderedDiffs();
        System.out.println(dataTableDiff.toString());
    }


}
