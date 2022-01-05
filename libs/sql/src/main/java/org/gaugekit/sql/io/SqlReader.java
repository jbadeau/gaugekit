package org.gaugekit.sql.io;

import com.thoughtworks.gauge.Table;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class SqlReader {

    private SqlReader() {
    }

    public static Table readTable(ResultSet resultSet) {
        try {
            List<String> headers = new ArrayList<String>();
            ResultSetMetaData resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
            int counter = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= counter; i++) {
                headers.add(resultSetMetaData.getColumnLabel(i));
            }
            validateHeaders(headers);
            Table table = new Table(headers);
            List<String> row;
            while (resultSet.next()) {
                row = new ArrayList<String>();
                for (String header : headers) {
                    Object value = resultSet.getString(header);
                    row.add(value == null ? "" : value.toString());
                }
                table.addRow(row);
            }
            return table;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void validateHeaders(List<String> headers) {
        for (int i = 0; i < headers.size(); i++) {
            String header = headers.get(i);
            if (header == null || header.equals("")) {
                throw new RuntimeException(String.format("header at index '%s' is empty", i));
            }
        }
    }

}