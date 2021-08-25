package org.gaugekit.table.diff;

import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableDiffer {

    private final Table from;
    private final Table to;

    public TableDiffer(Table fromTable, Table toTable) {
        this.from = fromTable;
        this.to = toTable;
    }

    public TableDiff calculateDiffs() {
        Map<Integer, Delta> deltasByLine = createDeltasByLine();
        return createTableDiff(deltasByLine);
    }


    public TableDiff calculateUnorderedDiffs() {
        List<SimpleEntry<List<String>, DiffType>> diffTableRows = new ArrayList<>();

        // 1. add all "to" row in extra table
        // 2. iterate over "from", when a common row occurs, remove it from extraRows
        // finally, only extra rows are kept and in same order that in "to".
        List<TableRow> extraRows = new ArrayList<>(to.getTableRows());

        for (TableRow row : from.getTableRows()) {
            if (!extraRows.remove(row)) {
                diffTableRows.add(
                        new SimpleEntry<>(row.getCellValues(), DiffType.DELETE));
            } else {
                diffTableRows.add(
                        new SimpleEntry<>(row.getCellValues(), DiffType.NONE));
            }
        }

        for (TableRow cells : extraRows) {
            diffTableRows.add(
                    new SimpleEntry<>(cells.getCellValues(), DiffType.INSERT));
        }

        return TableDiff.create(diffTableRows);
    }

    private static List<DiffableRow> getDiffableRows(Table raw) {
        List<DiffableRow> result = new ArrayList<>();
        for (TableRow row : raw.getTableRows()) {
            result.add(new DiffableRow(row.getCellValues(), row.getCellValues()));
        }
        return result;
    }


    @SuppressWarnings("unchecked")
    private Map<Integer, Delta> createDeltasByLine() {
        Patch patch = DiffUtils.diff(getDiffableRows(from), getDiffableRows(to));
        List<Delta> deltas = patch.getDeltas();

        Map<Integer, Delta> deltasByLine = new HashMap<>();
        for (Delta delta : deltas) {
            deltasByLine.put(delta.getOriginal().getPosition(), delta);
        }
        return deltasByLine;
    }

    private TableDiff createTableDiff(Map<Integer, Delta> deltasByLine) {
        List<SimpleEntry<List<String>, DiffType>> diffTableRows = new ArrayList<>();
        List<TableRow> rows = from.getTableRows();
        for (int i = 0; i < rows.size(); i++) {
            Delta delta = deltasByLine.get(i);
            if (delta == null) {
                diffTableRows.add(new SimpleEntry<>(from.getTableRows().get(i).getCellValues(), DiffType.NONE));
            } else {
                addRowsToTableDiff(diffTableRows, delta);
                // skipping lines involved in a delta
                if (delta.getType() == Delta.TYPE.CHANGE || delta.getType() == Delta.TYPE.DELETE) {
                    i += delta.getOriginal().getLines().size() - 1;
                } else {
                    diffTableRows.add(new SimpleEntry<>(from.getTableRows().get(i).getCellValues(), DiffType.NONE));
                }
            }
        }
        // Can have new lines at end
        Delta remainingDelta = deltasByLine.get(rows.size());
        if (remainingDelta != null) {
            addRowsToTableDiff(diffTableRows, remainingDelta);
        }
        return TableDiff.create(diffTableRows);
    }

    private void addRowsToTableDiff(List<SimpleEntry<List<String>, DiffType>> diffTableRows, Delta delta) {
        markChangedAndDeletedRowsInOriginalAsMissing(diffTableRows, delta);
        markChangedAndInsertedRowsInRevisedAsNew(diffTableRows, delta);
    }

    @SuppressWarnings("unchecked")
    private void markChangedAndDeletedRowsInOriginalAsMissing(List<SimpleEntry<List<String>, DiffType>> diffTableRows, Delta delta) {
        List<DiffableRow> deletedLines = (List<DiffableRow>) delta.getOriginal().getLines();
        for (DiffableRow row : deletedLines) {
            diffTableRows.add(new SimpleEntry<>(row.row, DiffType.DELETE));
        }
    }

    @SuppressWarnings("unchecked")
    private void markChangedAndInsertedRowsInRevisedAsNew(List<SimpleEntry<List<String>, DiffType>> diffTableRows, Delta delta) {
        List<DiffableRow> insertedLines = (List<DiffableRow>) delta.getRevised().getLines();
        for (DiffableRow row : insertedLines) {
            diffTableRows.add(new SimpleEntry<>(row.row, DiffType.INSERT));
        }
    }
}