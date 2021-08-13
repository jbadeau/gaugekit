package org.gaugekit.table.matcher;

import org.gaugekit.table.diff.TableDiff;
import org.gaugekit.table.diff.TableDiffer;
import com.thoughtworks.gauge.Table;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * Matches two data tables by their rows. By default the matcher
 * does not take row order into account. This can be fluently
 * enabled.
 *
 * <pre>
 *   assertThat(identical, hasTheSameRowsAs(table).inOrder());
 *   assertThat(shuffled, hasTheSameRowsAs(table));
 * </pre>
 */
public final class TableHasTheSameRowsAs extends TypeSafeDiagnosingMatcher<Table> {
    private final Table expectedValue;
    private final boolean unordered;

    private TableHasTheSameRowsAs(Table expectedValue, boolean unordered) {
        this.expectedValue = expectedValue;
        this.unordered = unordered;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a datable with the same rows");
        if (unordered) {
            description.appendText(" in any order");
        }
    }


    @Override
    protected boolean matchesSafely(Table item, Description description) {
        TableDiffer tableDiffer = new TableDiffer(expectedValue, item);
        TableDiff diff = unordered ? tableDiffer.calculateUnorderedDiffs() : tableDiffer.calculateDiffs();

        if (diff.isEmpty()) {
            return true;
        }
        description.appendText("the tables were different\n");
        description.appendText(diff.toString());
        return false;
    }

    /**
     * Compare the rows of the data table in order.
     *
     * @return a new matcher that compares the rows of the data table in order.
     */
    public TableHasTheSameRowsAs inOrder() {
        return new TableHasTheSameRowsAs(expectedValue, false);
    }

    public static TableHasTheSameRowsAs hasTheSameRowsAs(Table operand) {
        return new TableHasTheSameRowsAs(operand, true);
    }


}
