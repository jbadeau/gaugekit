package org.gaugekit.table.util;

import com.thoughtworks.gauge.Table;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gaugekit.core.util.DataUtils;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class TableReader {

    private static DataFormatter dataFormatter = new DataFormatter();

    private TableReader() {
    }

    public static Table readFromExcel(Path file, String sheetName) {
        try {
            Workbook workbook = new XSSFWorkbook(new FileInputStream(file.toFile()));
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException(String.format("Sheet with name '%s' does not exist", sheetName));
            }
            List<String> headers = readFromExcelRow(sheet.getRow(0));
            validateHeaders(headers);
            Table table = new Table(headers);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                table.addRow(readFromExcelRow(sheet.getRow(i)));
            }
            return table;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Table readFromExcel(String path, String sheetName) {
        return readFromExcel(DataUtils.fileAt(path), sheetName);
    }

    public static Table readFromCsv(Path file) {
        try {
            CSVParser parser = CSVParser.parse(new java.io.FileReader(file.toFile()), CSVFormat.DEFAULT
                    .withFirstRecordAsHeader());
            List<String> headers = parser.getHeaderNames();
            validateHeaders(headers);
            Table table = new Table(headers);
            for (CSVRecord record : parser.getRecords()) {
                table.addRow(readFromCsvRow(record));
            }
            return table;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Table readFromCsv(String path) {
        return readFromCsv(DataUtils.fileAt(path));
    }

    public static Table readFromResultSet(ResultSet resultSet) {
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

    private static List<String> readFromExcelRow(Row row) {
        List<String> tableRow = new ArrayList<String>();
        for (int i = 0; i < row.getLastCellNum(); i++) {
            tableRow.add(dataFormatter.formatCellValue(row.getCell(i)));
        }
        return tableRow;
    }

    private static List<String> readFromCsvRow(CSVRecord row) {
        List<String> tableRow = new ArrayList<String>();
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