package org.gaugekit.assertion.table;

import com.thoughtworks.gauge.Table;
import org.assertj.core.api.AbstractAssert;

public class TableAssert extends AbstractAssert<TableAssert, Table> {

    public TableAssert(Table actual) {
        super(actual, TableAssert.class);
    }

    public static TableAssert assertThat(Table actual) {
        return new TableAssert(actual);
    }

    public TableAssert empty() {
        isNotNull();

        if (actual.getTableRows().isEmpty()) {
            failWithMessage("Expected table to be empty <%s> but has <%s> rows", actual.getTableRows().size());
        }

        return this;
    }

    public TableAssert rowCount(int count) {
        isNotNull();

        if (actual.getTableRows().size() == count) {
            failWithMessage("Expected table to have row count <%s> but was <%s>", count, actual.getTableRows().size());
        }

        return this;
    }

}
