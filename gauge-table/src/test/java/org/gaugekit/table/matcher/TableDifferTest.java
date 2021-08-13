package org.gaugekit.table.matcher;

import org.gaugekit.table.diff.TableBuilder;
import org.gaugekit.table.diff.TableDiff;
import org.gaugekit.table.diff.TableDiffer;
import org.gaugekit.table.diff.TableDiffException;
import com.thoughtworks.gauge.Table;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TableDifferTest {

    private Table table() {
        return new TableBuilder("name", "email", "code")
                .addRow("Aslak", "aslak@email.com", "123")
                .addRow("Joe", "joe@email.com", "234")
                .addRow("Bryan", "bryan@email.org", "456")
                .addRow("Ni", "ni@email.com", "654")
                .build();
    }

    private Table tableWithDuplicate() {
        return new TableBuilder("name", "email", "code")
                .addRow("Aslak", "aslak@email.com", "123")
                .addRow("Joe", "joe@email.com", "234")
                .addRow("Bryan", "bryan@email.org", "456")
                .addRow("Joe", "joe@email.com", "234")
                .addRow("Ni", "ni@email.com", "654")
                .addRow("Ni", "ni@email.com", "654")
                .build();
    }

    private Table otherTableWithTwoConsecutiveRowsDeleted() {
        return new TableBuilder("name", "email", "code")
                .addRow("Aslak", "aslak@email.com", "123")
                .addRow("Ni", "ni@email.com", "654")
                .build();
    }

    private Table otherTableWithTwoConsecutiveRowsChanged() {
        return new TableBuilder("name", "email", "code")
                .addRow("Aslak", "aslak@email.com", "123")
                .addRow("Joe", "joe@NOSPAM.com", "234")
                .addRow("Bryan", "bryan@NOSPAM.org", "456")
                .addRow("Ni", "ni@email.com", "654")
                .build();
    }

    private Table otherTableWithTwoConsecutiveRowsInserted() {
        return new TableBuilder("name", "email", "code")
                .addRow("Aslak", "aslak@email.com", "123")
                .addRow("Joe", "joe@email.com", "234")
                .addRow("Doe", "joe@email.com", "234")
                .addRow("Foo", "schnickens@email.net", "789")
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

    private Table otherTableWithInsertedAtEnd() {
        return new TableBuilder("name", "email", "code")
                .addRow("Aslak", "aslak@email.com", "123")
                .addRow("Joe", "joe@email.com", "234")
                .addRow("Bryan", "bryan@email.org", "456")
                .addRow("Ni", "ni@email.com", "654")
                .addRow("Doe", "joe@email.com", "234")
                .addRow("Foo", "schnickens@email.net", "789")
                .build();
    }

    private Table otherTableWithDifferentOrder() {
        return new TableBuilder("name", "email", "code")
                .addRow("Joe", "joe@email.com", "234")
                .addRow("Aslak", "aslak@email.com", "123")
                .addRow("Bryan", "bryan@email.org", "456")
                .addRow("Ni", "ni@email.com", "654")
                .build();
    }

    private Table otherTableWithDifferentOrderAndDuplicate() {
        return new TableBuilder("name", "email", "code")
                .addRow("Joe", "joe@email.com", "234")
                .addRow("Aslak", "aslak@email.com", "123")
                .addRow("Bryan", "bryan@email.org", "456")
                .addRow("Ni", "ni@email.com", "654")
                .addRow("Ni", "ni@email.com", "654")
                .addRow("Joe", "joe@email.com", "234")
                .build();
    }

    private Table otherTableWithDifferentOrderDuplicateAndDeleted() {
        return new TableBuilder("name", "email", "code")
                .addRow("Joe", "joe@email.com", "234")
                .addRow("Bryan", "bryan@email.org", "456")
                .addRow("Bryan", "bryan@email.org", "456")
                .addRow("Ni", "ni@email.com", "654")
                .addRow("Bob", "bob.email.com", "555")
                .addRow("Bryan", "bryan@email.org", "456")
                .addRow("Ni", "ni@email.com", "654")
                .addRow("Joe", "joe@email.com", "234")
                .build();
    }

    private Table otherTableWithDeletedAndInsertedDifferentOrder() {
        return new TableBuilder("name", "email", "code")
                .addRow("Doe", "joe@email.com", "234")
                .addRow("Foo", "schnickens@email.net", "789")
                .addRow("Aslak", "aslak@email.com", "123")
                .addRow("Bryan", "bryan@email.org", "456")
                .build();
    }

    @Test
    public void shouldFindDifferences() {
        String expected = "" +

                "      | Aslak | aslak@email.com      | 123 |\n" +
                "    - | Joe   | joe@email.com        | 234 |\n" +
                "    + | Doe   | joe@email.com        | 234 |\n" +
                "    + | Foo   | schnickens@email.net | 789 |\n" +
                "      | Bryan | bryan@email.org      | 456 |\n" +
                "    - | Ni    | ni@email.com         | 654 |\n";
        assertDiff(table(), otherTableWithDeletedAndInserted(), expected);
    }

    @Test
    public void shouldFindNewLinesAtEnd() {
        String expected = "" +

                "      | Aslak | aslak@email.com      | 123 |\n" +
                "      | Joe   | joe@email.com        | 234 |\n" +
                "      | Bryan | bryan@email.org      | 456 |\n" +
                "      | Ni    | ni@email.com         | 654 |\n" +
                "    + | Doe   | joe@email.com        | 234 |\n" +
                "    + | Foo   | schnickens@email.net | 789 |\n";


        assertDiff(table(), otherTableWithInsertedAtEnd(), expected);
    }

    @Test
    public void considers_same_table_as_equal() {
        assertTrue(new TableDiffer(table(), table()).calculateDiffs().isEmpty());
    }

    @Test
    public void should_find_new_lines_at_end_when_using_diff() {
        String expected = "" +

                "      | Aslak | aslak@email.com      | 123 |\n" +
                "      | Joe   | joe@email.com        | 234 |\n" +
                "      | Bryan | bryan@email.org      | 456 |\n" +
                "      | Ni    | ni@email.com         | 654 |\n" +
                "    + | Doe   | joe@email.com        | 234 |\n" +
                "    + | Foo   | schnickens@email.net | 789 |\n";

        assertDiff(table(), otherTableWithInsertedAtEnd(), expected);
    }

    @Test
    public void should_diff_when_consecutive_deleted_lines() {
        String expected = "" +

                "      | Aslak | aslak@email.com | 123 |\n" +
                "    - | Joe   | joe@email.com   | 234 |\n" +
                "    - | Bryan | bryan@email.org | 456 |\n" +
                "      | Ni    | ni@email.com    | 654 |\n";
        assertDiff(table(), otherTableWithTwoConsecutiveRowsDeleted(), expected);
    }

    @Test
    public void should_diff_with_empty_list() {
        String expected = "" +

                "    - | Aslak | aslak@email.com | 123 |\n" +
                "    - | Joe   | joe@email.com   | 234 |\n" +
                "    - | Bryan | bryan@email.org | 456 |\n" +
                "    - | Ni    | ni@email.com    | 654 |\n";
        assertDiff(table(), new TableBuilder("name", "email", "code").build(), expected);
    }

    @Test
    public void should_diff_with_empty_table() {
        String expected = "" +

                "    - | Aslak | aslak@email.com | 123 |\n" +
                "    - | Joe   | joe@email.com   | 234 |\n" +
                "    - | Bryan | bryan@email.org | 456 |\n" +
                "    - | Ni    | ni@email.com    | 654 |\n";

        assertDiff(table(), new TableBuilder("name", "email", "code").build(), expected);
    }

    @Test
    public void empty_list_should_not_diff_with_empty_table() {
        List<List<String>> emptyList = new ArrayList<>();
        Table emptyTable = new TableBuilder("name", "email", "code").build();
        assertEquals(emptyTable.getRows(), emptyList);
    }

    @Test
    public void should_diff_when_consecutive_changed_lines() {
        String expected = "" +

                "      | Aslak | aslak@email.com  | 123 |\n" +
                "    - | Joe   | joe@email.com    | 234 |\n" +
                "    - | Bryan | bryan@email.org  | 456 |\n" +
                "    + | Joe   | joe@NOSPAM.com   | 234 |\n" +
                "    + | Bryan | bryan@NOSPAM.org | 456 |\n" +
                "      | Ni    | ni@email.com     | 654 |\n";

        assertDiff(table(), otherTableWithTwoConsecutiveRowsChanged(), expected);
    }

    @Test
    public void should_diff_when_consecutive_inserted_lines() {
        String expected = "" +

                "      | Aslak | aslak@email.com      | 123 |\n" +
                "      | Joe   | joe@email.com        | 234 |\n" +
                "    + | Doe   | joe@email.com        | 234 |\n" +
                "    + | Foo   | schnickens@email.net | 789 |\n" +
                "      | Bryan | bryan@email.org      | 456 |\n" +
                "      | Ni    | ni@email.com         | 654 |\n";
        assertDiff(table(), otherTableWithTwoConsecutiveRowsInserted(), expected);
    }

    @Test
    public void should_return_tables() {
        String expected = "" +
                "      | Aslak | aslak@email.com      | 123 |\n" +
                "      | Joe   | joe@email.com        | 234 |\n" +
                "    + | Doe   | joe@email.com        | 234 |\n" +
                "    + | Foo   | schnickens@email.net | 789 |\n" +
                "      | Bryan | bryan@email.org      | 456 |\n" +
                "      | Ni    | ni@email.com         | 654 |\n";

        assertDiff(table(), otherTableWithTwoConsecutiveRowsInserted(), expected);
    }

    @Test
    public void unordered_diff_with_itself() {
        assertTrue(new TableDiffer(table(), table()).calculateUnorderedDiffs().isEmpty());
    }

    @Test
    public void unordered_diff_with_itself_in_different_order() {
        assertTrue(new TableDiffer(table(), otherTableWithDifferentOrder()).calculateUnorderedDiffs().isEmpty());
    }

    @Test
    public void unordered_diff_with_less_lines_in_other() {
        String expected = "" +

                "      | Aslak | aslak@email.com | 123 |\n" +
                "    - | Joe   | joe@email.com   | 234 |\n" +
                "    - | Bryan | bryan@email.org | 456 |\n" +
                "      | Ni    | ni@email.com    | 654 |\n";
        assertUnorderedDiff(table(), otherTableWithTwoConsecutiveRowsDeleted(), expected);
    }

    @Test
    public void unordered_diff_with_more_lines_in_other() {
        String expected = "" +

                "      | Aslak | aslak@email.com      | 123 |\n" +
                "      | Joe   | joe@email.com        | 234 |\n" +
                "      | Bryan | bryan@email.org      | 456 |\n" +
                "      | Ni    | ni@email.com         | 654 |\n" +
                "    + | Doe   | joe@email.com        | 234 |\n" +
                "    + | Foo   | schnickens@email.net | 789 |\n";
        assertUnorderedDiff(table(), otherTableWithTwoConsecutiveRowsInserted(), expected);
    }

    @Test
    public void unordered_diff_with_added_and_deleted_rows_in_other() {
        String expected = "" +

                "      | Aslak | aslak@email.com      | 123 |\n" +
                "    - | Joe   | joe@email.com        | 234 |\n" +
                "      | Bryan | bryan@email.org      | 456 |\n" +
                "    - | Ni    | ni@email.com         | 654 |\n" +
                "    + | Doe   | joe@email.com        | 234 |\n" +
                "    + | Foo   | schnickens@email.net | 789 |\n";
        assertUnorderedDiff(table(), otherTableWithDeletedAndInsertedDifferentOrder(), expected);
    }

    @Test
    public void unordered_diff_with_added_duplicate_in_other() {
        String expected = "" +

                "      | Aslak | aslak@email.com | 123 |\n" +
                "      | Joe   | joe@email.com   | 234 |\n" +
                "      | Bryan | bryan@email.org | 456 |\n" +
                "      | Ni    | ni@email.com    | 654 |\n" +
                "    + | Ni    | ni@email.com    | 654 |\n" +
                "    + | Joe   | joe@email.com   | 234 |\n";
        assertUnorderedDiff(table(), otherTableWithDifferentOrderAndDuplicate(), expected);
    }

    @Test
    public void unordered_diff_with_added_duplicate_in_other_reversed() {
        String expected = "" +

                "      | Joe   | joe@email.com   | 234 |\n" +
                "      | Aslak | aslak@email.com | 123 |\n" +
                "      | Bryan | bryan@email.org | 456 |\n" +
                "      | Ni    | ni@email.com    | 654 |\n" +
                "    - | Ni    | ni@email.com    | 654 |\n" +
                "    - | Joe   | joe@email.com   | 234 |\n";
        assertUnorderedDiff(otherTableWithDifferentOrderAndDuplicate(), table(), expected);
    }

    @Test
    public void unordered_diff_with_added_duplicate_and_deleted_in_other() {
        String expected = "" +

                "    - | Aslak | aslak@email.com | 123 |\n" +
                "      | Joe   | joe@email.com   | 234 |\n" +
                "      | Bryan | bryan@email.org | 456 |\n" +
                "      | Joe   | joe@email.com   | 234 |\n" +
                "      | Ni    | ni@email.com    | 654 |\n" +
                "      | Ni    | ni@email.com    | 654 |\n" +
                "    + | Bryan | bryan@email.org | 456 |\n" +
                "    + | Bob   | bob.email.com   | 555 |\n" +
                "    + | Bryan | bryan@email.org | 456 |\n";

        assertUnorderedDiff(tableWithDuplicate(), otherTableWithDifferentOrderDuplicateAndDeleted(), expected);
    }

    @Test
    public void memory() {
        String expected = "" +

                "      | Joe   | joe@email.com   | 234 |\n" +
                "      | Aslak | aslak@email.com | 123 |\n" +
                "      | Bryan | bryan@email.org | 456 |\n" +
                "      | Ni    | ni@email.com    | 654 |\n" +
                "    - | Ni    | ni@email.com    | 654 |\n" +
                "    - | Joe   | joe@email.com   | 234 |\n";
        assertUnorderedDiff(otherTableWithDifferentOrderAndDuplicate(), table(), expected);
    }

    private void assertUnorderedDiff(Table table, Table other, String expected) {
        try {
            TableDiffer tableDiffer = new TableDiffer(table, other);
            TableDiff dataTableDiff = tableDiffer.calculateUnorderedDiffs();
            if (!dataTableDiff.isEmpty()) {
                throw TableDiffException.diff(dataTableDiff);
            }
            fail("Expected exception");
        } catch (TableDiffException e) {
            assertEquals("tables were different:\n" + expected, e.getMessage());
        }
    }

    private void assertDiff(Table table, Table other, String expected) {
        try {
            TableDiffer tableDiffer = new TableDiffer(table, other);
            TableDiff dataTableDiff = tableDiffer.calculateDiffs();
            if (!dataTableDiff.isEmpty()) {
                throw TableDiffException.diff(dataTableDiff);
            }
            fail("Expected exception");
        } catch (TableDiffException e) {
            assertEquals("tables were different:\n" + expected, e.getMessage());
        }
    }

}
