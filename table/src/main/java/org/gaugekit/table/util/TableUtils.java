package org.gaugekit.table.util;

import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TableUtils {

    private TableUtils() {
    }

    public static Map<String, String> tableToMap(Table table) {
        return tableToMap(table, table.getColumnNames().get(0), table.getColumnNames().get(1));
    }

    public static Map<String, String> tableToMap(Table table, String keyColumn, String valueColumn) {
        Map<String, String> map = new HashMap<>(table.getTableRows().size());
        for (TableRow row : table.getTableRows()) {
            map.put(row.getCell(keyColumn), row.getCell(valueColumn));
        }
        return map;
    }

    public static List<String> tableToList(Table table) {
        return tableToList(table, table.getColumnNames().get(0));
    }

    public static List<String> tableToList(Table table, String valueColumn) {
        List<String> list = new ArrayList<>(table.getTableRows().size());
        for (TableRow row : table.getTableRows()) {
            list.add(row.getCell(valueColumn));
        }
        return list;
    }

}
