package org.gaugekit.table.diff;


public final class TableDiffException extends RuntimeException {

    private TableDiffException(String message) {
        super(message);
    }

    public static TableDiffException diff(TableDiff tableDiff) {
        return new TableDiffException("tables were different:\n" + tableDiff.toString());
    }

}