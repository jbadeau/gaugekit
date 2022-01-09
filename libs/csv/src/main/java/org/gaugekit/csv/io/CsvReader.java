package org.gaugekit.csv.io;

import com.thoughtworks.gauge.Table;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.gaugekit.core.io.file.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class CsvReader {

    private CsvReader() {
    }

    public static Table readTable(String path) {
        return readTable(FileUtils.resolveDataFile(path));
    }

    public static Table readTable(Path path) {
        try {
            CSVParser parser = CSVParser.parse(new java.io.FileReader(path.toFile()), CSVFormat.DEFAULT
                    .withFirstRecordAsHeader());
            List<String> headers = parser.getHeaderNames();
            validateHeaders(headers);
            Table table = new Table(headers);
            for (CSVRecord record : parser.getRecords()) {
                table.addRow(toTableRow(record));
            }
            return table;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> toTableRow(CSVRecord row) {
        List<String> tableRow = new ArrayList<>();
        for (String cell : row) {
            tableRow.add(cell);
        }
        return tableRow;
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