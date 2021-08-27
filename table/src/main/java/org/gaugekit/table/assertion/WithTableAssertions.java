package org.gaugekit.table.assertion;

import com.thoughtworks.gauge.Table;

public interface WithTableAssertions {

    default TableAssert assertThat(final Table actual) {
        return TableAssert.assertThat(actual);
    }

}
