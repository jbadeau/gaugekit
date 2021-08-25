package org.gaugekit.table.diff;

import com.thoughtworks.gauge.Table;

import java.util.Arrays;

public class TableBuilder {

    private Table table;

    public TableBuilder(String... headers) {
        table = new Table(Arrays.asList(headers));
    }

    public TableBuilder addRow(String... cells) {
        table.addRow(Arrays.asList(cells));
        return this;
    }

    public Table build() {
        return table;
    }

}
