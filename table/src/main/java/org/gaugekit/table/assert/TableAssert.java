package org.gaugekit.table.diff;

import com.thoughtworks.gauge.Table;
import org.assertj.core.api.AbstractAssert;

public class TableAssert extends AbstractAssert<TableAssert, Table> {

    public TableAssert(Table actual) {
        super(actual, TableAssert.class);
    }


    public static TableAssert assertThat(Table actual) {
        return new TableAssert(actual);
    }

    public TableAssert hasTheSameRowsAs(Table expected) {
        isNotNull();
        TableDiffer tableDiffer = new TableDiffer(actual, expected);
        TableDiff diff = tableDiffer.calculateUnorderedDiffs();
        if (!diff.isEmpty()) {
            failWithMessage("Expected tables to have the same rows but were %s", diff);
        }
        return this;
    }

    public TableAssert hasTheSameRowsAndOrderAs(Table expected) {
        isNotNull();
        TableDiffer tableDiffer = new TableDiffer(actual, expected);
        TableDiff diff = tableDiffer.calculateUnorderedDiffs();
        if (!diff.isEmpty()) {
            failWithMessage("Expected tables to have the same rows but were %s", diff);
        }
        return this;
    }

}
