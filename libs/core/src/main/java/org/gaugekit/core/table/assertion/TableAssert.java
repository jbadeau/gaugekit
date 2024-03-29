package org.gaugekit.core.table.assertion;

import com.thoughtworks.gauge.Table;
import org.assertj.core.api.AbstractAssert;
import org.gaugekit.core.table.diff.TableDiff;
import org.gaugekit.core.table.diff.TableDiffer;

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
            failWithMessage("Expected tables to have the same rows but was %s", diff);
        }
        return this;
    }

    public TableAssert hasTheSameRowsAndOrderAs(Table expected) {
        isNotNull();
        TableDiffer tableDiffer = new TableDiffer(actual, expected);
        TableDiff diff = tableDiffer.calculateDiffs();
        if (!diff.isEmpty()) {
            failWithMessage("Expected tables to have the same rows but was %s", diff);
        }
        return this;
    }

}
